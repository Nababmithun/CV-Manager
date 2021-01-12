package com.example.personalinformation;

public class AcademicDetailsPojo {

    //data members
    private String degreeOrCourse;
    private float marksOrCgpa ;
    private int yearOfPassing;

    //getters
    public String getDegreeOrCourse()
    {
        return degreeOrCourse;
    }

    public float getMarksOrCgpa()
    {
        return marksOrCgpa;
    }

    public int getYearOfPassing()
    {
        return yearOfPassing;
    }

    //setters
    public void setDegreeOrCourse(String degreeOrCourse)
    {
        this.degreeOrCourse = degreeOrCourse;
    }

    public void setMarksOrCgpa(float marksOrCgpa)
    {
        this.marksOrCgpa = marksOrCgpa;
    }

    public void setYearOfPassing(int yearOfPassing) { this.yearOfPassing = yearOfPassing; }
}

