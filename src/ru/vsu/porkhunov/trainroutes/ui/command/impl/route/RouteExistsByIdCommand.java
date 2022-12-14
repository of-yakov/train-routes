package ru.vsu.porkhunov.trainroutes.ui.command.impl.route;

import ru.vsu.porkhunov.trainroutes.service.RouteService;

import java.util.Scanner;

public class RouteExistsByIdCommand extends RouteCommand {
    private final static String COMMAND_NAME = "Проверка существования маршрута по ID";

    public RouteExistsByIdCommand(RouteService routeService, Scanner scanner) {
        super(COMMAND_NAME, routeService, scanner);
    }

    @Override
    protected String doExecute() {
        System.out.print("Введите ID маршрута: ");
        long id = scanner.nextLong();

        return routeService.existsById(id) ?
                String.format("Маршрут с ID %d существует", id) :
                String.format("Маршрута с ID %d не существует", id);
    }
}
