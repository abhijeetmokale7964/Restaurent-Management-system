package com.restaurant.serviceImpl;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.restaurant.entity.Bill;
import com.restaurant.entity.OrderItem;
import com.restaurant.repository.BillRepository;
import com.restaurant.repository.OrderItemRepository;
import com.restaurant.service.InvoiceService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {

    private final BillRepository billRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public ByteArrayInputStream generateInvoice(Long orderId) {

        log.info("Generating invoice for orderId: {}", orderId);

        // fetch bill
        Bill bill = billRepository.findByOrderId(orderId);

        if (bill == null) {
            log.error("Bill not found for orderId: {}", orderId);
            throw new RuntimeException("Bill not found for orderId: " + orderId);
        }

        // fetch order items
        List<OrderItem> items = orderItemRepository.findByOrderId(orderId);

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);
            document.open();

            // ===== Title =====
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Restaurant Invoice", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Order ID: " + orderId));
            document.add(new Paragraph(" "));

            // ===== Table =====
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10);

            // header
            table.addCell("Item");
            table.addCell("Price");
            table.addCell("Qty");
            table.addCell("Total");

            // data
            for (OrderItem item : items) {

                double total = item.getPrice() * item.getQuantity();

                table.addCell(String.valueOf(item.getMenuItemId())); // better: item.getMenuItem().getName()
                table.addCell(String.valueOf(item.getPrice()));
                table.addCell(String.valueOf(item.getQuantity()));
                table.addCell(String.valueOf(total));
            }

            document.add(table);

            // ===== Bill Summary =====
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Total Amount: " + bill.getTotalAmount()));
            document.add(new Paragraph("Tax: " + bill.getTax()));
            document.add(new Paragraph("Discount: " + bill.getDiscount()));
            document.add(new Paragraph("Final Amount: " + bill.getFinalAmount()));
            document.add(new Paragraph("Payment Status: " + bill.getPaymentStatus()));

            document.close();

            log.info("Invoice generated successfully for orderId: {}", orderId);

        } catch (Exception e) {
            log.error("Error while generating invoice for orderId: {}", orderId, e);
            throw new RuntimeException("Error generating invoice");
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}