package ru.vsu.porkhunov.trainroutes.service.impl;

import ru.vsu.porkhunov.trainroutes.entity.Train;
import ru.vsu.porkhunov.trainroutes.persistence.repository.TrainRepository;
import ru.vsu.porkhunov.trainroutes.service.TrainService;
import ru.vsu.porkhunov.trainroutes.service.exception.EntityNotFoundException;
import ru.vsu.porkhunov.trainroutes.service.exception.TrainNotFoundException;

import java.util.List;

public class TrainServiceImpl implements TrainService {
    private final TrainRepository trainRepository;

    public TrainServiceImpl(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    @Override
    public Train add(Train train) {
        return trainRepository.save(train);
    }

    @Override
    public void deleteById(Long id) throws TrainNotFoundException {
        if (!trainRepository.existsById(id)) {
            throw new TrainNotFoundException();
        }

        trainRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return trainRepository.existsById(id);
    }

    @Override
    public List<Train> findAll() {
        return trainRepository.findAll();
    }

    @Override
    public Train findById(Long id) throws EntityNotFoundException {
        return trainRepository.findById(id)
                .orElseThrow(TrainNotFoundException::new);
    }

    @Override
    public Train updateById(Train train, Long id) throws TrainNotFoundException {
        if (train == null) {
            throw new IllegalArgumentException("Train cannot be null");
        }

        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        if (!trainRepository.existsById(id)) {
            throw new TrainNotFoundException();
        }

        train.setId(id);

        return trainRepository.save(train);
    }
}
