package ru.job4j.di;

/**
 * Class Main
 * В классе тестируется создание и использование контекста.
 * @author Dmitry Razumov
 * @version 1
 */
public class Main {
    /**
     * Главный метод программы.
     * @param args Параметры командной строки.
     */
    public static void main(String[] args) {
        Context context = new Context();
        context.reg(Store.class);
        context.reg(ConsoleInput.class);
        context.reg(StartUI.class);
        StartUI ui = context.get(StartUI.class);
        ui.add();
        ui.add();
        ui.print();
    }
}
