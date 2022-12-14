package ru.vsu.porkhunov.trainroutes.ui.command.impl.station;

import ru.vsu.porkhunov.trainroutes.service.StationService;
import ru.vsu.porkhunov.trainroutes.service.exception.EntityNotFoundException;

import java.util.Scanner;

public class StationDeleteByIdCommand extends StationCommand {
    private static final String COMMAND_NAME = "Удаление станции по ID";

    public StationDeleteByIdCommand(StationService stationService, Scanner scanner) {
        super(COMMAND_NAME, stationService, scanner);
    }

    @Override
    protected String doExecute() {
        System.out.print("Введите ID станции: ");
        long id = scanner.nextLong();

        try {
            stationService.deleteById(id);
        } catch (EntityNotFoundException e) {
            return String.format("Станции с ID %d не существует", id);
        }

        return String.format("Станция с ID %d успешно удалена", id);
    }
}
