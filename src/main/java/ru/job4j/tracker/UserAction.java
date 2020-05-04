package ru.job4j.tracker;

/**
 * Interface UserAction
 * Интерфейс определяет наименование пункта меню для выбора действий пользователя
 * и само действие в соответствии с выбранным пунктом меню.
 * @author Dmitry Razumov
 * @version 1
 */
public interface UserAction {
    /**
     * Метод определяет наименование пункта меню.
     * @return Наименование пункта меню
     */
    String name();

    /**
     * Метод определяет действие, которое нужно выполнить в соответствии с выбранным пунктом меню.
     * @param input Объект для считывания ответов пользователя
     * @param tracker Объект Tracker обрабатывает действия пользователя при работе с заявками
     * @return true, если операция выполнена успешно, иначе false
     */
    boolean execute(Input input, Tracker tracker);
}
