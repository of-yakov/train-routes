package ru.vsu.porkhunov.trainroutes.entity;

import java.time.LocalDateTime;

public class Waypoint extends Entity<Long> {
    private Long routeId;
    private Long stationId;
    private LocalDateTime departsAt;
    private LocalDateTime arrivesAt;

    public Waypoint() {
    }

    public Waypoint(Long routeId, Long stationId, LocalDateTime departsAt, LocalDateTime arrivesAt) {
        this.routeId = routeId;
        this.stationId = stationId;
        this.departsAt = departsAt;
        this.arrivesAt = arrivesAt;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public LocalDateTime getDepartsAt() {
        return departsAt;
    }

    public void setDepartsAt(LocalDateTime departsAt) {
        this.departsAt = departsAt;
    }

    public LocalDateTime getArrivesAt() {
        return arrivesAt;
    }

    public void setArrivesAt(LocalDateTime arrivesAt) {
        this.arrivesAt = arrivesAt;
    }

    @Override
    public String toString() {
        return "RouteStation{" +
                "routeId=" + routeId +
                ", stationId=" + stationId +
                ", departsAt=" + departsAt +
                ", arrivesAt=" + arrivesAt +
                ", id=" + id +
                "}";
    }
}
