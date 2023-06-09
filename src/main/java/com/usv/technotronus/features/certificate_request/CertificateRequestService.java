package com.usv.technotronus.features.certificate_request;

import com.usv.technotronus.features.academic_year.AcademicYearService;
import com.usv.technotronus.features.academic_year.dto.AcademicYearDto;
import com.usv.technotronus.features.certificate_request.dto.CertificateRequestBySecretaryDto;
import com.usv.technotronus.features.certificate_request.dto.CertificateRequestReportDto;
import com.usv.technotronus.features.certificate_request.dto.CreateCertificateRequestDto;
import com.usv.technotronus.features.certificate_request.dto.StudentCertificateRequestViewDto;
import com.usv.technotronus.features.exceptions.BadRequestException;
import com.usv.technotronus.features.generated_certificate.GeneratedCertificateDto;
import com.usv.technotronus.features.generated_certificate.GeneratedCertificateService;
import com.usv.technotronus.features.pdf_generator.PdfGeneratorService;
import com.usv.technotronus.features.study_program.StudyProgram;
import com.usv.technotronus.features.study_program.StudyProgramRepository;
import com.usv.technotronus.features.user.User;
import com.usv.technotronus.features.user.UserRepository;
import com.usv.technotronus.utilities.EmailService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CertificateRequestService {

    private final ModelMapper modelMapper;

    private final CertificateRequestRepository repository;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final PdfGeneratorService pdfGeneratorService;
    private final AcademicYearService academicYearService;
    private final GeneratedCertificateService generatedCertificateService;
    private final StudyProgramRepository studyProgramRepository;

    @PostConstruct
    public void postConstruct() {
        modelMapper.addMappings(Utils.createCertificateRequestDtoPropertyMap);
        modelMapper.addMappings(Utils.certificateRequestBySecretaryDtoPropertyMap);
    }

    public CreateCertificateRequestDto createCertificateRequest(CreateCertificateRequestDto requestDto) {

        CertificateRequest certificateRequest = modelMapper.map(requestDto, CertificateRequest.class);
        Optional<User> userOptional = userRepository.findById(requestDto.getUserId());
        if (userOptional.isEmpty()) {
            throw new BadRequestException();
        }

        UUID secretaryId = userOptional.get().getStudyProgram().getSecretary().getId();
        Optional<User> secretaryOptional = userRepository.findById(secretaryId);

        if (secretaryOptional.isEmpty()) {
            throw new BadRequestException();
        }
        var requestNumber = repository.getLastRequestNumber();
        if(Objects.isNull(requestNumber)){
            requestNumber = 1;
        }else {
            requestNumber ++;
        }

        certificateRequest.setRequestNumber(requestNumber);
        certificateRequest.setUser(userOptional.get());
        certificateRequest.setSecretary(secretaryOptional.get());

        return modelMapper.map(repository.save(certificateRequest), CreateCertificateRequestDto.class);
    }

    public CertificateRequest getCertificateRequestById(Integer id) {
        return repository.findById(id).orElseThrow(BadRequestException::new);
    }

    public CertificateRequestBySecretaryDto updateCertificateRequestApprovalStatus(Integer id, Status status) {
        return repository.findById(id)
            .map(certificateRequest -> {
                certificateRequest.setStatus(status);
//                if(Status.APPROVED.equals(status)){
//                    emailService.sendEmail("cosmin.maruneac@student.usv.ro",
//                        "Adeverința ta este gata. Te rugăm să o ridici din fața secretariatului.",
//                        "Adeverința ta este gata");
//                }
                repository.save(certificateRequest);
                return modelMapper.map(certificateRequest, CertificateRequestBySecretaryDto.class);
            })
            .orElseThrow(EntityNotFoundException::new);
    }

    public List<CertificateRequestBySecretaryDto> getAllCertificateRequestsBySecretaryAssigned(UUID secretaryId) {

        return repository.findAllBySecretaryId(secretaryId).stream()
            .map(cr -> modelMapper.map(cr, CertificateRequestBySecretaryDto.class)).toList();
    }

    public List<StudentCertificateRequestViewDto> getAllCertificateRequestsByStudent(UUID studentId) {

        return repository.findAllByUserId(studentId).stream()
            .map(cr -> modelMapper.map(cr, StudentCertificateRequestViewDto.class)).toList();
    }

    public String generateCertificatePdf(Integer certificateRequestId) {

        Optional<CertificateRequest> byId = repository.findById(certificateRequestId);

        if (byId.isEmpty()) {
            throw new BadRequestException("This certificate request id does not exist!");
        }

        CertificateRequest certificateRequest = byId.get();


        if (!Status.APPROVED.equals(certificateRequest.getStatus())) {
            throw new BadRequestException("This certificate request is not approved!");
        }

        Integer registrationNumber = byId.get().getUser().getStudyProgram().getRegistrationNumber();

        if (Objects.isNull(registrationNumber)) {
            throw new BadRequestException("Please set a registrationNumber");
        }

        StudyProgram studyProgram = studyProgramRepository.findByName(certificateRequest.getUser().getStudyProgram().getName());

        AcademicYearDto academicYear = academicYearService.getCurrentAcademicYear();


        String pdfUrl = pdfGeneratorService.generatePdf(certificateRequest.getUser().getFullName(),
            certificateRequest.getUser().getStudyYear(),
            certificateRequest.getUser().getStudyProgram().getName(),
            certificateRequest.getReason(),
            certificateRequest.getRequestNumber(),
            LocalDate.now(),
            academicYearService.getCurrentAcademicYear().getCurrentAcademicYear(),
            certificateRequest.getUser().getDomain().getName(),
            certificateRequest.getUser().getFinancialStatus(),
            registrationNumber,
            academicYear.getChiefSecretaryName(),
            academicYear.getDeanName(),
            studyProgram.getSecretary().getFullName());

        GeneratedCertificateDto dto = GeneratedCertificateDto.builder()
            .reason(certificateRequest.getReason())
            .fieldOfStudy(certificateRequest.getUser().getDomain().getName() + " " + certificateRequest.getUser().getStudyProgram().getName())
            .financialStatus(certificateRequest.getUser().getFinancialStatus().name())
            .registrationDate(LocalDate.now())
            .studentFirstName(certificateRequest.getUser().getFirstName())
            .studentLastName(certificateRequest.getUser().getLastName())
            .yearOfStudy(certificateRequest.getUser().getStudyYear().toString())
            .registrationNumber("42")
            .build();

        generatedCertificateService.create(dto);

        return pdfUrl;
    }

    public CertificateRequestReportDto getStatusForCurrentMonth() {
        Integer createdThisMonth = repository.countCreatedThisMonth();
        Integer acceptedThisMonth = repository.countAcceptedThisMonth();
        Integer refusedThisMonth = repository.countRefusedThisMonth();

        return CertificateRequestReportDto.builder()
            .createdThisMonth(createdThisMonth)
            .acceptedThisMonth(acceptedThisMonth)
            .refusedThisMonth(refusedThisMonth)
            .build();
    }
}
