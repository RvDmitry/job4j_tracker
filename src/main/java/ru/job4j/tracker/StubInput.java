package ru.job4j.tracker;

/**
 * Class StubInput
 * Класс реализует интерфейс Input. Иммитирует взаимодействие пользователя с объектом Scanner.
 * @author Dmitry Razumov
 * @version 1
 */
public class StubInput implements Input {
    /**
     * Поле задает массив ответов пользователя.
     */
    private String[] answers;

    /**
     * Поле задает счетчик элементов массива.
     */
    private int position = 0;

    /**
     * Конструктор инициализирует массив ответов пользователя.
     * @param answers Массив ответов пользователя
     */
    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * Метод иммитирует ответы пользователя, возвращяя заранее переданный массив ответов поэлементно при вызове метода.
     * @param question Вопрос на который нужно ответить пользователю
     * @return Элементы массива ответов
     */
    @Override
    public String askStr(String question) {
        return answers[position++];
    }

    /**
     * Метод иммитирует ответы пользователя, возвращяя заранее переданный массив ответов поэлементно при вызове метода.
     * @param question Вопрос на который нужно ответить пользователю
     * @return Элементы массива ответов
     */
    @Override
    public int askInt(String question) {
        return Integer.valueOf(askStr(question));
    }

    /**
     * Метод иммитирует ответы пользователя, возвращяя заранее переданный массив ответов поэлементно при вызове метода.
     * @param question Вопрос на который нужно ответить пользователю
     * @param max Максимальное число, которое может ввести пользователь
     * @return Элементы массива ответов
     */
    @Override
    public int askInt(String question, int max) {
        int select = askInt(question);
        if (select >= 0 && select < max) {
            return select;
        } else {
            throw new IllegalStateException(String.format("Out of about %s > [0, %s]", select, max));
        }
    }
}
