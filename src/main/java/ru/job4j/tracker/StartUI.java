package ru.job4j.tracker;

/**
 * Class StartUI
 * Класс реализует меню заявок.
 * @author Dmitry Razumov
 * @version 2
 */
public class StartUI {
    /**
     * Метод обрабатывает действия пользователя при работе с меню.
     * @param input Объект для считывания ответов пользователя
     * @param tracker Объект Tracker обрабатывает действия пользователя при работе с заявками
     * @param actions Массив объектов, выполняющих различные действия над заявками.
     */
    public void init(Input input, Tracker tracker, UserAction[] actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ", actions.length);
            UserAction action = actions[select];
            run = action.execute(input, tracker);
        }
    }

    /**
     * Метод отображает меню приложения на экране.
     * @param actions Массив объектов, содержащими наименование пунктов меню.
     */
    private void showMenu(UserAction[] actions) {
        System.out.println(System.lineSeparator() + "Menu.");
        for (int index = 0; index < actions.length; index++) {
            System.out.println(index + ". " + actions[index].name());
        }
    }

    /**
     * Главный метод программы. Создает массив действий для польователя. Запускает выполнение приложения.
     * @param args Параметры командной строки
     */
    public static void main(String[] args) {
        Input validate = new ValidateInput();
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(),
                new FindAllAction(),
                new ReplaceAction(),
                new DeleteAction(),
                new FindByIdAction(),
                new FindByNameAction(),
                new ExitAction()
        };
        new StartUI().init(validate, tracker, actions);
    }
}
