package models;

import java.util.Date;

/**
 * Created by Fabian on 24.03.17.
 * User
 */
public class User {
    private int id;
    private String name;
    private String firstName;
    private Date dateOfBirth;
    private Gender gender;

    public User(int id, String name, String firstName, Date dateOfBirth, Gender gender){
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public Date getDateOfBirth(){
        return dateOfBirth;
    }

    public int getId(){
        return id;
    }
}
