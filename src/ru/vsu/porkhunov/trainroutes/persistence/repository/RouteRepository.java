package ru.vsu.porkhunov.trainroutes.persistence.repository;

import ru.vsu.porkhunov.trainroutes.entity.Route;

import java.util.List;

public interface RouteRepository extends CrudRepository<Route, Long> {
    List<Route> findAllByTrainId(Long trainId);
}
