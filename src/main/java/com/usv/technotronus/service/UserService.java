package com.usv.technotronus.service;

import com.usv.technotronus.entities.User;
import com.usv.technotronus.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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
}
