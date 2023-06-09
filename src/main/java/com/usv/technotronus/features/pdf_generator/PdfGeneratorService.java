package com.usv.technotronus.features.pdf_generator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.usv.technotronus.features.user.FinancialStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class PdfGeneratorService {

    public String generatePdf(String studentName,
                              int year, String studyProgram,
                              String reason,
                              int number,
                              LocalDate date,
                              String currentAcademicYear,
                              String domain,
                              FinancialStatus style,
                              Integer registrationNumber,
                              String chiefSecretaryName,
                              String deanName,
                              String secretaryName) {
        Document document = new Document();
        try {
            String filePath = "src/main/resources/generated/" + UUID.randomUUID() + ".pdf";

            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // University header
            Paragraph universityHeader = new Paragraph();
            universityHeader.add(new Phrase("UNIVERSITATEA \"STEFAN CEL MARE\" DIN SUCEAVA\n", FontFactory.getFont(FontFactory.HELVETICA, 12)));
            universityHeader.add(new Phrase("FACULTATEA DE INGINERIE ELECTRICA SI STIINTA CALCULATOARELOR\n\n", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
            document.add(universityHeader);

            // Number and FIESC line
            Paragraph numberLine = new Paragraph();
            numberLine.setAlignment(Element.ALIGN_RIGHT);
            numberLine.add(new Phrase(registrationNumber + "/" + number + " /FIESC/" + date + "\n\n", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            document.add(numberLine);

            // Title
            Paragraph title = new Paragraph("A D E V E R I N T A", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            // Student information
            Paragraph studentInfo = new Paragraph();
            studentInfo.add(new Phrase("Studentul(a)" + studentName + " este inscris(a) in anul universitar " + currentAcademicYear + " in anul " + year + " de studii, program/domeniu de studii - "
                + domain + ":\n\n", FontFactory.getFont(FontFactory.HELVETICA, 12)));
            document.add(studentInfo);

            Paragraph studProgram = new Paragraph();
            studProgram.add(new Phrase("     " + studyProgram + "\n"));
            document.add(studProgram);

            // Reason
            Paragraph serveLine = new Paragraph();
            serveLine.add(new Phrase("forma de invatamant IF, regimul " + getFinancialStatusString(style) + "\n\n", FontFactory.getFont(FontFactory.HELVETICA, 12)));
            serveLine.add(new Phrase("Adeverinta se elibereaza pentru a-i servi la: " + reason + "\n\n", FontFactory.getFont(FontFactory.HELVETICA, 12)));
            document.add(serveLine);

            // Names
            PdfPTable rolesTable = new PdfPTable(3);
            rolesTable.setWidthPercentage(100);
            rolesTable.setWidths(new int[]{1, 1, 1});

            String[] names = {
                "D E C A N",
                "SECRETAR SEF",
                "SECRETARIAT"
            };

            for (String name : names) {
                PdfPCell cell = new PdfPCell();
                cell.setBorderWidth(0);
                cell.setPadding(5);
                cell.addElement(new Phrase(name, FontFactory.getFont(FontFactory.HELVETICA, 12)));
                rolesTable.addCell(cell);
            }

            document.add(rolesTable);

            PdfPTable namesTable = new PdfPTable(3);
            namesTable.setWidthPercentage(100);
            namesTable.setWidths(new int[]{1, 1, 1});
            String[] signatureRequiredNames = {
                deanName,
                chiefSecretaryName,
                secretaryName
            };

            for (String name : signatureRequiredNames) {
                PdfPCell cell = new PdfPCell();
                cell.setBorderWidth(0);
                cell.setPadding(5);
                cell.addElement(new Phrase(name, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
                namesTable.addCell(cell);
            }

            document.add(namesTable);

            document.close();
            System.out.println("PDF generated successfully at: " + filePath);
            File file = new File(filePath);
            return file.getAbsolutePath();
        } catch (Exception e) {
            System.err.println("Error generating PDF: " + e.getMessage());
        }

        return "";
    }

    private String getFinancialStatusString(FinancialStatus style) {

        return style.equals(FinancialStatus.TAX) ? "cu taxa " : "fara taxa ";
    }
}
