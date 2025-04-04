package lab5.utility;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import lab5.exceptions.TooFewArgumentsException;
import lab5.exceptions.TooManyArgumentsException;
import lab5.exceptions.UnknownCommandException;
import lab5.managers.CollectionManager;
import lab5.managers.CommandManager;
import lab5.managers.ConsoleManager;
import lab5.managers.FileManager;

/**
 * Класс, который управляет работой программы.
 * @author Alina
 */
public class Runner {
    public CommandManager commandManager;
    public ConsoleManager consoleManager;
    public CollectionManager collectionManager;
    public FileManager fileManager;
    public Map<String, Command> commands;
    private boolean running = false;
    private RunningMode currentMode;
    public HashSet<String> scripts = new HashSet<>();;

    /**
     * Перечисление кодов завершения выполнения команды.
     */
    public enum ExitCode {
        OK,
        EXIT,
        ERROR
    }

    /**
     * Перечисление режимов работы программы.
     */
    public enum RunningMode {
        INTERACTIVE,
        SCRIPT
    }

    public Runner(CommandManager commandManager, ConsoleManager consoleManager, FileManager fileManager) {
        this.commandManager = commandManager;
        this.consoleManager = consoleManager;
        this.fileManager = fileManager;
        this.commands = commandManager.getCommands();
    }

    /**
     * Запускает команду, которую ввёл пользователь.
     * @param userCommand команда
     */
    public void launchCommand(String[] userCommand) {
        try {
            if (!commands.containsKey(userCommand[0])){
                throw new UnknownCommandException("Не найдена команда " + userCommand[0]);
            }
            if (commands.get(userCommand[0]).getNumberOfArguments() > userCommand.length-1) {
                throw new TooManyArgumentsException("Команда " + userCommand[0] + " должна содержать " 
                + commands.get(userCommand[0]).getNumberOfArguments() + " аргументов");
            }
            if (commands.get(userCommand[0]).getNumberOfArguments() < userCommand.length-1) {
                throw new TooFewArgumentsException("Команда " + userCommand[0] + " должна содержать " 
                + commands.get(userCommand[0]).getNumberOfArguments() + " аргументов");
            }

            ExitCode exitCode = commands.get(userCommand[0]).execute(userCommand);
            if (currentMode == RunningMode.INTERACTIVE) {
                switch (exitCode) {
                    case OK -> {
                        ConsoleManager.println("Команда " + userCommand[0] + " выполнена.");
                        commandManager.addToHistory(userCommand[0]);
                    }
                    case ERROR -> {
                        ConsoleManager.println("При выполнении команды " + userCommand[0] + " произошла ошибка.");
                        ConsoleManager.println("Команда " + userCommand[0] + " не была выполнена.");
                    }
                    case EXIT -> {
                        ConsoleManager.println("Получена команда выхода из программы.");
                        ConsoleManager.println("Завершение работы программы.");
                        running = false;
                    }
                }
            }
            else {
                switch (exitCode) {
                    case OK -> {
                        ConsoleManager.println("Команда " + userCommand[0] + " выполнена.");
                        commandManager.addToHistory(userCommand[0]);
                    }
                    case ERROR -> {
                        ConsoleManager.println("При выполнении команды " + userCommand[0] + " произошла ошибка.");
                        ConsoleManager.println("Команда " + userCommand[0] + " не была выполнена.");
                    }
                    case EXIT -> {
                        ConsoleManager.println("Получена команда выхода из программы.");
                        ConsoleManager.println("Завершение работы программы.");
                        running = false;
                    }
                }
            }
        } catch (UnknownCommandException e) {
            if (currentMode == RunningMode.INTERACTIVE) {
                ConsoleManager.printError(e.getMessage());
                ConsoleManager.println("Для получения списка команд введите \"help\".");
            }
        } catch (TooManyArgumentsException | TooFewArgumentsException e) {
            ConsoleManager.printError(e.getMessage());
        }
    }

    /**
     * Управляет работой программы.
     */
    public void run() {
        running = true;
        currentMode = RunningMode.INTERACTIVE;
        if (!fileManager.isFileExist(fileManager.getCollectionFilePath())) {
            ConsoleManager.printError("Не найден файл " + fileManager.getCollectionFilePath());
            ConsoleManager.println("Создана пустая коллекция.");
            collectionManager = new CollectionManager(new HashMap<>());
        }
        else {
            try {
                collectionManager = new CollectionManager(fileManager.readCollection());
            } catch (IOException e) {
                ConsoleManager.printError("Невозможно прочитать коллекцию из файла " + fileManager.getCollectionFilePath());
                ConsoleManager.println("Создана пустая коллекция.");
                collectionManager = new CollectionManager(new HashMap<>());
            }
        }
        ConsoleManager.greeting();

        while(running) {
            String[] currenrCommand = ConsoleManager.askCommand();
            if (currenrCommand != null) {
                launchCommand(currenrCommand);
            }
            else {
                ConsoleManager.println("Завершение работы программы.");
                running = false;
            }
        }
    }

    public boolean getRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public RunningMode getCurrentMode() {
        return currentMode;
    }

    public void setCurrentMode(RunningMode currentMode) {
        this.currentMode = currentMode;
    }
}
