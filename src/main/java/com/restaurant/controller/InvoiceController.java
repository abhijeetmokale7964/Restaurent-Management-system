package com.restaurant.controller;

import com.restaurant.service.InvoiceService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoice")
@RequiredArgsConstructor
@Slf4j
public class InvoiceController {

    private final InvoiceService invoiceService;

    // download invoice pdf by orderId
    @GetMapping("/{orderId}")
    public ResponseEntity<InputStreamResource> downloadInvoice(@PathVariable Long orderId) {

        log.info("Request received to download invoice for orderId: {}", orderId);

        try {

            InputStreamResource file = new InputStreamResource(
                    invoiceService.generateInvoice(orderId)
            );

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=invoice_" + orderId + ".pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(file);

        } catch (Exception e) {

            log.error("Error while downloading invoice for orderId: {}", orderId, e);

            return ResponseEntity.internalServerError().build();
        }
    }
}