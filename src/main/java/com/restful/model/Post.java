/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restful.model;

import java.util.Objects;

/**
 *
 * @author macoo
 */
public class Post {

    private int id;
    private int userId;
    private String title;
    private String body;

    // Constructor to initialize the Post with an ID only (used for searching)
    public Post(int id) {
        this.id = id;
    }

    // Default constructor
    public Post() {
    }

    // Constructor to initialize all attributes
    public Post(int id, int userId, String title, String body) {
        super();
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Setter for ID
    public void setId(int id) {
        this.id = id;
    }

    // Getter for User ID
    public int getUserId() {
        return userId;
    }

    // Setter for User ID
    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Getter for Title
    public String getTitle() {
        return title;
    }

    // Setter for Title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter for Body
    public String getBody() {
        return body;
    }

    // Setter for Body
    public void setBody(String body) {
        this.body = body;
    }

    // Override hashCode method
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        hash = 47 * hash + this.userId;
        hash = 47 * hash + Objects.hashCode(this.title);
        hash = 47 * hash + Objects.hashCode(this.body);
        return hash;
    }

    // Override equals method to compare Posts by ID, User ID, Title, and Body
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Post other = (Post) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return Objects.equals(this.body, other.body);
    }

}