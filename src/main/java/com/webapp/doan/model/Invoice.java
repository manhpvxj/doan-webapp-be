package com.webapp.doan.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "createAt")
    private String createAt;

    @Column(name = "totalPrice")
    private Long totalPrice;

    @Column(name = "updateAt")
    private String updateAt;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private String status;

    @JsonIgnore
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private List<DetailInvoice> detailInvoices;
}
