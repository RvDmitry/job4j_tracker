package ru.job4j.tracker;

import org.junit.Test;
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
        new StartUI().init(input, new Tracker(), new UserAction[] { action });
        assertThat(action.isCall(), is(true));
    }
}