package com.example.personalinformation;

public class PersonalDetailsPojo {

    //data members
    private String name;
    private String address;
    private String email;
    private String contact;

    //getters
    public String getName()
    {
        return name;
    }

    public String getAddress()
    {
        return address;
    }

    public String getEmail()
    {
        return email;
    }

    public String getContact() { return contact; }

    //setters
    public void setName(String name)
    {
        this.name = name;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setContact(String contact) { this.contact = contact; }
}
