package ru.vsu.porkhunov.trainroutes.persistence.repository;

import ru.vsu.porkhunov.trainroutes.entity.Waypoint;

import java.util.List;

public interface WaypointRepository extends CrudRepository<Waypoint, Long> {
    List<Waypoint> findAllByRouteIdAndSortedByArrivesAt(Long routeId);
}
