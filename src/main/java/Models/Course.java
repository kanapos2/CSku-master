package Models;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

public class Course {

    private String id , name , credit , year, term , difficulty,prerequisite;
    private String s_id;

    public Course(String id, String name, String credit, String year, String term, String difficulty,String prerequisite) {
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.year = year;
        this.term = term;
        this.difficulty = difficulty;
        this.prerequisite=prerequisite;
    }

    public Course(String s_id, String c_id,String c_name, String credit) {
        this.s_id = s_id;
        this.id=c_id;
        this.name = c_name;
        this.credit = credit;
    }


    public String getS_id() {
        return s_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", credit='" + credit + '\'' +
                ", year='" + year + '\'' +
                ", term='" + term + '\'' +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }
}
