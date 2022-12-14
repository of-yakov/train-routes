package ru.vsu.porkhunov.trainroutes.ui.command.impl.train;

import ru.vsu.porkhunov.trainroutes.entity.Train;
import ru.vsu.porkhunov.trainroutes.service.TrainService;
import ru.vsu.porkhunov.trainroutes.service.exception.EntityNotFoundException;

import java.util.Scanner;

public class TrainUpdateByIdCommand extends TrainCommand {
    private static final String COMMAND_NAME = "Обновление данных о поезде по ID";

    public TrainUpdateByIdCommand(TrainService trainService, Scanner scanner) {
        super(COMMAND_NAME, trainService, scanner);
    }

    @Override
    protected String doExecute() {
        System.out.print("Введите ID обновляемого поезда: ");
        long id = scanner.nextLong();

        System.out.print("Введите новое наименование поезда: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        try {
            Train train = trainService.updateById(new Train(name), id);

            return "Поезд успешно обновлен\n" + train.toString();
        } catch (EntityNotFoundException e) {
            return String.format("Поезда с ID %d не существует", id);
        }
    }
}
