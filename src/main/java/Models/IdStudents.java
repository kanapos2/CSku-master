package Models;

import java.util.ArrayList;

public class IdStudents {

    private String id;
    private String firstName;
    private String lastName;
    private String path;
    private String year;

    public IdStudents(String id , String firstName , String lastName,String year){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.year=year;

    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "IdStudents{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
