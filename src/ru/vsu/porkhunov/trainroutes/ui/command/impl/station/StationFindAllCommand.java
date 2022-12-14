package ru.vsu.porkhunov.trainroutes.ui.command.impl.station;

import ru.vsu.porkhunov.trainroutes.entity.Station;
import ru.vsu.porkhunov.trainroutes.service.StationService;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StationFindAllCommand extends StationCommand {
    private static final String COMMAND_NAME = "Получение всех станций";

    public StationFindAllCommand(StationService stationService, Scanner scanner) {
        super(COMMAND_NAME, stationService, scanner);
    }

    @Override
    protected String doExecute() {
        List<Station> stations = stationService.findAll();

        if (!stations.isEmpty()) {

            return IntStream.range(0, stations.size())
                    .mapToObj(i -> stations.get(i).toString() + (i < stations.size() - 1 ? "\n" : ""))
                    .collect(Collectors.joining());
        }

        return "Станции не найдены";
    }
}
