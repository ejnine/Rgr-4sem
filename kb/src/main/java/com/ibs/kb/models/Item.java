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
    private Long idSeller;

    public Long getIdSeller() {
        return idSeller;
    }
    public void setIdSeller(Long idSeller) {
        this.idSeller = idSeller;
    }
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

    @Override
    public String toString() {
        String rtn = "Книга: " + name + "\n" +
                    "Описание: " + description + "\n" +
                    "Цена: " + price + " rub" + "\n";
        return rtn;
    }

    public Item() {

    }

    public Item(String name, String description, String style, int price, Long idSeller) {
        this.name = name;
        this.description = description;
        this.style = style;
        this.price = price;
        this.idSeller = idSeller;
    }
}
