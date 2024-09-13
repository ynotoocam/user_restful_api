/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.codejava.ws;

import java.util.Objects;

/**
 *
 * @author macoo
 */
public class Comment {

    private int id;
    private int postId;
    private String email;
    private String body;

    // Constructor to initialize the Comment with an ID only (used for searching)
    public Comment(int id) {
        this.id = id;
    }

    // Default constructor
    public Comment() {
    }

    // Constructor to initialize all attributes
    public Comment(int id, int postId, String email, String body) {
        super();
        this.id = id;
        this.postId = postId;
        this.email = email;
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

    // Getter for Post ID
    public int getPostId() {
        return postId;
    }

    // Setter for Post ID
    public void setPostId(int postId) {
        this.postId = postId;
    }

    // Getter for Email
    public String getEmail() {
        return email;
    }

    // Setter for Email
    public void setEmail(String email) {
        this.email = email;
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
        hash = 59 * hash + this.id;
        hash = 59 * hash + this.postId;
        hash = 59 * hash + Objects.hashCode(this.email);
        hash = 59 * hash + Objects.hashCode(this.body);
        return hash;
    }

    // Override equals method to compare Comments by ID, Post ID, Email, and Body
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
        final Comment other = (Comment) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.postId != other.postId) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.body, other.body);
    }

}