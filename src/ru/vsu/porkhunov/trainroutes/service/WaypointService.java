package ru.vsu.porkhunov.trainroutes.service;

import ru.vsu.porkhunov.trainroutes.entity.Waypoint;

import java.util.List;

public interface WaypointService extends Service<Waypoint, Long> {
    List<Waypoint> findAllByRouteIdWithSortByArrivesAt(Long routeId);
}
