package ru.vsu.porkhunov.trainroutes.entity;

public class Station extends Entity<Long> {
    private String name;

    public Station() {
    }

    public Station(String name) {
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
        return "Station{" +
                "name='" + name + '\'' +
                ", id=" + id +
                "}";
    }
}
