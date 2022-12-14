package ru.vsu.porkhunov.trainroutes.ui.command.impl.route;

import ru.vsu.porkhunov.trainroutes.entity.Route;
import ru.vsu.porkhunov.trainroutes.service.RouteService;

import java.util.Scanner;

public class RouteAddCommand extends RouteCommand {
    private final static String COMMAND_NAME = "Добавление маршрута";

    public RouteAddCommand(RouteService routeService, Scanner scanner) {
        super(COMMAND_NAME, routeService, scanner);
    }


    @Override
    protected String doExecute() {
        System.out.print("Введите наименование маршрута: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        System.out.print("Введите ID поезда: ");
        long trainId = scanner.nextLong();

        System.out.print("Введите ID станции отправления: ");
        long departureStationId = scanner.nextLong();

        System.out.print("Введите ID станции прибытия: ");
        long arrivalStationId = scanner.nextLong();

        Route route = routeService.add(
                new Route(name, trainId, departureStationId, arrivalStationId)
        );

        return "Маршрут успешно добавлен\n" + route.toString();
    }
}
