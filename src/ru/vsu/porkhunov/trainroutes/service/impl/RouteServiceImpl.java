package ru.vsu.porkhunov.trainroutes.service.impl;

import ru.vsu.porkhunov.trainroutes.entity.Route;
import ru.vsu.porkhunov.trainroutes.persistence.repository.RouteRepository;
import ru.vsu.porkhunov.trainroutes.service.RouteService;
import ru.vsu.porkhunov.trainroutes.service.exception.EntityNotFoundException;
import ru.vsu.porkhunov.trainroutes.service.exception.RouteNotFoundException;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;

    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public List<Route> findAllByTrainId(Long trainId) {
        return routeRepository.findAllByTrainId(trainId);
    }

    @Override
    public Route add(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public void deleteById(Long id) throws RouteNotFoundException {
        if (!routeRepository.existsById(id)) {
            throw new RouteNotFoundException();
        }

        routeRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return routeRepository.existsById(id);
    }

    @Override
    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    @Override
    public Route findById(Long id) throws EntityNotFoundException {
        return routeRepository.findById(id)
                .orElseThrow(RouteNotFoundException::new);
    }

    @Override
    public Route updateById(Route route, Long id) throws RouteNotFoundException {
        if (route == null) {
            throw new IllegalArgumentException("Route cannot be null");
        }

        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        if (!routeRepository.existsById(id)) {
            throw new RouteNotFoundException();
        }

        route.setId(id);

        return routeRepository.save(route);
    }
}
