package com.goshop.demo.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.sql.Blob;

public class Image {
    private Long id;
    private String imgName;
    private String imgType;
    private Blob image;
    private String downloadUrl;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


}
