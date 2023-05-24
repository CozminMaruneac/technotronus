package com.usv.technotronus.features.user;

import com.usv.technotronus.features.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

    public User processUser(String email, String givenName, String familyName, String picture) {

        return userRepository.findUserByEmailContainsIgnoreCase(email)
            .orElseGet(() -> {
                User user = User.builder()
                    .email(email)
                    .firstName(givenName)
                    .lastName(familyName)
                    .profileImageUrl(picture)
                    .build();

                userRepository.save(user);
                return user;
            });
    }

    public List<UserDto> createUsersBulk(List<UserDto> users) {

        List<User> mappedUsers = users.stream()
            .map(userDto -> modelMapper.map(userDto, User.class))
            .toList();

        userRepository.saveAll(mappedUsers);

        return mappedUsers.stream()
            .map(user -> modelMapper.map(user, UserDto.class))
            .toList();

    }

    public UserDto getById(UUID userId) {

        return userRepository.findById(userId)
            .map(user -> modelMapper.map(user,UserDto.class))
            .orElseThrow(EntityNotFoundException::new);
    }

    public UserDto getCurrentUser() {
        Optional<User> user = userRepository.findUserByEmailContainsIgnoreCase(getLoggedUserEmail());
        if(!user.isPresent()){
            throw new BadRequestException();
        }
        return modelMapper.map(user.get(),UserDto.class);

    }
}
