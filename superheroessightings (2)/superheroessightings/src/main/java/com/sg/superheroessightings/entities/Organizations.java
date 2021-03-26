package com.sg.superheroessightings.entities;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Organizations {
    public int organizationId;

    @NotBlank(message = "Organization's Name must not be empty.")
    @Size(max = 50, message="Organization's Name must be less than 50 characters.")
    public String organizationName;
    @NotBlank(message = "Organization's Description must not be empty.")
    @Size(max = 256, message="Organization's Description must be less than 255 characters.")
    public String organizationDescription;
    @NotBlank(message = "Organization's Address must not be empty.")
    @Size(max = 256, message="Organization's Address must be less than 255 characters.")
    public String address;
    public String contactInfo;


    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationDescription() {
        return organizationDescription;
    }

    public void setOrganizationDescription(String organizationDescription) {
        this.organizationDescription = organizationDescription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organizations that = (Organizations) o;
        return organizationId == that.organizationId && Objects.equals(organizationName, that.organizationName) && Objects.equals(organizationDescription, that.organizationDescription) && Objects.equals(address, that.address) && Objects.equals(contactInfo, that.contactInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizationId, organizationName, organizationDescription, address, contactInfo);
    }
}
