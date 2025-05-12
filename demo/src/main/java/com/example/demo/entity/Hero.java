package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String image;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "hero_id")
    private List<Power> powers;

    public Hero() {}

    public Hero(String name, List<Power> powers) {
        this.name = name;
        this.powers = powers;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public List<Power> getPowers() {
        return powers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPowers(List<Power> powers) {
        this.powers = powers;
    }
}
