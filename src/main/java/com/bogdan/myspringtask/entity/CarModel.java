package com.bogdan.myspringtask.entity;

import javax.persistence.*;

@Entity
@Table(name = "car_models")
public class CarModel extends BaseEntity{

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public CarModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "name='" + name + '\'' +
                ", brand=" + brand.getName() +
                '}';
    }
}
