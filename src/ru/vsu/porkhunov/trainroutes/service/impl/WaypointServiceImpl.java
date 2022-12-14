package ru.vsu.porkhunov.trainroutes.service.impl;

import ru.vsu.porkhunov.trainroutes.entity.Waypoint;
import ru.vsu.porkhunov.trainroutes.persistence.repository.WaypointRepository;
import ru.vsu.porkhunov.trainroutes.service.WaypointService;
import ru.vsu.porkhunov.trainroutes.service.exception.EntityNotFoundException;
import ru.vsu.porkhunov.trainroutes.service.exception.WaypointNotFoundException;

import java.util.List;

public class WaypointServiceImpl implements WaypointService {
    private final WaypointRepository waypointRepository;

    public WaypointServiceImpl(WaypointRepository waypointRepository) {
        this.waypointRepository = waypointRepository;
    }

    @Override
    public List<Waypoint> findAllByRouteIdWithSortByArrivesAt(Long routeId) {
        return waypointRepository.findAllByRouteIdAndSortedByArrivesAt(routeId);
    }

    @Override
    public Waypoint add(Waypoint waypoint) {
        return waypointRepository.save(waypoint);
    }

    @Override
    public void deleteById(Long id) throws WaypointNotFoundException {
        if (!waypointRepository.existsById(id)) {
            throw new WaypointNotFoundException();
        }

        waypointRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return waypointRepository.existsById(id);
    }

    @Override
    public List<Waypoint> findAll() {
        return waypointRepository.findAll();
    }

    @Override
    public Waypoint findById(Long id) throws EntityNotFoundException {
        return waypointRepository.findById(id)
                .orElseThrow(WaypointNotFoundException::new);
    }

    @Override
    public Waypoint updateById(Waypoint waypoint, Long id) throws WaypointNotFoundException {
        if (waypoint == null) {
            throw new IllegalArgumentException("Waypoint cannot be null");
        }

        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        if (!waypointRepository.existsById(id)) {
            throw new WaypointNotFoundException();
        }

        waypoint.setId(id);

        return waypointRepository.save(waypoint);
    }
}
