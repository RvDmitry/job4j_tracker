package ru.job4j.tracker;

/**
 * Class CreateAction
 * Класс отвечает за создание новой заявки и вывода соответствующего пункта меню.
 * @author Dmitry Razumov
 * @version 1
 */
public class CreateAction implements UserAction {
    /**
     * Метод возвращает наименование пункта меню.
     * @return Наименование пункта меню
     */
    @Override
    public String name() {
        return "Add new Item";
    }

    /**
     * Метод создает новую заявку.
     * @param input Объект для считывания ответов пользователя
     * @param tracker Объект Tracker обрабатывает действия пользователя при работе с заявками
     * @return true, если операция выполнена успешно, иначе false
     */
    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println("=== Create a new Item ====");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        tracker.add(item);
        System.out.println("Done.");
        return true;
    }
}
