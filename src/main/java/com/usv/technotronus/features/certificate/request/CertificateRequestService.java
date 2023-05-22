package com.usv.technotronus.features.certificate.request;

import com.usv.technotronus.features.certificate.request.dto.CreateCertificateRequestDto;
import com.usv.technotronus.features.exceptions.BadRequestException;
import com.usv.technotronus.features.user.User;
import com.usv.technotronus.features.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CertificateRequestService {

    private final ModelMapper modelMapper;

    private final CertificateRequestRepository repository;
    private final UserRepository userRepository;

    @PostConstruct
    public void postConstruct() {
        modelMapper.addMappings(Utils.createCertificateRequestDtoPropertyMap);
    }

    public CreateCertificateRequestDto createCertificateRequest(CreateCertificateRequestDto requestDto) {

        CertificateRequest certificateRequest = modelMapper.map(requestDto, CertificateRequest.class);
        Optional<User> userOptional = userRepository.findById(requestDto.getUserId());
        if (userOptional.isEmpty()) {
            throw new BadRequestException();
        }
        certificateRequest.setUser(userOptional.get());

        return modelMapper.map(repository.save(certificateRequest), CreateCertificateRequestDto.class);
    }

    public CertificateRequest getCertificateRequestById(Integer id) {
        return repository.findById(id).orElseThrow(BadRequestException::new);
    }

    public CertificateRequest updateCertificateRequestApprovalStatus(Integer id, Boolean isApproved) {
        return repository.findById(id)
            .map(certificateRequest -> modelMapper.map(certificateRequest, CertificateRequest.class))
            .map(certificateRequest -> {
                certificateRequest.setIsApproved(isApproved);
                repository.save(certificateRequest);
                return certificateRequest;
            })
            .orElseThrow(EntityNotFoundException::new);
    }
}
