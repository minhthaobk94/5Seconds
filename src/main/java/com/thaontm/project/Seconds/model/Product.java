package com.thaontm.project.Seconds.model;

import com.thaontm.project.Seconds.utils.Constants;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cat_id", referencedColumnName = "id")
    private Category category;

    @Column(name = "productName")
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "price")
    private double price;

    public Product() {
    }

    public Product(Category category, String productName, String description, String imgUrl, double price) {
        this.category = category;
        this.productName = productName;
        this.description = description;
        this.imgUrl = imgUrl;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        if (imgUrl != null && !imgUrl.isEmpty()) {
            return imgUrl;
        }
        return Constants.IMG_URL_DEFAULT;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
