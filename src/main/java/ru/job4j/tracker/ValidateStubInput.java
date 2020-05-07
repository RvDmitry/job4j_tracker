package ru.job4j.tracker;

/**
 * Class ValidateStubInput
 * Класс иммитирует ввод данных пользователем.
 * @author Dmitry Razumov
 * @version 1
 */
public class ValidateStubInput extends ValidateInput {
    /**
     * Поле задает массив ответов пользователя.
     */
    private String[] data;

    /**
     * Поле задает счетчик элементов массива.
     */
    private int position;

    /**
     * Конструктор инициализирует массив ответов пользователя.
     * @param data Массив ответов пользователя
     */
    public ValidateStubInput(String[] data) {
        this.data = data;
    }

    /**
     * Метод иммитирует ответы пользователя, возвращяя заранее переданный массив ответов поэлементно при вызове метода.
     * @param question Вопрос на который нужно ответить пользователю
     * @return Элементы массива ответов
     */
    @Override
    public String askStr(String question) {
        return data[position++];
    }
}
