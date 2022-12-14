package ru.vsu.porkhunov.trainroutes.ui.command.impl.train;

import ru.vsu.porkhunov.trainroutes.entity.Train;
import ru.vsu.porkhunov.trainroutes.service.TrainService;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrainFindAllCommand extends TrainCommand {
    private static final String COMMAND_NAME = "Получение всех поездов";

    public TrainFindAllCommand(TrainService trainService, Scanner scanner) {
        super(COMMAND_NAME, trainService, scanner);
    }

    @Override
    protected String doExecute() {
        List<Train> trains = trainService.findAll();

        if (!trains.isEmpty()) {

            return IntStream.range(0, trains.size())
                    .mapToObj(i -> trains.get(i).toString() + (i < trains.size() - 1 ? "\n" : ""))
                    .collect(Collectors.joining());
        }

        return "Поезда не найдены";
    }
}
