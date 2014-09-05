package by.epam.testingsystem.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable, Cloneable {

    private int id;

    public Entity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}