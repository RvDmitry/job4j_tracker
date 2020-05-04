package ru.job4j.tracker;

/**
 * Class StubAction
 * Класс иммитирует некоторый пункт меню и выполнение некоторого действия при его выборе.
 * @author Dmitry Razumov
 * @version 1
 */
public class StubAction implements UserAction {
    /**
     * Поле задает статус пункта меню.
     */
    private boolean call = false;

    /**
     * Метод возвращает наименование пункта меню.
     * @return Наименование пункта меню
     */
    @Override
    public String name() {
        return "Stub action";
    }

    /**
     * Метод меняет статус состояния меню при его выборе.
     * @param input Объект для считывания ответов пользователя
     * @param tracker Объект Tracker обрабатывает действия пользователя при работе с заявками
     * @return false
     */
    @Override
    public boolean execute(Input input, Tracker tracker) {
        call = true;
        return false;
    }

    /**
     * Метод возвращает статус выбора пункта меню. Было ли выполнено действие при выборе пункта меню.
     * @return true, если действие было выполнено, иначе false
     */
    public boolean isCall() {
        return call;
    }
}
