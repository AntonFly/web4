package com.airhacks;

import javax.persistence.*;


@Entity
@Table(name= "dots")
public class Dots {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String login;
    @Column
    private float x;
    @Column
    private float y;
    @Column
    private float r;
    @Column
    private boolean hit;


    public Dots(){}
    public Dots(float x, float y, float r){
        this.x =x;
        this.y =y;
        this.r=r;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
}
