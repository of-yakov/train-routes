package ru.vsu.porkhunov.trainroutes.entity;

public class Route extends Entity<Long> {
    private String name;
    private Long trainId;
    private Long departureStationId;
    private Long arrivalStationId;

    public Route() {
    }

    public Route(String name, Long trainId, Long departureStationId, Long arrivalStationId) {
        this.name = name;
        this.trainId = trainId;
        this.departureStationId = departureStationId;
        this.arrivalStationId = arrivalStationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public Long getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(Long departureStationId) {
        this.departureStationId = departureStationId;
    }

    public Long getArrivalStationId() {
        return arrivalStationId;
    }

    public void setArrivalStationId(Long arrivalStationId) {
        this.arrivalStationId = arrivalStationId;
    }

    @Override
    public String toString() {
        return "Route{" +
                "name='" + name + '\'' +
                ", trainId=" + trainId +
                ", departureStationId=" + departureStationId +
                ", arrivalStationId=" + arrivalStationId +
                ", id=" + id +
                "}";
    }
}
