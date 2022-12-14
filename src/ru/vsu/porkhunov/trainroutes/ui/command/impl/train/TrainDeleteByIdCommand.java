package ru.vsu.porkhunov.trainroutes.ui.command.impl.train;

import ru.vsu.porkhunov.trainroutes.service.TrainService;
import ru.vsu.porkhunov.trainroutes.service.exception.EntityNotFoundException;

import java.util.Scanner;

public class TrainDeleteByIdCommand extends TrainCommand {
    private static final String COMMAND_NAME = "Удаление поезда по ID";

    public TrainDeleteByIdCommand(TrainService trainService, Scanner scanner) {
        super(COMMAND_NAME, trainService, scanner);
    }

    @Override
    protected String doExecute() {
        System.out.print("Введите ID поезда: ");
        long id = scanner.nextLong();

        try {
            trainService.deleteById(id);
        } catch (EntityNotFoundException e) {
            return String.format("Поезда с ID %d не существует", id);
        }

        return String.format("Поезд с ID %d успешно удален", id);
    }
}
