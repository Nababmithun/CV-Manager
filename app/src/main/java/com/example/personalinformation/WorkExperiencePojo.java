package com.example.personalinformation;

public class WorkExperiencePojo {

    //data members
    private String organization;
    private String designation;
    private String fromDate;
    private String toDate;
    private String role;

    //getters
    public String getOrganization()
    {
        return organization;
    }

    public String getDesignation()
    {
        return designation;
    }

    public String getFromDate()
    {
        return fromDate;
    }

    public String getToDate() { return toDate; }

    public String getRole() { return role; }

    //setters
    public void setOrganization(String organization)
    {
        this.organization = organization;
    }

    public void setDesignation(String designation)
    {
        this.designation = designation;
    }

    public void setFromDate(String fromDate)
    {
        this.fromDate = fromDate;
    }

    public void setToDate(String toDate)
    {
        this.toDate = toDate;
    }

    public void setRole(String role)
    {
        this.role = role;
    }
}

