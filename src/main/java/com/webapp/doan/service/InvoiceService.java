package com.webapp.doan.service;

import com.webapp.doan.dto.InvoiceDto;
import com.webapp.doan.exceptions.EtAuthException;
import com.webapp.doan.exceptions.EtBadRequestException;
import com.webapp.doan.exceptions.EtResourceNotFoundException;
import com.webapp.doan.model.DetailInvoice;
import com.webapp.doan.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface InvoiceService {

    public Invoice createInvoice(InvoiceDto invoiceDto) throws EtBadRequestException;

    public Page<Invoice> findAllInvoices(int page, int size) throws EtResourceNotFoundException;

    public Invoice findInvoiceById(Integer id) throws  EtResourceNotFoundException;

    public List<DetailInvoice> findDetailInvoice(Invoice invoice) throws EtResourceNotFoundException;

    public Invoice editInvoiceStatusById(int invoiceId, String status) throws EtAuthException;

}
