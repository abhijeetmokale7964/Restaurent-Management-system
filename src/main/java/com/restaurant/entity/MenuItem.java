package com.restaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@Table(name = "menu_items")
public class MenuItem {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;

	    private String category;

	    private Double price;

	    private Boolean available;

	 public MenuItem() {
		super();
		// TODO Auto-generated constructor stub
	 }
	 
	 
	 
}
