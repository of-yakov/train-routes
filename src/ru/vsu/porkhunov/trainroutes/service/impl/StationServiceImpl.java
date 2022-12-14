package ru.vsu.porkhunov.trainroutes.service.impl;

import ru.vsu.porkhunov.trainroutes.entity.Station;
import ru.vsu.porkhunov.trainroutes.persistence.repository.StationRepository;
import ru.vsu.porkhunov.trainroutes.service.StationService;
import ru.vsu.porkhunov.trainroutes.service.exception.EntityNotFoundException;
import ru.vsu.porkhunov.trainroutes.service.exception.StationNotFoundException;

import java.util.List;

public class StationServiceImpl implements StationService {
    private final StationRepository stationRepository;

    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public Station add(Station station) {
        return stationRepository.save(station);
    }

    @Override
    public void deleteById(Long id) throws StationNotFoundException {
        if (!stationRepository.existsById(id)) {
            throw new StationNotFoundException();
        }

        stationRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return stationRepository.existsById(id);
    }

    @Override
    public List<Station> findAll() {
        return stationRepository.findAll();
    }

    @Override
    public Station findById(Long id) throws EntityNotFoundException {
        return stationRepository.findById(id)
                .orElseThrow(StationNotFoundException::new);
    }

    @Override
    public Station updateById(Station station, Long id) throws StationNotFoundException {
        if (station == null) {
            throw new IllegalArgumentException("Station cannot be null");
        }

        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        if (!stationRepository.existsById(id)) {
            throw new StationNotFoundException();
        }

        station.setId(id);

        return stationRepository.save(station);
    }
}
