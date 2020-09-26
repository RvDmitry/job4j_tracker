package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class StartUI
 * Класс реализует меню заявок.
 * @author Dmitry Razumov
 * @version 2
 */
public class StartUI {
    private static List<String> list = new ArrayList<>();
    /**
     * Метод обрабатывает действия пользователя при работе с меню.
     * @param input Объект для считывания ответов пользователя
     * @param tracker Объект Tracker обрабатывает действия пользователя при работе с заявками
     * @param actions Массив объектов, выполняющих различные действия над заявками.
     */
    public void init(Input input, Store tracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ", actions.size());
            UserAction action = actions.get(select);
            run = action.execute(input, tracker);
            for (int i = 0; i < 1000000; i++) {
                list.add("qqqqqqqqqqqqwwwwwwsssssssssssssswwwwwwwwwww");
            }
        }
    }

    /**
     * Метод отображает меню приложения на экране.
     * @param actions Список объектов, содержащих наименование пунктов меню.
     */
    private void showMenu(List<UserAction> actions) {
        System.out.println(System.lineSeparator() + "Menu.");
        for (int index = 0; index < actions.size(); index++) {
            System.out.println(index + ". " + actions.get(index).name());
        }
    }

    /**
     * Главный метод программы. Создает список действий для польователя. Запускает выполнение приложения.
     * @param args Параметры командной строки
     */
    public static void main(String[] args) {
        Input validate = new ValidateInput(new ConsoleInput());
        try (Store tracker = new SqlTracker(null)) {
            tracker.init();
            List<UserAction> actions = Arrays.asList(
                    new CreateAction(),
                    new FindAllAction(),
                    new ReplaceAction(),
                    new DeleteAction(),
                    new FindByIdAction(),
                    new FindByNameAction(),
                    new ExitAction()
            );
            new StartUI().init(validate, tracker, actions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
