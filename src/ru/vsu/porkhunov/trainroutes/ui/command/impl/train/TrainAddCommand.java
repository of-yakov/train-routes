package ru.vsu.porkhunov.trainroutes.ui.command.impl.train;

import ru.vsu.porkhunov.trainroutes.entity.Train;
import ru.vsu.porkhunov.trainroutes.service.TrainService;

import java.util.Scanner;

public class TrainAddCommand extends TrainCommand {
    private static final String COMMAND_NAME = "Добавление нового поезда";

    public TrainAddCommand(TrainService trainService, Scanner scanner) {
        super(COMMAND_NAME, trainService, scanner);
    }

    @Override
    protected String doExecute() {
        System.out.print("Введите наименование поезда: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        Train train = trainService.add(new Train(name));

        return "Поезд успешно добавлен\n" + train.toString();
    }
}
