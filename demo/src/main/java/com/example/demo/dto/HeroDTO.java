package com.example.demo.dto;

import java.util.List;

public class HeroDTO {
    private Long id;
    private String name;
    private String image;
    private List<String> powers;

    public HeroDTO() {}

    public HeroDTO(Long id, String name, List<String> powers) {
        this.id = id;
        this.name = name;
        this.powers = powers;
    }

    public HeroDTO(Long id, String name, List<String> powers, String image) {
        this.id = id;
        this.name = name;
        this.powers = powers;
        this.image = image;
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

    public List<String> getPowers() {
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

    public void setPowers(List<String> powers) {
        this.powers = powers;
    }
}
