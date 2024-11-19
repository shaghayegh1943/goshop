package com.goshop.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String name;

   @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
   private List<Product> products ;

    public Category(String name) {
        this.name = name;
    }
}
