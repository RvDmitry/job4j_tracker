package ru.job4j.tracker;

/**
 * Class ReplaceAction
 * Класс отвечает за редактирование заявки и вывода соответствующего пункта меню.
 * @author Dmitry Razumov
 * @version 1
 */
public class ReplaceAction implements UserAction {
    /**
     * Метод возвращает наименование пункта меню.
     * @return Наименование пункта меню
     */
    @Override
    public String name() {
        return "Edit item";
    }

    /**
     * Метод выполняет редактирование заявки.
     * @param input Объект для считывания ответов пользователя
     * @param tracker Объект Tracker обрабатывает действия пользователя при работе с заявками
     * @return true, если операция выполнена успешно, иначе false
     */
    @Override
    public boolean execute(Input input, Store tracker) {
        System.out.println("=== Edit item ===");
        int id =  input.askInt("Enter Item Id you will edit: ");
        String name =  input.askStr("Enter new name: ");
        Item item = new Item(name);
        if (tracker.replace(id, item)) {
            System.out.println("Item successfully edited");
        } else {
            System.out.println("Item with this Id was not found.");
        }
        return true;
    }
}
