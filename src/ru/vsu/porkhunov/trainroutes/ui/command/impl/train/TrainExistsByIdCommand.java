package ru.vsu.porkhunov.trainroutes.ui.command.impl.train;

import ru.vsu.porkhunov.trainroutes.service.TrainService;

import java.util.Scanner;

public class TrainExistsByIdCommand extends TrainCommand {
    private static final String COMMAND_NAME = "Проверка существования поезда по ID";

    public TrainExistsByIdCommand(TrainService trainService, Scanner scanner) {
        super(COMMAND_NAME, trainService, scanner);
    }

    @Override
    protected String doExecute() {
        System.out.print("Введите ID поезда: ");
        long id = scanner.nextLong();

        return trainService.existsById(id) ?
                String.format("Поезд с ID %d существует", id) :
                String.format("Поезда с ID %d не существует", id);
    }
}
