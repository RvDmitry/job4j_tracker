package ru.job4j.tracker;

import java.util.List;

/**
 * Class FindAllAction
 * Класс отвечает за поиск всех заявок и вывода соответствующего пункта меню.
 * @author Dmitry Razumov
 * @version 1
 */
public class FindAllAction implements UserAction {
    /**
     * Метод возвращает наименование пункта меню.
     * @return Наименование пункта меню
     */
    @Override
    public String name() {
        return "Show all items";
    }

    /**
     * Метод выводит все заявки на экран.
     * @param input Объект для считывания ответов пользователя
     * @param tracker Объект Tracker обрабатывает действия пользователя при работе с заявками
     * @return true, если операция выполнена успешно, иначе false
     */
    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println("=== All Items ====");
        List<Item> items = tracker.findAll();
        for (Item item :items) {
            System.out.println(item);
        }
        System.out.println("Done.");
        return true;
    }
}
