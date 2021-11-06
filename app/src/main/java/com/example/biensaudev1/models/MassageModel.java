package com.example.biensaudev1.models;

import java.io.Serializable;

public class MassageModel implements Serializable {
    String title;
    String shortDescription;
    String description;
    String image;
    String duration;
    String recipients;
    String expectation;


    public MassageModel() {

    }

    public MassageModel(String title, String shortDescription, String description, String image, String duration, String recipients, String expectation) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.description = description;
        this.image = image;
        this.duration = duration;
        this.expectation = expectation;
    }

    public String getExpectation() {
        return expectation;
    }

    public void setExpectation(String expectation) {
        this.expectation = expectation;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
