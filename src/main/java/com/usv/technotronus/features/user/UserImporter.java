package com.usv.technotronus.features.user;

import com.usv.technotronus.features.domain.DomainRepository;
import com.usv.technotronus.features.study_program.StudyProgramRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserImporter {

    private final StudyProgramRepository studyProgramRepository;
    private final DomainRepository domainRepository;
    private final UserRepository userRepository;

    public List<User> importUsersFromExcel(MultipartFile multipartFile) {
        List<User> users = new ArrayList<>();

        try (InputStream inputStream = multipartFile.getInputStream();
             Workbook workbook = WorkbookFactory.create(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // Assuming the header is in the first row
            Row headerRow = rowIterator.next();
            int emailIndex = getColumnIndex(headerRow, "Adresă de email");
            int lastNameIndex = getColumnIndex(headerRow, "Nume de familie");
            int fatherInitialIndex = getColumnIndex(headerRow, "Inițiala tatălui");
            int firstNameIndex = getColumnIndex(headerRow, "Prenume");
            int studyDomainIndex = getColumnIndex(headerRow, "Domeniu de studii");
            int studyProgramIndex = getColumnIndex(headerRow, "Program de studii");
            int studyYearIndex = getColumnIndex(headerRow, "An studii");
            int financialStatusIndex = getColumnIndex(headerRow, "Statut financiar");

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String email = getCellValueAsString(row.getCell(emailIndex));
                String lastName = getCellValueAsString(row.getCell(lastNameIndex));
                String fatherInitial = getCellValueAsString(row.getCell(fatherInitialIndex));
                String firstName = getCellValueAsString(row.getCell(firstNameIndex));
                String studyDomain = getCellValueAsString(row.getCell(studyDomainIndex));
                String studyProgram = getCellValueAsString(row.getCell(studyProgramIndex));
                int studyYear = getCellValueAsInt(row.getCell(studyYearIndex));
                String financialStatus = getCellValueAsString(row.getCell(financialStatusIndex));

                User user = User.builder()
                    .role(UserRole.USER)
                    .email(email)
                    .firstName(firstName)
                    .lastName(lastName)
                    .studyProgram(studyProgramRepository.findByName(studyProgram))
                    .fatherInitial(fatherInitial)
                    .domain(domainRepository.findByName(studyDomain))
                    .studyYear(studyYear)
                    .financialStatus(FinancialStatus.getByStatusName(financialStatus))
                    .build();
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        userRepository.saveAll(users);
        return users;
    }

    private int getColumnIndex(Row headerRow, String columnName) {
        Iterator<Cell> cellIterator = headerRow.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            if (columnName.equalsIgnoreCase(cell.getStringCellValue().trim())) {
                return cell.getColumnIndex();
            }
        }
        return -1; // Column not found
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue().trim();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf((int) cell.getNumericCellValue());
        } else {
            return "";
        }
    }

    private int getCellValueAsInt(Cell cell) {
        if (cell == null) {
            return 0;
        }
        if (cell.getCellType() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        } else {
            return 0;
        }
    }
}


