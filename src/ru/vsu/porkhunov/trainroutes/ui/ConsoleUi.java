package ru.vsu.porkhunov.trainroutes.ui;

import ru.vsu.porkhunov.trainroutes.ui.command.ConsoleCommand;

import java.util.Map;
import java.util.Scanner;

public class ConsoleUi {
    private final Map<Integer, ConsoleCommand> commands;
    private final Scanner scanner;

    public ConsoleUi(Map<Integer, ConsoleCommand> commands, Scanner scanner) {
        this.commands = commands;
        this.scanner = scanner;
    }

    public void start() {
        System.out.println("Добро пожаловать в программу управления маршрутами поездов!");

        for (int command = 0; command >= 0 && command < commands.size(); command = scanner.nextInt() - 1) {
            commands.get(command).execute();

            System.out.print("Выберите команду: ");
        }

        System.out.println("\nСпасибо за использование нашей программы! До свидания!");
    }
}
