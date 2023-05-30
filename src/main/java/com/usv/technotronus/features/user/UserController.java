package com.usv.technotronus.features.user;

import com.usv.technotronus.features.pdf_generator.PdfGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PdfGeneratorService pdfGeneratorService;
    private final UserImporter userImporter;

    @PostMapping("/bulk")
    public List<UserDto> createUsers(@RequestBody List<UserDto> users) {
        return userService.createUsersBulk(users);

    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable(name = "id") UUID userId) {
        return userService.getById(userId);

    }

    @GetMapping("/current")
    public UserDto getCurrentUser() {
        return userService.getCurrentUser();
    }

    @GetMapping("")
    public List<UserDto> getUsers() {

        return userService.getUsers();
    }

    @PostMapping("/generatePdf")
    public void generatePdf() {

        pdfGeneratorService.generatePdf("Cosmin", 2023, "program", "de aia", 42, LocalDate.now(), "Test", "Test", "IF", "fara taxa");
    }

    @PostMapping("/import")
    public void importUser(@RequestPart MultipartFile students) {

        userImporter.importUsersFromExcel(students);
    }
}
