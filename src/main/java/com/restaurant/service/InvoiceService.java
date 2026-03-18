package com.restaurant.service;

import java.io.ByteArrayInputStream;

public interface InvoiceService {

    // generate invoice pdf for given orderId
    ByteArrayInputStream generateInvoice(Long orderId);
}