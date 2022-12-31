package com.ProductService.Model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {
	
	     @Id
	     @GeneratedValue(strategy = GenerationType.AUTO)
	    private Integer id;
	    private String name;
	    private String description;
	    private BigDecimal price;

}
