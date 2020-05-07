package ru.job4j.tracker;

/**
 * Class ValidateInput
 * Класс выполняет валидацию введенных пользователем значений.
 * @author Dmitry Razumov
 * @version 1
 */
public class ValidateInput implements Input {
    /**
     * Поле содержит источник данных.
     */
    private final Input input;

    /**
     * Конструктор инициализирует источник данных.
     * @param input Класс - источник данных
     */
    public ValidateInput(Input input) {
        this.input = input;
    }

    /**
     * Метод, вызывает метод класса источника данных для выполнения требуемых действий.
     * @param question Вопрос на который нужно ответить пользователю
     * @return Ответ на заданный вопрос
     */
    @Override
    public String askStr(String question) {
        return input.askStr(question);
    }

    /**
     * Метод осуществляет валидацию введенного пользователем числа.
     * @param question Вопрос на который нужно ответить пользователю
     * @return Число введенное пользователем
     */
    @Override
    public int askInt(String question) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = input.askInt(question);
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }

    /**
     * Метод осуществляет валидацию введенного пользователем числа.
     * @param question Вопрос на который нужно ответить пользователю
     * @param max Максимальное число, которое может ввести пользователь
     * @return Число введенное пользователем
     */
    @Override
    public int askInt(String question, int max) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = input.askInt(question, max);
                invalid = false;
            } catch (IllegalStateException moe) {
                System.out.println("Please select key from menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }
}
