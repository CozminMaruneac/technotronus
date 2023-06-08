package com.usv.technotronus.features.user;

import com.usv.technotronus.features.user.dto.SecretaryDto;
import com.usv.technotronus.features.user.dto.StudyProgramReportDto;
import com.usv.technotronus.features.user.dto.UserDto;
import com.usv.technotronus.features.user.dto.UserViewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserImporter userImporter;

    @PostMapping("/bulk")
    public List<UserDto> createSecretariesAccounts(@RequestBody List<UserDto> users) {
        return userService.createSecretariesAccounts(users);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable(name = "id") UUID userId) {
        return userService.getById(userId);

    }

    @GetMapping("/current")
    public UserDto getCurrentUser() {
        return userService.getCurrentUser();
    }

    @GetMapping("/secretaries")
    public List<SecretaryDto> getSecretaries() {
        return userService.getSecretaries();
    }

    @GetMapping("/domain/{domainId}")
    public List<UserViewDto> getUsersByDomain(@PathVariable Integer domainId) {

        return userService.getUsersByDomain(domainId);
    }

    @PostMapping("/import")
    public void importUser(@RequestPart MultipartFile students) {

        userImporter.importUsersFromExcel(students);
    }

    @GetMapping("/report")
    public StudyProgramReportDto getStudyProgramReportByDomain(@RequestParam Long domainId) {
        return userService.getStudyProgramReportByDomain(domainId);
    }
}
