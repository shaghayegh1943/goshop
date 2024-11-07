package com.goshop.demo.model;

import java.math.BigDecimal;
import java.util.List;

public class Product {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int quantity;
    private String description;
    private Category category;
    private List<Image> images;

}
