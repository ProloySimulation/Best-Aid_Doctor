package com.example.best_aid_doctor.Model;

public class Question {

    String description , id , comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Question(String description , String id , String comment) {
        this.description = description;
        this.id = id ;
        this.comment = comment ;

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
