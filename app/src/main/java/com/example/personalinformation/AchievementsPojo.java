package com.example.personalinformation;

public class AchievementsPojo {

    //data members
    private String achievements;
    private String curricular;
    private String extraCurricular;

    //getters
    public String getAchievements()
    {
        return achievements;
    }

    public String getCurricular()
    {
        return curricular;
    }

    public String getExtraCurricular()
    {
        return extraCurricular;
    }

    //setters
    public void setAchievements(String achievements) { this.achievements = achievements; }

    public void setCurricular(String curricular)
    {
        this.curricular = curricular;
    }

    public void setExtraCurricular(String extraCurricular) { this.extraCurricular = extraCurricular; }
}
