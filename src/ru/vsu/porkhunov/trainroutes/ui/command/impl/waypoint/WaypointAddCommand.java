package ru.vsu.porkhunov.trainroutes.ui.command.impl.waypoint;

import ru.vsu.porkhunov.trainroutes.entity.Waypoint;
import ru.vsu.porkhunov.trainroutes.service.WaypointService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class WaypointAddCommand extends WaypointCommand {
    private static final String COMMAND_NAME = "Добавление новой точки маршрута";

    public WaypointAddCommand(WaypointService waypointService, Scanner scanner) {
        super(COMMAND_NAME, waypointService, scanner);
    }

    @Override
    protected String doExecute() {
        System.out.print("Введите идентификатор маршрута: ");
        long routeId = scanner.nextLong();

        System.out.print("Введите идентификатор станции: ");
        long stationId = scanner.nextLong();

        System.out.print("Введите дату и время отправления: ");
        scanner.nextLine();
        LocalDateTime departsAt = LocalDateTime.parse(scanner.nextLine());

        System.out.print("Введите дату и время прибытия: ");
        LocalDateTime arrivesAt = LocalDateTime.parse(scanner.nextLine());

        Waypoint waypoint = waypointService.add(new Waypoint(routeId, stationId, departsAt, arrivesAt));

        return "Точка маршрута успешно добавлена\n" + waypoint.toString();
    }
}
