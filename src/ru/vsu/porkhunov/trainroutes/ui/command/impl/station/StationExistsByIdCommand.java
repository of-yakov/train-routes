package ru.vsu.porkhunov.trainroutes.ui.command.impl.station;

import ru.vsu.porkhunov.trainroutes.service.StationService;

import java.util.Scanner;

public class StationExistsByIdCommand extends StationCommand {
    private static final String COMMAND_NAME = "Проверка существования станции по ID";

    public StationExistsByIdCommand(StationService stationService, Scanner scanner) {
        super(COMMAND_NAME, stationService, scanner);
    }

    @Override
    protected String doExecute() {
        System.out.print("Введите ID станции: ");
        long id = scanner.nextLong();

        return stationService.existsById(id) ?
                String.format("Станция с ID %d существует", id) :
                String.format("Станции с ID %d не существует", id);
    }
}
