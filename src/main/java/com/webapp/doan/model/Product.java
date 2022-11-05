package com.webapp.doan.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer productId;

    @Column(name = "name")
    private String name;

    @Column(name = "priceSell")
    private Integer priceSell;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "description")
    private String description;

    @ElementCollection
    private List<String> image;

    @OneToOne
    @JoinColumn(name = "categoryId")
    private Category category;



}
