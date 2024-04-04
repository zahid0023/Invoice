package com.example.ghuddyinvoice.serviceImpl;

import com.example.ghuddyinvoice.AvailableAccommodationPackageData;
import com.example.ghuddyinvoice.AvailableMealPackageData;
import com.example.ghuddyinvoice.AvailableTransferPackageData;
import com.example.ghuddyinvoice.Util;
import com.example.ghuddyinvoice.service.InvoiceService;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private static final String NEW_LINE = "\n";

    @Override
    public void generateInvoice() throws FileNotFoundException {
        String path = "invoice.pdf";
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDocument);

        document.add(getHeaderTable(new float[]{500, 250}));
        document.add(getBillingInfoTable(new float[]{450, 400}, "Root", "address", "1234", "root@gmail.com", BigDecimal.valueOf(1000), BigDecimal.valueOf(1000), BigDecimal.valueOf(0), LocalDate.now().toString(), LocalDate.now().toString(), "470q834q904"));
        document.add(getProductDescriptionTable(new float[]{750}, LocalDate.now().toString(), LocalDate.now().toString(), "description"));

        document.add(getAccommodationTable(new float[]{750}, Util.accommodation()));
        document.add(new AreaBreak());
        document.add(getFoodTable(new float[]{750}, Util.food()));
        document.add(getTransferTable(new float[]{750}, Util.accommodation()));
        document.add(new AreaBreak());
        document.add(getTransportationTable(new float[]{750}, Util.accommodation()));
        document.add(getGuideTable(new float[]{750}, Util.accommodation()));
        document.add(new AreaBreak());
        document.add(getSpotEntryTable(new float[]{750}, Util.accommodation()));
        document.close();

    }

    private Table getHeaderTable(float[] columWidths) {
        Table headerTable = new Table(columWidths);

        Cell logoCell = new Cell();
        logoCell.add("Invoice").setBold().setFontSize(50);
        logoCell.setBorder(Border.NO_BORDER);

        headerTable.addCell(logoCell);

        Cell companyInfoCell = new Cell();

        Paragraph companyInfoParagraph = new Paragraph();

        String companyName = "Ghuddy Limited\n";
        companyInfoParagraph.add(companyName);
        String contactNumber = "01385438555\n";
        companyInfoParagraph.add(contactNumber);
        String contactEmail = "support@ghuddy.com\n";
        companyInfoParagraph.add(contactEmail);

        companyInfoCell.add(companyInfoParagraph);
        companyInfoCell.setBorder(Border.NO_BORDER);

        headerTable.addCell(companyInfoCell);
        headerTable.setBorderBottom(new SolidBorder(Color.BLACK, 2));

        return headerTable;
    }

    private Table getBillingInfoTable(float[] columnWidths, String userName, String userAddress, String userContactNumber, String userEmailAddress,
                                      BigDecimal totalBill, BigDecimal paidBill, BigDecimal dueBill, String bookedDate, String invoiceDate, String invoiceCode) {
        Table billingInfoTable = new Table(columnWidths);
        billingInfoTable.setMarginTop(20);
        billingInfoTable.setBorder(Border.NO_BORDER);
        billingInfoTable.setBorderBottom(new SolidBorder(Color.BLACK, 2));

        Cell billingInfoCell = new Cell();
        billingInfoCell.setBorder(Border.NO_BORDER);
        StringBuilder stringBuilder = new StringBuilder();

        String newLine = "\n";
        Paragraph billingInfoBold = new Paragraph("Billing Information").setBold().setFontSize(20);
        billingInfoCell.add(billingInfoBold);
        billingInfoCell.add(newLine);

        stringBuilder.append(userName).append(newLine).append(userAddress).append(newLine).append(userContactNumber).append(newLine).append(userEmailAddress).append(newLine);
        billingInfoCell.add(new Paragraph(stringBuilder.toString()).setMarginBottom(10));

        billingInfoTable.addCell(billingInfoCell);

        Cell paymentSummaryCell = new Cell();
        Paragraph paymentSummary = new Paragraph("Reservation Confirmation").setBold().setFontSize(20);
        paymentSummaryCell.add(paymentSummary);
        paymentSummaryCell.add(newLine);
        StringBuilder paymentSummaryString = new StringBuilder();
        paymentSummaryString
                .append("Booked Date: ")
                .append(bookedDate)
                .append(newLine)
                .append("Invoice Date: ")
                .append(invoiceDate)
                .append(newLine)
                .append("Invoice Number: ")
                .append(invoiceCode)
                .append(newLine)
                .append("Total Amount: ")
                .append(totalBill)
                .append(newLine)
                .append("Paid Amount: ")
                .append(paidBill)
                .append(newLine)
                .append("Due Amount: ")
                .append(dueBill);
        paymentSummaryCell.add(new Paragraph(paymentSummaryString.toString()).setMarginBottom(10)).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.RIGHT);

        billingInfoTable.addCell(paymentSummaryCell);

        return billingInfoTable;
    }

    private Table getProductDescriptionTable(float[] columSizes, String tourStartDate, String tourReportingTime, String tourDescription) {
        Table tourDescriptionTable = new Table(columSizes).setBorder(Border.NO_BORDER);

        Cell tourDescriptionTitleCell = new Cell().setBorder(Border.NO_BORDER);
        tourDescriptionTitleCell
                .add(new Paragraph("Tour Summary").setPaddingTop(10).setTextAlignment(TextAlignment.CENTER)).setBold().setFontSize(20).setMarginBottom(5);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Tour Start Date: ")
                .append(tourStartDate)
                .append(NEW_LINE)
                .append("Tour Reporting Time: ")
                .append(tourReportingTime)
                .append(NEW_LINE)
                .append("Tour Description: ")
                .append(tourDescription)
                .append(NEW_LINE);

        Cell tourDescriptionCell = new Cell().setBorder(Border.NO_BORDER).setMarginBottom(10).setMarginTop(10);
        tourDescriptionCell.add(stringBuilder.toString());

        tourDescriptionTable.addCell(tourDescriptionTitleCell);
        tourDescriptionTable.addCell(tourDescriptionCell);
        tourDescriptionTable.setBorderBottom(new SolidBorder(Color.BLACK, 2)).setMarginBottom(10);

        return tourDescriptionTable;
    }

    private Table getAccommodationTable(float[] columnWidths, List<AvailableAccommodationPackageData> availableAccommodationPackageDataList) {
        Collections.sort(availableAccommodationPackageDataList, Comparator.comparing(AvailableAccommodationPackageData::getNightNumber));

        Table accommodationTable = new Table(columnWidths).setBorder(Border.NO_BORDER);
        Cell accommodationTableTitle = new Cell().setBorder(Border.NO_BORDER);
        accommodationTableTitle.add(new Paragraph("Accommodation").setBold().setFontSize(16)).setPaddingBottom(10).setFontColor(Color.BLUE);
        accommodationTable.addCell(accommodationTableTitle);

        Cell accommodationTableContent = new Cell().setBorder(Border.NO_BORDER);
        Table accommodationContentTable = new Table(new float[]{250, 250, 250});

        // Add headers
        accommodationContentTable.addCell(new Cell().add("Night Number").setBold().setFontSize(14).setBackgroundColor(Color.GRAY));
        accommodationContentTable.addCell(new Cell().add("Accommodation Name").setBold().setFontSize(14).setBackgroundColor(Color.GRAY));
        accommodationContentTable.addCell(new Cell().add("Room Description").setBold().setFontSize(14).setBackgroundColor(Color.GRAY));

        // Add dynamic rows
        availableAccommodationPackageDataList.forEach(availableAccommodationPackageData -> {
            accommodationContentTable.addCell(new Cell().add(availableAccommodationPackageData.getNightNumber().toString()));
            accommodationContentTable.addCell(new Cell().add(availableAccommodationPackageData.getTourPackageAccommodationName()));
            accommodationContentTable.addCell(new Cell().add(availableAccommodationPackageData.getRoomDescription()));
        });

        accommodationTableContent.add(accommodationContentTable);
        accommodationTable.addCell(accommodationTableContent);

        return accommodationTable;
    }

    private Table getFoodTable(float[] columnWidths, List<AvailableMealPackageData> availableMealPackageDataList) {
        // Create a TreeMap with key as dayNumber and value as a List of AvailableMealPackageData
        TreeMap<Integer, List<AvailableMealPackageData>> treeMap = availableMealPackageDataList.stream()
                .collect(Collectors.groupingBy(AvailableMealPackageData::getDayNumber,
                        TreeMap::new,
                        Collectors.toList()));


        Table foodTable = new Table(columnWidths).setBorder(Border.NO_BORDER);
        foodTable.addCell(titleCell("Food"));

        Cell foodTableContent = new Cell().setBorder(Border.NO_BORDER);

        Table foodContentTable = new Table(new float[]{187.5f, 187.5f, 187.5f, 187.5f});

        // Add headers
        foodContentTable.addCell(new Cell().add("Day Number").setBold().setFontSize(14).setBackgroundColor(Color.GRAY));
        foodContentTable.addCell(new Cell().add("Breakfast").setBold().setFontSize(14).setBackgroundColor(Color.GRAY));
        foodContentTable.addCell(new Cell().add("Lunch").setBold().setFontSize(14).setBackgroundColor(Color.GRAY));
        foodContentTable.addCell(new Cell().add("Dinner").setBold().setFontSize(14).setBackgroundColor(Color.GRAY));

        // Add dynamic rows
        treeMap.forEach((day, availableMealPackageDataList2) -> {
            availableMealPackageDataList2.stream().forEach(availableMealPackageData -> {
                foodContentTable.addCell(new Cell().add(day.toString()));
                foodContentTable.addCell(new Cell().add(
                        availableMealPackageData.getMealTypeName().toLowerCase().equals("breakfast") ? new Paragraph(availableMealPackageData.toFoodItems()) : new Paragraph("Not Included")
                ));
                foodContentTable.addCell(new Cell().add(
                        availableMealPackageData.getMealTypeName().toLowerCase().equals("lunch") ? new Paragraph(availableMealPackageData.toFoodItems()) : new Paragraph("Not Included")
                ));
                foodContentTable.addCell(new Cell().add(
                        availableMealPackageData.getMealTypeName().toLowerCase().equals("dinner") ? new Paragraph(availableMealPackageData.toFoodItems()) : new Paragraph("Not Included")
                ));

            });
        });

        foodTableContent.add(foodContentTable);
        foodTable.addCell(foodTableContent);

        return foodTable;
    }

    private Cell titleCell(String title) {
        Cell tableTitle = new Cell().setBorder(Border.NO_BORDER);
        tableTitle.add(new Paragraph(title).setBold().setFontSize(16)).setPaddingBottom(10).setFontColor(Color.BLUE);
        return tableTitle;
    }

    public void addHeaders(Table table, List<String> headers) {
        for (String header : headers) {
            table.addCell(new Cell().add(header).setBold().setFontSize(14).setBackgroundColor(Color.GRAY));
        }
    }

    private Table getTransferTable(float[] columnWidths, List<AvailableTransferPackageData> availableAccommodationPackageDataList) {

        Table transferTable = new Table(columnWidths).setBorder(Border.NO_BORDER);
        Cell transferTableTitle = titleCell("Transfer");
        transferTable.addCell(transferTableTitle);


        Cell transferTableContent = new Cell().setBorder(Border.NO_BORDER);
        Table transferContentTable = new Table(new float[]{250, 250, 250});

        // Add headers
       addHeaders(transferContentTable,List.of("Route","Trip Type","AC","Description"));
        // Add dynamic rows
        availableAccommodationPackageDataList.forEach(availableAccommodationPackageData -> {
            accommodationContentTable.addCell(new Cell().add(availableAccommodationPackageData.getNightNumber().toString()));
            accommodationContentTable.addCell(new Cell().add(availableAccommodationPackageData.getTourPackageAccommodationName()));
            accommodationContentTable.addCell(new Cell().add(availableAccommodationPackageData.getRoomDescription()));
        });

        accommodationTableContent.add(accommodationContentTable);
        accommodationTable.addCell(accommodationTableContent);

        return accommodationTable;
    }

    private Table getTransportationTable(float[] columnWidths, List<AvailableAccommodationPackageData> availableAccommodationPackageDataList) {
        Collections.sort(availableAccommodationPackageDataList, Comparator.comparing(AvailableAccommodationPackageData::getNightNumber));

        Table accommodationTable = new Table(columnWidths).setBorder(Border.NO_BORDER);
        Cell accommodationTableTitle = new Cell().setBorder(Border.NO_BORDER);
        accommodationTableTitle.add(new Paragraph("Transportation").setBold().setFontSize(16)).setPaddingBottom(10).setFontColor(Color.BLUE);
        accommodationTable.addCell(accommodationTableTitle);

        Cell accommodationTableContent = new Cell().setBorder(Border.NO_BORDER);
        Table accommodationContentTable = new Table(new float[]{250, 250, 250});

        // Add headers
        accommodationContentTable.addCell(new Cell().add("Night Number").setBold().setFontSize(14).setBackgroundColor(Color.GRAY));
        accommodationContentTable.addCell(new Cell().add("Accommodation Name").setBold().setFontSize(14).setBackgroundColor(Color.GRAY));
        accommodationContentTable.addCell(new Cell().add("Room Description").setBold().setFontSize(14).setBackgroundColor(Color.GRAY));

        // Add dynamic rows
        availableAccommodationPackageDataList.forEach(availableAccommodationPackageData -> {
            accommodationContentTable.addCell(new Cell().add(availableAccommodationPackageData.getNightNumber().toString()));
            accommodationContentTable.addCell(new Cell().add(availableAccommodationPackageData.getTourPackageAccommodationName()));
            accommodationContentTable.addCell(new Cell().add(availableAccommodationPackageData.getRoomDescription()));
        });

        accommodationTableContent.add(accommodationContentTable);
        accommodationTable.addCell(accommodationTableContent);

        return accommodationTable;
    }

    private Table getGuideTable(float[] columnWidths, List<AvailableAccommodationPackageData> availableAccommodationPackageDataList) {
        Collections.sort(availableAccommodationPackageDataList, Comparator.comparing(AvailableAccommodationPackageData::getNightNumber));

        Table accommodationTable = new Table(columnWidths).setBorder(Border.NO_BORDER);
        Cell accommodationTableTitle = new Cell().setBorder(Border.NO_BORDER);
        accommodationTableTitle.add(new Paragraph("Guide").setBold().setFontSize(16)).setPaddingBottom(10).setFontColor(Color.BLUE);
        accommodationTable.addCell(accommodationTableTitle);

        Cell accommodationTableContent = new Cell().setBorder(Border.NO_BORDER);
        Table accommodationContentTable = new Table(new float[]{250, 250, 250});

        // Add headers
        accommodationContentTable.addCell(new Cell().add("Night Number").setBold().setFontSize(14).setBackgroundColor(Color.GRAY));
        accommodationContentTable.addCell(new Cell().add("Accommodation Name").setBold().setFontSize(14).setBackgroundColor(Color.GRAY));
        accommodationContentTable.addCell(new Cell().add("Room Description").setBold().setFontSize(14).setBackgroundColor(Color.GRAY));

        // Add dynamic rows
        availableAccommodationPackageDataList.forEach(availableAccommodationPackageData -> {
            accommodationContentTable.addCell(new Cell().add(availableAccommodationPackageData.getNightNumber().toString()));
            accommodationContentTable.addCell(new Cell().add(availableAccommodationPackageData.getTourPackageAccommodationName()));
            accommodationContentTable.addCell(new Cell().add(availableAccommodationPackageData.getRoomDescription()));
        });

        accommodationTableContent.add(accommodationContentTable);
        accommodationTable.addCell(accommodationTableContent);

        return accommodationTable;
    }

    private Table getSpotEntryTable(float[] columnWidths, List<AvailableAccommodationPackageData> availableAccommodationPackageDataList) {
        Collections.sort(availableAccommodationPackageDataList, Comparator.comparing(AvailableAccommodationPackageData::getNightNumber));

        Table accommodationTable = new Table(columnWidths).setBorder(Border.NO_BORDER);
        Cell accommodationTableTitle = new Cell().setBorder(Border.NO_BORDER);
        accommodationTableTitle.add(new Paragraph("Spot Entry").setBold().setFontSize(16)).setPaddingBottom(10).setFontColor(Color.BLUE);
        accommodationTable.addCell(accommodationTableTitle);

        Cell accommodationTableContent = new Cell().setBorder(Border.NO_BORDER);
        Table accommodationContentTable = new Table(new float[]{250, 250, 250});

        // Add headers
        accommodationContentTable.addCell(new Cell().add("Night Number").setBold().setFontSize(14).setBackgroundColor(Color.GRAY));
        accommodationContentTable.addCell(new Cell().add("Accommodation Name").setBold().setFontSize(14).setBackgroundColor(Color.GRAY));
        accommodationContentTable.addCell(new Cell().add("Room Description").setBold().setFontSize(14).setBackgroundColor(Color.GRAY));

        // Add dynamic rows
        availableAccommodationPackageDataList.forEach(availableAccommodationPackageData -> {
            accommodationContentTable.addCell(new Cell().add(availableAccommodationPackageData.getNightNumber().toString()));
            accommodationContentTable.addCell(new Cell().add(availableAccommodationPackageData.getTourPackageAccommodationName()));
            accommodationContentTable.addCell(new Cell().add(availableAccommodationPackageData.getRoomDescription()));
        });

        accommodationTableContent.add(accommodationContentTable);
        accommodationTable.addCell(accommodationTableContent);

        return accommodationTable;
    }
}
