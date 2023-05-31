package com.usv.technotronus.features.certificate_request;

import com.usv.technotronus.features.certificate_request.dto.CertificateRequestBySecretaryDto;
import com.usv.technotronus.features.certificate_request.dto.CreateCertificateRequestDto;
import com.usv.technotronus.features.certificate_request.dto.StudentCertificateRequestViewDto;
import com.usv.technotronus.features.exceptions.BadRequestException;
import com.usv.technotronus.features.user.User;
import com.usv.technotronus.features.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CertificateRequestService {

    private final ModelMapper modelMapper;

    private final CertificateRequestRepository repository;
    private final UserRepository userRepository;

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
}