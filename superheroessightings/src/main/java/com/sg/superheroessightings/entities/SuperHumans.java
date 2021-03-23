package com.sg.superheroessightings.entities;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;


public class SuperHumans {
    public int superHumanId;
    @NotBlank(message = "Name must not be empty.")
    @Size(max = 50, message="Name must be less than 50 characters.")
    public String name;
    @NotBlank(message = "Description must not be empty.")
    @Size(max = 256, message="Name must be less than 50 characters.")
    public String description;

    public int powerId;

    public int getSuperHumanId() {
        return superHumanId;
    }

    public void setSuperHumanId(int superHumanId) {
        this.superHumanId = superHumanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPowerId() {
        return powerId;
    }

    public void setPowerId(int powerId) {
        this.powerId = powerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuperHumans that = (SuperHumans) o;
        return superHumanId == that.superHumanId && powerId == that.powerId && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(superHumanId, name, description, powerId);
    }
}