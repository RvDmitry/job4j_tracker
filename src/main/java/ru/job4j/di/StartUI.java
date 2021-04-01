package ru.job4j.di;

import org.springframework.stereotype.Component;

/**
 * Class StartUI
 *
 * @author Dmitry Razumov
 * @version 1
 */
@Component
public class StartUI {

    private Store store;
    private ConsoleInput input;

    public StartUI(Store store, ConsoleInput input) {
        this.store = store;
        this.input = input;
    }

    /**
     * Метод добавляет введенную в консоли строку в хранилище.
     */
    public void add() {
        String value = input.askStr("Введите строку: ");
        store.add(value);
    }

    /**
     * Метод добавляет переданную строку в хранилище.
     * @param value Строка.
     */
    public void add(String value) {
        store.add(value);
    }

    /**
     * Метод выводит строки из хранилища на экран.
     */
    public void print() {
        for (String value : store.getAll()) {
            System.out.println(value);
        }
    }
}
