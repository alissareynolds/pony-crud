package com.example.pony_project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Pony {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String color;

    private Boolean isUnicorn;

    private Boolean isPegasus;

    private Integer age;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getIsUnicorn() {
        return isUnicorn;
    }

    public void setIsUnicorn(Boolean isUnicorn) {
        this.isUnicorn = isUnicorn;
    }

    public Boolean getIsPegasus() {
        return isPegasus;
    }

    public void setIsPegasus(Boolean isPegasus) {
        this.isPegasus = isPegasus;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
