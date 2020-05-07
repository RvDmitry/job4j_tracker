package ru.job4j.tracker;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Class ValidateInputTest
 * @author Dmitry Razumov
 * @version 1
 */
public class ValidateInputTest {
    /**
     * Тест проверяет валидацию на правильность ввода пользователем числа.
     * Метод ValidateStubInput иммитирует ввод данных пользователем.
     * Если пользователь ввел не число, метод ValidateInput снова запрашивает ввод.
     */
    @Test
    public void whenInvalidInput() {
        ByteArrayOutputStream mem = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(mem));
        String[] data = {"one", "1"};
        ValidateInput input = new ValidateStubInput(data);
        input.askInt("Enter");
        assertThat(
                mem.toString(),
                is(String.format("Please enter validate data again.%n"))
        );
        System.setOut(out);
    }

    /**
     * Тест проверяет валидацию на правильность ввода пользователем числа.
     * Метод ValidateStubInput иммитирует ввод данных пользователем.
     * Если пользователь ввел число больше либо равное максимальному,
     * метод ValidateInput снова запрашивает ввод.
     */
    @Test
    public void whenMaxErrorInput() {
        ByteArrayOutputStream mem = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(mem));
        String[] data = {"4", "1"};
        ValidateInput input = new ValidateStubInput(data);
        input.askInt("Enter", 2);
        assertThat(
                mem.toString(),
                is(String.format("Please select key from menu.%n"))
        );
        System.setOut(out);
    }
}