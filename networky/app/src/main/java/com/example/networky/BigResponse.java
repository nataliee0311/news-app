package com.example.networky;

import java.util.List;

//Class for json OBJ response
//Method Get Art List returns all articles from json OBJ 'BigResponse' - Stores them in Array of type <Art>

public class BigResponse {
    private List<Art> articles;
    private String status;
    public String getStatus() {
        return status;
    }
    public List<Art> getArtlist() {
        return articles;
    }
}
