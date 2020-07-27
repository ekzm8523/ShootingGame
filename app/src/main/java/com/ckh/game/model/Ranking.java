package com.ckh.game.model;

public class Ranking {
    private int id;
    private String username;
    private int score;
    private String created_at;

    public Ranking(String username, int score) {
        this.username = username;
        this.score = score;
        this.created_at = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
