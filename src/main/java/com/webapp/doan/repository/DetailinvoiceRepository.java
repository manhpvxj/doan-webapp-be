package com.webapp.doan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webapp.doan.model.DetailInvoice;

import java.util.List;

@Repository
public interface DetailinvoiceRepository extends JpaRepository<DetailInvoice, Integer> {
    @Query(value = "SELECT * FROM DetailInvoice WHERE invoice_id = :id", nativeQuery = true)
    public List<DetailInvoice> findAllByInvoice_Id(Integer id);
}
