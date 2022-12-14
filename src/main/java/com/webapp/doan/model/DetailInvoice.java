package com.webapp.doan.model;

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
@Table(name = "DETAILINVOICE")
public class DetailInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @JsonIgnore
    @JoinColumn( name = "invoice_id")
    @ManyToOne
    private Invoice invoice;

    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;


}
