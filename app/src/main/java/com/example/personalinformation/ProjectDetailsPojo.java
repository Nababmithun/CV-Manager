package com.example.personalinformation;

public class ProjectDetailsPojo {

    //data members
    private String title;
    private String description;
    private String role;
    private int teamStrength;
    private int duration;

    //getters
    public String getTitle() { return title; }

    public String getDescription() { return description; }

    public String getRole() { return role; }

    public int getTeamStrength() { return teamStrength; }

    public int getDuration() { return duration; }

    //setters
    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public void setTeamStrength(int teamStrength) { this.teamStrength = teamStrength; }

    public void setDuration(int duration) { this.duration = duration; }
}
