package com.example.best_aid_doctor.Model;

public class Question {

    String description , id ;

    public Question(String description , String id) {
        this.description = description;
        this.id = id ;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
