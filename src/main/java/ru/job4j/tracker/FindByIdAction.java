package ru.job4j.tracker;

/**
 * Class FindByIdAction
 * Класс отвечает за поиск заявки по ее идентификационному номеру и вывода соответствующего пункта меню.
 * @author Dmitry Razumov
 * @version 1
 */
public class FindByIdAction implements UserAction {
    /**
     * Метод возвращает наименование пункта меню.
     * @return Наименование пункта меню
     */
    @Override
    public String name() {
        return "Find item by Id";
    }

    /**
     * Метод осуществляет поиск заявки по ее идентификационному номеру и выводит ее на экран.
     * @param input Объект для считывания ответов пользователя
     * @param tracker Объект Tracker обрабатывает действия пользователя при работе с заявками
     * @return true, если операция выполнена успешно, иначе false
     */
    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println("=== Find item by Id ====");
        String id =  input.askStr("Enter Item Id: ");
        Item item = tracker.findById(id);
        if (item == null) {
            System.out.println("No item with this Id.");
        } else {
            System.out.println(item);
            System.out.println("Done.");
        }
        return true;
    }
}
