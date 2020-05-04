package ru.job4j.tracker;

/**
 * Class ExitAction
 * Класс отвечает за выход из приложения и вывода соответствующего пункта меню.
 * @author Dmitry Razumov
 * @version 1
 */
public class ExitAction implements UserAction{
    /**
     * Метод возвращает наименование пункта меню.
     * @return Наименование пункта меню
     */
    @Override
    public String name() {
        return "Exit Program";
    }

    /**
     * Метод выполняет выход из программы.
     * Для успешного завершения программы метод должен всегда возвращать false.
     * @param input Объект для считывания ответов пользователя
     * @param tracker Объект Tracker обрабатывает действия пользователя при работе с заявками
     * @return false
     */
    @Override
    public boolean execute(Input input, Tracker tracker) {
        return false;
    }
}
