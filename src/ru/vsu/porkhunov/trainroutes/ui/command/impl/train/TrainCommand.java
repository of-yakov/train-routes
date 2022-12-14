package ru.vsu.porkhunov.trainroutes.ui.command.impl.train;

import ru.vsu.porkhunov.trainroutes.service.TrainService;
import ru.vsu.porkhunov.trainroutes.ui.command.ConsoleCommand;

import java.util.Scanner;

public abstract class TrainCommand extends ConsoleCommand {
    protected final TrainService trainService;
    protected final Scanner scanner;

    public TrainCommand(String name, TrainService trainService, Scanner scanner) {
        super(name);
        this.trainService = trainService;
        this.scanner = scanner;
    }
}
