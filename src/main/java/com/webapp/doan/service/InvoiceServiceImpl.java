package com.webapp.doan.service;

import com.webapp.doan.dto.DetailInvoiceDto;
import com.webapp.doan.dto.InvoiceDto;
import com.webapp.doan.exceptions.EtAuthException;
import com.webapp.doan.exceptions.EtBadRequestException;
import com.webapp.doan.exceptions.EtResourceNotFoundException;
import com.webapp.doan.model.DetailInvoice;
import com.webapp.doan.model.Invoice;
import com.webapp.doan.model.Product;
import com.webapp.doan.repository.DetailinvoiceRepository;
import com.webapp.doan.repository.InvoiceRepository;
import com.webapp.doan.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    InvoiceRepository invoiceRepo;
    DetailinvoiceRepository detailInvoiceRepo;

    ProductRepository productRepo;

    public InvoiceServiceImpl(DetailinvoiceRepository detailInvoiceRepo, ProductRepository productRepo, InvoiceRepository invoiceRepo) {
        this.detailInvoiceRepo = detailInvoiceRepo;
        this.productRepo = productRepo;
        this.invoiceRepo = invoiceRepo;
    }


    @Override
    public Invoice createInvoice(InvoiceDto invoiceDto) throws EtBadRequestException {
        Invoice invoice = new Invoice();
        invoice.setCreateAt(invoiceDto.getCreatedAt());
        invoice.setUpdateAt(null);
        invoice.setTotalPrice(invoiceDto.getTotalPrice());
        invoice.setFullName(invoiceDto.getFullName());
        invoice.setAddress(invoiceDto.getAddress());
        invoice.setPhoneNumber(invoiceDto.getPhoneNumber());
        invoice.setStatus(invoiceDto.getStatus());
        Invoice result = invoiceRepo.save(invoice);
        Invoice currInvoice = invoiceRepo.findById(result.getId()).get();
        List<DetailInvoice> detailInvoices = new ArrayList<>();
        invoiceDto.getDetailInvoices().forEach(detail -> {
            if(currInvoice == null) {
                throw new EtBadRequestException("Invalid information");
            }
            DetailInvoice detailInvoice = new DetailInvoice();
            Optional<Product> product = productRepo.findById(detail.getProductId());
            if(!product.isPresent()) {
                throw new EtBadRequestException("Invalid product");
            }
            detailInvoice.setProduct(product.get());
            detailInvoice.setQuantity(detail.getQuantity());
            detailInvoice.setInvoice(currInvoice);
            detailInvoices.add(detailInvoice);
        });
        currInvoice.setDetailInvoices(detailInvoices);
        System.out.println(currInvoice);
        return invoiceRepo.save(currInvoice);
    }

    @Override
    public Page<Invoice> findAllInvoicesByPage(int page, int size) throws EtResourceNotFoundException {
        Pageable pageRender = PageRequest.of(page - 1, size);
        return invoiceRepo.findAll(pageRender);
    }

    public List<Invoice> findAllInvoices() throws EtResourceNotFoundException {
        return invoiceRepo.findAll();
    }
    public Invoice findInvoiceById(Integer id) throws EtResourceNotFoundException {
        return invoiceRepo.findById(id).get();
    }

    @Override
    public List<DetailInvoice> findDetailInvoice(Invoice invoice) throws EtResourceNotFoundException {
        return detailInvoiceRepo.findAllByInvoice_Id(invoice.getId());
    }

    @Override
    public Invoice editInvoiceStatusById(int invoiceId, String status) throws EtAuthException {
        return null;
    }
}
