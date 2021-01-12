package com.example.personalinformation;

public class ReferenceDetailsPojo {

    //data members
    private String name;
    private String designation;
    private String organization;
    private String address;
    private String email;
    private String phone;

    //getters
    public String getName()
    {
        return name;
    }

    public String getDesignation()
    {
        return designation;
    }

    public String getOrganization()
    {
        return organization;
    }

    public String getAddress()
    {
        return address;
    }

    public String getPhone() { return phone; }

    public String getEmail()
    {
        return email;
    }

    //setters
    public void setName(String name)
    {
        this.name = name;
    }

    public void setDesignation(String designation)
    {
        this.designation = designation;
    }

    public void setOrganization(String organization)
    {
        this.organization = organization;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setPhone(String phone) { this.phone = phone; }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
