package ru.job4j.tracker;

import java.util.Scanner;

/**
 * Class ConsoleInput
 * Класс реализует интерфейс Input. Осуществляет взаимодействие с объектом Scanner.
 * @author Dmitry Razumov
 * @version 1
 */
public class ConsoleInput implements Input {
    /**
     * Поле создает и инициализирует объект Scanner.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Метод задает пользователю вопрос и возвращает введенный пользователем ответ в виде строки.
     * @param question Вопрос на который нужно ответить пользователю
     * @return Строка введенная пользователем
     */
    @Override
    public String askStr(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    /**
     * Метод задает пользователю вопрос и возвращает введенный пользователем ответ в виде числа.
     * @param question Вопрос на который нужно ответить пользователю
     * @return Число введенное пользователем
     */
    @Override
    public int askInt(String question) {
        return Integer.valueOf(askStr(question));
    }
}
