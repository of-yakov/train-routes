package ru.vsu.porkhunov.trainroutes.ui.command.impl.waypoint;

import ru.vsu.porkhunov.trainroutes.entity.Waypoint;
import ru.vsu.porkhunov.trainroutes.service.WaypointService;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WaypointFindAllByRouteIdWithSortByArrivesAtCommand extends WaypointCommand {
    private static final String COMMAND_NAME = "Получение всех точек маршрута по ID маршрута";

    public WaypointFindAllByRouteIdWithSortByArrivesAtCommand(WaypointService waypointService, Scanner scanner) {
        super(COMMAND_NAME, waypointService, scanner);
    }

    @Override
    protected String doExecute() {
        System.out.print("Введите ID маршрута: ");
        long routeId = scanner.nextLong();

        List<Waypoint> waypoints = waypointService.findAllByRouteIdWithSortByArrivesAt(routeId);

        if (!waypoints.isEmpty()) {
            return IntStream.range(0, waypoints.size())
                    .mapToObj(i -> waypoints.get(i).toString() + (i < waypoints.size() - 1 ? "\n" : ""))
                    .collect(Collectors.joining());
        }

        return "Точки маршрута не найдены";
    }
}
