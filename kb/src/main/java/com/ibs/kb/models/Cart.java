package com.ibs.kb.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private int idItem;
    private int idBuyer;
    private String status;

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getIdBuyer() {
        return idBuyer;
    }

    public void setIdBuyer(int idBuyer) {
        this.idBuyer = idBuyer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart() {

    }

    public Cart(int idBuyer, int idItem, String status) {
        this.idBuyer = idBuyer;
        this.idItem = idItem;
        this.status = status;
    }
}
