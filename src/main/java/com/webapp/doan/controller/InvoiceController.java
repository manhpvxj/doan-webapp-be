package com.webapp.doan.controller;

import com.webapp.doan.model.DetailInvoice;
import com.webapp.doan.model.Invoice;
import com.webapp.doan.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> FindById(@PathVariable("id") Integer id) {
        Invoice invoice = invoiceService.findInvoiceById(id);
        List<DetailInvoice> detailInvoices = invoiceService.findDetailInvoice(invoice);
        Map<String, Object> map1 = new HashMap<>();
        Map<String,Object> map = new HashMap<>();
        map1.put("invoice", invoice);
        map1.put("detail", detailInvoices);
        map.put("data", map1);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
