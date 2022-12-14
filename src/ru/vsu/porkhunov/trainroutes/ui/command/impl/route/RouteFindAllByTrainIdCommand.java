package ru.vsu.porkhunov.trainroutes.ui.command.impl.route;

import ru.vsu.porkhunov.trainroutes.entity.Route;
import ru.vsu.porkhunov.trainroutes.service.RouteService;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RouteFindAllByTrainIdCommand extends RouteCommand {
    private final static String COMMAND_NAME = "Получение всех маршрутов по ID поезда";

    public RouteFindAllByTrainIdCommand(RouteService routeService, Scanner scanner) {
        super(COMMAND_NAME, routeService, scanner);
    }

    @Override
    protected String doExecute() {
        System.out.print("Введите ID поезда: ");
        long trainId = scanner.nextLong();

        List<Route> routes = routeService.findAllByTrainId(trainId);

        if (!routes.isEmpty()) {
            return IntStream.range(0, routes.size())
                    .mapToObj(i -> routes.get(i).toString() + (i < routes.size() - 1 ? "\n" : ""))
                    .collect(Collectors.joining());
        }

        return "Маршруты не найдены";
    }
}
