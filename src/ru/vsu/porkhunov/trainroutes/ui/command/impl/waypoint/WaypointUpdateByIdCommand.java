package ru.vsu.porkhunov.trainroutes.ui.command.impl.waypoint;

import ru.vsu.porkhunov.trainroutes.entity.Waypoint;
import ru.vsu.porkhunov.trainroutes.service.WaypointService;
import ru.vsu.porkhunov.trainroutes.service.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.Scanner;

public class WaypointUpdateByIdCommand extends WaypointCommand {
    private static final String COMMAND_NAME = "Обновление данных о точке маршрута по ID";

    public WaypointUpdateByIdCommand(WaypointService waypointService, Scanner scanner) {
        super(COMMAND_NAME, waypointService, scanner);
    }

    @Override
    protected String doExecute() {
        System.out.print("Введите ID обновляемой точки маршрута: ");
        long id = scanner.nextLong();

        System.out.print("Введите новый ID маршрута: ");
        long routeId = scanner.nextLong();

        System.out.print("Введите новый ID станции: ");
        long stationId = scanner.nextLong();

        System.out.print("Введите новую дату и время отправления: ");
        scanner.nextLine();
        LocalDateTime departsAt = LocalDateTime.parse(scanner.nextLine());

        System.out.print("Введите новую дату и время прибытия: ");
        LocalDateTime arrivesAt = LocalDateTime.parse(scanner.nextLine());

        try {
            Waypoint waypoint = waypointService.updateById(
                    new Waypoint(routeId, stationId, departsAt, arrivesAt), id
            );

            return "Точка остановки маршрута успешно обновлена\n" + waypoint.toString();
        } catch (EntityNotFoundException e) {
            return String.format("Точки маршрута с ID %d не существует", id);
        }
    }
}
