package ru.vsu.porkhunov.trainroutes.entity;

public class Train extends Entity<Long> {
    private String name;

    public Train() {
    }

    public Train(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Train{" +
                "name='" + name + '\'' +
                ", id=" + id +
                "}";
    }
}
