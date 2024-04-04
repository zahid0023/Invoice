package com.example.ghuddyinvoice;

import com.example.ghuddyinvoice.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping(path = "/api/v1/open")
public class InvoicePdfController {
    private final InvoiceService invoiceService;

    public InvoicePdfController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @RequestMapping(path = "/invoice/generate", method = RequestMethod.POST)
    public ResponseEntity<?> generatePdf() throws FileNotFoundException {
        invoiceService.generateInvoice();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
