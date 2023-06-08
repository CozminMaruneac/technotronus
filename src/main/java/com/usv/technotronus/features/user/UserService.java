package com.usv.technotronus.features.user;

import com.usv.technotronus.features.exceptions.BadRequestException;
import com.usv.technotronus.features.user.dto.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.usv.technotronus.utilities.CurrentUserLogged.getLoggedUserEmail;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @PostConstruct
    public void postConstruct() {
        modelMapper.addMappings(Utils.userUserViewDtoPropertyMap);
        modelMapper.addMappings(Utils.secretaryDtoPropertyMap);
    }


    public User processUser(String email, String givenName, String familyName, String picture) {

        return userRepository.findUserByEmailContainsIgnoreCase(email)
            .orElseGet(() -> {
                User user = User.builder()
                    .email(email)
                    .firstName(givenName)
                    .lastName(familyName)
                    .profileImageUrl(picture)
                    .role(UserRole.USER)
                    .build();

                userRepository.save(user);
                return user;
            });
    }

    public UserDto getById(UUID userId) {

        return userRepository.findById(userId)
            .map(user -> modelMapper.map(user, UserDto.class))
            .orElseThrow(EntityNotFoundException::new);
    }

    public UserDto getCurrentUser() {
        Optional<User> user = userRepository.findUserByEmailContainsIgnoreCase(getLoggedUserEmail());
        if (!user.isPresent()) {
            throw new BadRequestException();
        }
        return modelMapper.map(user.get(), UserDto.class);

    }

    public List<UserDto> createSecretariesAccounts(List<UserDto> users) {

        List<User> mappedUsers = users.stream()
            .map(userDto -> {

                User secretary = modelMapper.map(userDto, User.class);
                secretary.setRole(UserRole.SECRETARY);
                return secretary;
            })
            .toList();

        userRepository.saveAll(mappedUsers);

        return mappedUsers.stream()
            .map(user -> modelMapper.map(user, UserDto.class))
            .toList();

    }

    public List<UserViewDto> getUsersByDomain(Integer domainId) {

        return userRepository.findUserByDomainId(domainId).stream()
            .map(student -> modelMapper.map(student, UserViewDto.class))
            .toList();
    }


    public StudyProgramReportDto getStudyProgramReportByDomain(Long domainId) {
        StudyProgramReportDto reportDto = new StudyProgramReportDto();

        List<TotalStudentPerStudyProgramResult> results = userRepository.getUserNumberPerStudyProgram(domainId);
        reportDto.setStudentPerStudyProgram(convertToDto(results));

        Integer totalStudents = userRepository.getTotalStudentsByDomain(domainId);
        reportDto.setTotalStudents(totalStudents);

        return reportDto;
    }

    public List<TotalStudentPerStudyProgramDto> convertToDto(List<TotalStudentPerStudyProgramResult> results) {
        List<TotalStudentPerStudyProgramDto> dtos = new ArrayList<>();
        for (TotalStudentPerStudyProgramResult result : results) {
            TotalStudentPerStudyProgramDto dto = new TotalStudentPerStudyProgramDto();
            dto.setStudyProgramName(result.getStudyProgramName());
            dto.setStudentsNumber(result.getStudentsNumber());
            dtos.add(dto);
        }
        return dtos;
    }

    public List<SecretaryDto> getSecretaries() {

        return userRepository.findUsersByRole(UserRole.SECRETARY)
            .stream()
            .map(user -> modelMapper.map(user, SecretaryDto.class))
            .toList();
    }
}
