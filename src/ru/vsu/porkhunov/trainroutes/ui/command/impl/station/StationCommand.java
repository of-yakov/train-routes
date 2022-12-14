package ru.vsu.porkhunov.trainroutes.ui.command.impl.station;

import ru.vsu.porkhunov.trainroutes.service.StationService;
import ru.vsu.porkhunov.trainroutes.ui.command.ConsoleCommand;

import java.util.Scanner;

public abstract class StationCommand extends ConsoleCommand {
    protected final StationService stationService;
    protected final Scanner scanner;

    public StationCommand(String name, StationService stationService, Scanner scanner) {
        super(name);
        this.stationService = stationService;
        this.scanner = scanner;
    }
}
