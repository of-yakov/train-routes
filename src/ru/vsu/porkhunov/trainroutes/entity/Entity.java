package ru.vsu.porkhunov.trainroutes.entity;

import java.util.Objects;

public abstract class Entity<Id> {
    protected Id id;

    public Entity() {
    }

    public final Id getId() {
        return id;
    }

    public final void setId(Id id) {
        this.id = id;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Entity<?> entity = (Entity<?>) o;

        return Objects.equals(id, entity.id);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }
}
