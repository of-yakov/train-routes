package ru.vsu.porkhunov.trainroutes.ui.command.impl.route;

import ru.vsu.porkhunov.trainroutes.service.RouteService;
import ru.vsu.porkhunov.trainroutes.service.exception.EntityNotFoundException;

import java.util.Scanner;

public class RouteDeleteByIdCommand extends RouteCommand {
    private final static String COMMAND_NAME = "Удаление маршрута по ID";

    public RouteDeleteByIdCommand(RouteService routeService, Scanner scanner) {
        super(COMMAND_NAME, routeService, scanner);
    }

    @Override
    protected String doExecute() {
        System.out.print("Введите ID маршрута: ");
        long id = scanner.nextLong();

        try {
            routeService.deleteById(id);
        } catch (EntityNotFoundException e) {
            return String.format("Маршрут с ID %d не существует", id);
        }

        return String.format("Маршрут с ID %d успешно удален", id);
    }
}
