package ru.job4j.di;

/**
 * Class StartUI
 *
 * @author Dmitry Razumov
 * @version 1
 */
public class StartUI {

    private Store store;
    private ConsoleInput input;

    public StartUI(Store store, ConsoleInput input) {
        this.store = store;
        this.input = input;
    }

    /**
     * Метод добавляет строки в хранилище.
     */
    public void add() {
        String value = input.askStr("Введите строку: ");
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
