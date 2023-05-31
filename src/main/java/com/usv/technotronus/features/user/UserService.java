package com.usv.technotronus.features.user;

import com.usv.technotronus.features.exceptions.BadRequestException;
import com.usv.technotronus.features.user.dto.UserDto;
import com.usv.technotronus.features.user.dto.UserViewDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
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

    public List<UserViewDto> getUsersByDomain(Integer domainId) {

        return userRepository.findUserByDomainId(domainId).stream()
            .map(student -> modelMapper.map(student, UserViewDto.class))
            .toList();
    }
}
