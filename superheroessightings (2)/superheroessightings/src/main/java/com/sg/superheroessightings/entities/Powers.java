package com.sg.superheroessightings.entities;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Powers {
    public int powerId;
    @NotBlank(message = "Super power must not be empty.")
    @Size(max = 50, message = "Super power must be less than 50 characters.")
    public String superpower;

    public int getPowerId() {
        return powerId;
    }

    public void setPowerId(int powerId) {
        this.powerId = powerId;
    }

    public String getSuperpower() {
        return superpower;
    }

    public void setSuperpower(String superpower) {
        this.superpower = superpower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Powers powers = (Powers) o;
        return powerId == powers.powerId && Objects.equals(superpower, powers.superpower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(powerId, superpower);
    }
}
