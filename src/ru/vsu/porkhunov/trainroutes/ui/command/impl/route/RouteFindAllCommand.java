package ru.vsu.porkhunov.trainroutes.ui.command.impl.route;

import ru.vsu.porkhunov.trainroutes.entity.Route;
import ru.vsu.porkhunov.trainroutes.service.RouteService;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RouteFindAllCommand extends RouteCommand {
    private static final String COMMAND_NAME = "Получение всех маршрутов";

    public RouteFindAllCommand(RouteService routeService, Scanner scanner) {
        super(COMMAND_NAME, routeService, scanner);
    }

    @Override
    protected String doExecute() {
        List<Route> routes = routeService.findAll();

        if (!routes.isEmpty()) {

            return IntStream.range(0, routes.size())
                    .mapToObj(i -> routes.get(i).toString() + (i < routes.size() - 1 ? "\n" : ""))
                    .collect(Collectors.joining());
        }

        return "Маршруты не найдены";
    }
}
