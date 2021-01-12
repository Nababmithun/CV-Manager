package com.example.personalinformation;

public class FieldOfInterestPojo {

    //data members
    private String field;
    private String skills;
    private String strengths;
    private String hobbies;

    //getters
    public String getField()
    {
        return field;
    }

    public String getSkills()
    {
        return skills;
    }

    public String getStrengths()
    {
        return strengths;
    }

    public String getHobbies() { return hobbies; }

    //setters
    public void setField(String field)
    {
        this.field = field;
    }

    public void setSkills(String skills)
    {
        this.skills = skills;
    }

    public void setStrengths(String strengths)
    {
        this.strengths = strengths;
    }

    public void setHobbies(String hobbies) { this.hobbies = hobbies; }
}
