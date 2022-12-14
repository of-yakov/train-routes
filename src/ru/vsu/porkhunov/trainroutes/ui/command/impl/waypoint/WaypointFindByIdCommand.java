package ru.vsu.porkhunov.trainroutes.ui.command.impl.waypoint;

import ru.vsu.porkhunov.trainroutes.service.WaypointService;
import ru.vsu.porkhunov.trainroutes.service.exception.EntityNotFoundException;

import java.util.Scanner;

public class WaypointFindByIdCommand extends WaypointCommand {
    private static final String COMMAND_NAME = "Получение точки маршрута по ID";

    public WaypointFindByIdCommand(WaypointService waypointService, Scanner scanner) {
        super(COMMAND_NAME, waypointService, scanner);
    }

    @Override
    protected String doExecute() {
        System.out.print("Введите ID точки маршрута: ");
        long id = scanner.nextLong();

        try {
            return waypointService.findById(id).toString();
        } catch (EntityNotFoundException e) {
            return String.format("Точки маршрута с ID %d не существует", id);
        }
    }
}
