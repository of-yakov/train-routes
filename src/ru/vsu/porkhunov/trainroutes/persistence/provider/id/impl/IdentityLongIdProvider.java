package ru.vsu.porkhunov.trainroutes.persistence.provider.id.impl;

import ru.vsu.porkhunov.trainroutes.persistence.provider.id.LongIdProvider;

public class IdentityLongIdProvider implements LongIdProvider {
    private Long currentId;

    public IdentityLongIdProvider() {
        currentId = 1L;
    }

    @Override
    public Long provide() {
        Long id = currentId;

        currentId++;

        return id;
    }
}
