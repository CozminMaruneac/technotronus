package com.usv.technotronus.features.excel_generator;

import com.usv.technotronus.features.generated_certificate.GeneratedCertificateDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ExcelGenerator {

    public String generateExcelFile(List<GeneratedCertificateDto> generatedCertificateDtos) {

        List<List<Object>> data = convertToExcelData(generatedCertificateDtos);

        String filePath = "src/main/resources/generated/" + UUID.randomUUID() + ".xlsx";

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Student Records");

            String[] headers = {
                "Nr. înregistrare",
                "Data înregistrării",
                "Nume student",
                "Prenume student",
                "Domeniu/program de studii",
                "An de studiu",
                "Finanțare",
                "Motiv adeverință"
            };

            Row headerRow = sheet.createRow(0);
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Auto-size the columns to fit the content
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Add data to the rows
            int rowIndex = 1;
            for (List<Object> rowData : data) {
                Row row = sheet.createRow(rowIndex++);

                int cellIndex = 0;
                for (Object value : rowData) {
                    Cell cell = row.createCell(cellIndex++);
                    if (value instanceof String) {
                        cell.setCellValue((String) value);
                    } else if (value instanceof Integer) {
                        cell.setCellValue((Integer) value);
                    } else if (value instanceof Double) {
                        cell.setCellValue((Double) value);
                    }
                }
            }

            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                workbook.write(fileOutputStream);
            }

            return filePath;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }


    public static List<List<Object>> convertToExcelData(List<GeneratedCertificateDto> certificateDtos) {
        List<List<Object>> data = new ArrayList<>();

        for (GeneratedCertificateDto certificateDto : certificateDtos) {
            List<Object> rowData = new ArrayList<>();
            rowData.add(certificateDto.getRegistrationNumber());
            rowData.add(certificateDto.getRegistrationDate().toString());
            rowData.add(certificateDto.getStudentLastName());
            rowData.add(certificateDto.getStudentFirstName());
            rowData.add(certificateDto.getFieldOfStudy());
            rowData.add(certificateDto.getYearOfStudy());
            rowData.add(certificateDto.getFinancialStatus());
            rowData.add(certificateDto.getReason());

            data.add(rowData);
        }

        return data;
    }
}
