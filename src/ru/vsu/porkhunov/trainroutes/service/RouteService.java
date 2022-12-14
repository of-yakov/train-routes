package ru.vsu.porkhunov.trainroutes.service;

import ru.vsu.porkhunov.trainroutes.entity.Route;

import java.util.List;

public interface RouteService extends Service<Route, Long> {
    List<Route> findAllByTrainId(Long trainId);
}
