package ru.job4j.tracker;

import java.util.List;

/**
 * Class FindByNameAction
 * Класс отвечает за поиск заявки по ее имени и вывода соответствующего пункта меню.
 * @author Dmitry Razumov
 * @version 1
 */
public class FindByNameAction implements UserAction{
    /**
     * Метод возвращает наименование пункта меню.
     * @return Наименование пункта меню
     */
    @Override
    public String name() {
        return "Find items by name";
    }

    /**
     * Метод осуществляет поиск заявки по ее имени и выводит ее на экран.
     * @param input Объект для считывания ответов пользователя
     * @param tracker Объект Tracker обрабатывает действия пользователя при работе с заявками
     * @return true, если операция выполнена успешно, иначе false
     */
    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println("=== Find items by name ===");
        String name =  input.askStr("Enter Item name: ");
        List<Item> items = tracker.findByName(name);
        if (items.size() == 0) {
            System.out.println("No item with this name.");
        } else {
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println("Done.");
        }
        return true;
    }
}
