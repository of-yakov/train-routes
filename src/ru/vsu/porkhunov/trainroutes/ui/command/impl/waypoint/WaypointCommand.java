package ru.vsu.porkhunov.trainroutes.ui.command.impl.waypoint;

import ru.vsu.porkhunov.trainroutes.service.WaypointService;
import ru.vsu.porkhunov.trainroutes.ui.command.ConsoleCommand;

import java.util.Scanner;

public abstract class WaypointCommand extends ConsoleCommand {
    protected final WaypointService waypointService;
    protected final Scanner scanner;

    public WaypointCommand(String name, WaypointService waypointService, Scanner scanner) {
        super(name);
        this.waypointService = waypointService;
        this.scanner = scanner;
    }
}
