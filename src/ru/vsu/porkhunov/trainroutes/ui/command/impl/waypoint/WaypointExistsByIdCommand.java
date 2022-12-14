package ru.vsu.porkhunov.trainroutes.ui.command.impl.waypoint;

import ru.vsu.porkhunov.trainroutes.service.WaypointService;

import java.util.Scanner;

public class WaypointExistsByIdCommand extends WaypointCommand {
    private static final String COMMAND_NAME = "Проверка существования точки маршрута по ID";

    public WaypointExistsByIdCommand(WaypointService waypointService, Scanner scanner) {
        super(COMMAND_NAME, waypointService, scanner);
    }

    @Override
    protected String doExecute() {
        System.out.print("Введите ID точки маршрута: ");
        long id = scanner.nextLong();

        return waypointService.existsById(id) ?
                String.format("Точка маршрута с ID %d существует", id) :
                String.format("Точки маршрута с ID %d не существует", id);
    }
}
