package ru.vsu.porkhunov.trainroutes.ui.command.impl.route;

import ru.vsu.porkhunov.trainroutes.entity.Route;
import ru.vsu.porkhunov.trainroutes.service.RouteService;
import ru.vsu.porkhunov.trainroutes.service.exception.EntityNotFoundException;

import java.util.Scanner;

public class RouteUpdateByIdCommand extends RouteCommand {
    private static final String COMMAND_NAME = "Обновление данных о маршруте по ID";

    public RouteUpdateByIdCommand(RouteService routeService, Scanner scanner) {
        super(COMMAND_NAME, routeService, scanner);
    }

    @Override
    protected String doExecute() {
        System.out.print("Введите ID обновляемого маршрута: ");
        long id = scanner.nextLong();

        System.out.print("Введите новое наименование маршрута: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        System.out.print("Введите новый ID поезда: ");
        long trainId = scanner.nextLong();

        System.out.print("Введите новый ID станции отправления: ");
        long departureStationId = scanner.nextLong();

        System.out.print("Введите новый ID станции прибытия: ");
        long arrivalStationId = scanner.nextLong();

        try {
            Route route = routeService.updateById(
                    new Route(name, trainId, arrivalStationId, departureStationId), id
            );

            return "Маршрут успешно обновлен\n" + route.toString();
        } catch (EntityNotFoundException e) {
            return String.format("Маршрута с ID %d не существует", id);
        }
    }
}
