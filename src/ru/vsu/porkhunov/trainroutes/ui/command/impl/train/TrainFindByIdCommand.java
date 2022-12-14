package ru.vsu.porkhunov.trainroutes.ui.command.impl.train;

import ru.vsu.porkhunov.trainroutes.service.TrainService;
import ru.vsu.porkhunov.trainroutes.service.exception.EntityNotFoundException;

import java.util.Scanner;

public class TrainFindByIdCommand extends TrainCommand {
    private static final String COMMAND_NAME = "Получение поезда по ID";

    public TrainFindByIdCommand(TrainService trainService, Scanner scanner) {
        super(COMMAND_NAME, trainService, scanner);
    }

    @Override
    protected String doExecute() {
        System.out.print("Введите ID поезда: ");
        long id = scanner.nextLong();

        try {
            return trainService.findById(id).toString();
        } catch (EntityNotFoundException e) {
            return String.format("Поезда с ID %d не существует%n", id);
        }
    }
}
