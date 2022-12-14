package ru.vsu.porkhunov.trainroutes.ui.command.impl.station;

import ru.vsu.porkhunov.trainroutes.entity.Station;
import ru.vsu.porkhunov.trainroutes.service.StationService;
import ru.vsu.porkhunov.trainroutes.service.exception.EntityNotFoundException;

import java.util.Scanner;

public class StationUpdateByIdCommand extends StationCommand {
    private static final String COMMAND_NAME = "Обновление данных о станции по ID";

    public StationUpdateByIdCommand(StationService stationService, Scanner scanner) {
        super(COMMAND_NAME, stationService, scanner);
    }

    @Override
    protected String doExecute() {
        System.out.print("Введите ID обновляемой станции: ");
        long id = scanner.nextLong();

        System.out.print("Введите новое наименование станции: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        try {
            Station station = stationService.updateById(new Station(name), id);

            return "Станция успешно обновлена\n" + station.toString();
        } catch (EntityNotFoundException e) {
            return String.format("Станции с ID %d не существует", id);
        }
    }
}
