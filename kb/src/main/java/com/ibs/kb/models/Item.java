package com.ibs.kb.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private String style;
    private int price;

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public String getStyle() {return style;}
    public void setStyle(String style) {this.style = style;}
    public int getPrice() {return price;}
    public void setPrice(int price) {this.price = price;}
}
