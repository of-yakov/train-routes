package ru.vsu.porkhunov.trainroutes.ui.command.impl.station;

import ru.vsu.porkhunov.trainroutes.entity.Station;
import ru.vsu.porkhunov.trainroutes.service.StationService;

import java.util.Scanner;

public class StationAddCommand extends StationCommand {
    private static final String COMMAND_NAME = "Добавление новой станции";

    public StationAddCommand(StationService stationService, Scanner scanner) {
        super(COMMAND_NAME, stationService, scanner);
    }

    @Override
    protected String doExecute() {
        System.out.print("Введите наименование станции: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        Station station = stationService.add(new Station(name));

        return "Станция успешно добавлена\n" + station.toString();
    }
}
