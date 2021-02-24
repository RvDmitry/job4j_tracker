package ru.job4j.tracker;

/**
 * Class DeleteAction
 * Класс отвечает за удаление заявки и вывода соответствующего пункта меню.
 * @author Dmitry Razumov
 * @version 1
 */
public class DeleteAction implements UserAction {
    /**
     * Метод возвращает наименование пункта меню.
     * @return Наименование пункта меню
     */
    @Override
    public String name() {
        return "Delete item";
    }

    /**
     * Метод выполняет удаление заявки.
     * @param input Объект для считывания ответов пользователя
     * @param tracker Объект Tracker обрабатывает действия пользователя при работе с заявками
     * @return true, если операция выполнена успешно, иначе false
     */
    @Override
    public boolean execute(Input input, Store tracker) {
        System.out.println("=== Delete item ====");
        int id =  input.askInt("Enter Item Id: ");
        if (tracker.delete(id)) {
            System.out.println("Item successfully deleted");
        } else {
            System.out.println("Item with this Id was not found.");
        }
        return true;
    }
}
