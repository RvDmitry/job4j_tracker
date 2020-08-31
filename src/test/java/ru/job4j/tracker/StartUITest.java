package ru.job4j.tracker;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringJoiner;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class StartUITest
 * @author Dmitry Razumov
 * @version 2
 */
public class StartUITest {
    /**
     * Тест проверяет загрузку меню и выбор пункта меню пользователем.
     * А также иммитирует выполнение действия при выборе пункта меню.
     */
    @Test
    public void whenExit() {
        StubInput input = new StubInput(
                new String[] {"0"}
        );
        StubAction action = new StubAction();
        new StartUI().init(input, new SqlTracker(null), Arrays.asList(action));
        assertThat(action.isCall(), is(true));
    }

    /**
     * Тестируется вывод меню на консоль.
     */
    @Test
    public void whenPrtMenu() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        StubInput input = new StubInput(
                new String[] {"0"}
        );
        StubAction action = new StubAction();
        new StartUI().init(input, new SqlTracker(null), Arrays.asList(action));
        String expect = new StringJoiner(System.lineSeparator(), System.lineSeparator(), System.lineSeparator())
                .add("Menu.")
                .add("0. Stub action")
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }
}