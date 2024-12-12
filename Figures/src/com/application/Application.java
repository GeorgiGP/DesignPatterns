package com.application;

import com.exceptions.InvalidCommand;
import com.factory.AbstractFactory;
import com.collection.FigureCollection;

import java.util.Scanner;

public class Application {
    private void validateExit(String exit) {
        if (exit.strip().equalsIgnoreCase("exit")) {
            System.exit(0);
        }
    }

    private void exitMessage() {
        System.out.println("To exit program: exit");
    }

    private boolean validateRestart(String exit) {
        return exit.strip().equalsIgnoreCase("restart");
    }

    private void restartMessage() {
        System.out.println("To restart program: restart");
    }

    private void openingMenuMessage() {
        System.out.println("Welcome to Figure Design Patterns!");
        System.out.println("Choose one of the following reading options:");
    }

    private void readingFiguresOptionMessage() {
        System.out.println("1. To input: SystemIN");
        System.out.println("2. From file: File <filename>");
        System.out.println("3. Random Figures: Random");
        exitMessage();
    }

    private void operationsMessage() {
        System.out.println("1. Display all figures: SystemOUT");
        System.out.println("2. Delete a figure from the list: Delete <index>");
        System.out.println("3. Duplicate a figure in the list: Copy <index>");
        System.out.println("4. Store the resulting list into a file: File <filename>");
        System.out.println("5. Display perimeter: Perimeter <index>");
        exitMessage();
        restartMessage();
    }

    private void command(FigureCollection figures, String command) {
        command = command.strip().toLowerCase();
        Scanner scanner = new Scanner(command);
        try {
            String input = scanner.next();
            switch (input) {
                case "systemout" -> figures.display();
                case "delete" -> {
                    int idxDelete = Integer.parseInt(scanner.next());
                    figures.delete(idxDelete);
                } case "copy" -> {
                    int idxCopy = Integer.parseInt(scanner.next());
                    figures.copy(idxCopy);
                } case "file" -> {
                    input = scanner.next();
                    figures.toFile(input);
                } case "perimeter" -> {
                    int idxPer = Integer.parseInt(scanner.next());
                    System.out.println(figures.perimeter(idxPer));
                } default -> throw new IllegalArgumentException("Invalid command");
            }
        } catch (Exception e) {
            throw new InvalidCommand(e.getMessage(), e.getCause());
        }
    }

    private void controlMenuForCommands(FigureCollection figures) {
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                operationsMessage();
                String option = in.nextLine();
                validateExit(option);
                if (validateRestart(option)) {
                    break;
                }
                command(figures, option);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        run();
    }

    public void run() {
        openingMenuMessage();
        Scanner in = new Scanner(System.in);
        AbstractFactory factory = null;
        FigureCollection figures = null;
        while (factory == null) {
            try {
                readingFiguresOptionMessage();
                String option = in.nextLine();
                validateExit(option);
                factory = new AbstractFactory(option);
                figures = factory.collection();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                factory = null;
            }
        }
        controlMenuForCommands(figures);
    }
}
