package ru.vsu.porkhunov.trainroutes.ui.command.impl.station;

import ru.vsu.porkhunov.trainroutes.service.StationService;
import ru.vsu.porkhunov.trainroutes.service.exception.EntityNotFoundException;

import java.util.Scanner;

public class StationFindByIdCommand extends StationCommand {
    private static final String COMMAND_NAME = "Получение станции по ID";

    public StationFindByIdCommand(StationService stationService, Scanner scanner) {
        super(COMMAND_NAME, stationService, scanner);
    }

    @Override
    protected String doExecute() {
        System.out.print("Введите ID станции: ");
        long id = scanner.nextLong();

        try {
            return stationService.findById(id).toString();
        } catch (EntityNotFoundException e) {
            return String.format("Станции с ID %d не существует", id);
        }
    }
}
