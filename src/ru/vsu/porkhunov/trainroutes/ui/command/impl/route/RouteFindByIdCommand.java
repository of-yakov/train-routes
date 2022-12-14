package ru.vsu.porkhunov.trainroutes.ui.command.impl.route;

import ru.vsu.porkhunov.trainroutes.service.RouteService;
import ru.vsu.porkhunov.trainroutes.service.exception.EntityNotFoundException;

import java.util.Scanner;

public class RouteFindByIdCommand extends RouteCommand {
    private final static String COMMAND_NAME = "Получение маршрута по ID";

    public RouteFindByIdCommand(RouteService routeService, Scanner scanner) {
        super(COMMAND_NAME, routeService, scanner);
    }

    @Override
    protected String doExecute() {
        System.out.print("Введите ID маршрута: ");
        long id = scanner.nextLong();

        try {
            return routeService.findById(id).toString();
        } catch (EntityNotFoundException e) {
            return String.format("Маршрута с ID %d не существует", id);
        }
    }
}
