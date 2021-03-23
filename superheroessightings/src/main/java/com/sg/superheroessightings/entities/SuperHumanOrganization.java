package com.sg.superheroessightings.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class SuperHumanOrganization {
    public int shoId;
    public int superHumanId;
    public int organizationId;

    public int getShoId() {
        return shoId;
    }

    public void setShoId(int shoId) {
        this.shoId = shoId;
    }

    public int getSuperHumanId() {
        return superHumanId;
    }

    public void setSuperHumanId(int superHumanId) {
        this.superHumanId = superHumanId;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuperHumanOrganization that = (SuperHumanOrganization) o;
        return shoId == that.shoId && superHumanId == that.superHumanId && organizationId == that.organizationId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shoId, superHumanId, organizationId);
    }
}
