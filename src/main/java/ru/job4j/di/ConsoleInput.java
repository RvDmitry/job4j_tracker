package ru.job4j.di;

import java.util.Scanner;

/**
 * Class ConsoleInput
 * Класс считывает введенные пользователем данные в консоли.
 * @author Dmitry Razumov
 * @version 1
 */
public class ConsoleInput {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Метод задает вопрос пользователю и считывает введенную им строку в консоли.
     * @param question Вопрос пользователю.
     * @return Введенная пользователем строка.
     */
    public String askStr(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }
}
