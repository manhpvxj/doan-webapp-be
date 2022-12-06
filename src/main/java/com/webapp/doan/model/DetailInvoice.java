package com.webapp.doan.model;

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
@Table(name = "DETAILINVOICE")
public class DetailInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Invoice invoiceId;

    @OneToOne
    @JoinColumn(name = "productId")
    private Product product;


    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total")
    private Long total;

}
