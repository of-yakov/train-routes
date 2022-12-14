package ru.vsu.porkhunov.trainroutes.ui.command.impl.route;

import ru.vsu.porkhunov.trainroutes.service.RouteService;
import ru.vsu.porkhunov.trainroutes.ui.command.ConsoleCommand;

import java.util.Scanner;

public abstract class RouteCommand extends ConsoleCommand {
    protected final RouteService routeService;
    protected final Scanner scanner;

    public RouteCommand(String name, RouteService routeService, Scanner scanner) {
        super(name);
        this.routeService = routeService;
        this.scanner = scanner;
    }
}
