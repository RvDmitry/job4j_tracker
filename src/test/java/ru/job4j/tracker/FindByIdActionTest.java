package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class FindByIdActionTest
 * @author Dmitry Razumov
 * @version 1
 */
public class FindByIdActionTest {

    @Test
    public void whenCheckOutputWithMock() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        Store tracker = new MemTracker();
        Item item = new Item("Simple item");
        tracker.add(item);
        FindByIdAction find = new FindByIdAction();
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(item.getId());
        find.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find item by Id ====" + ln + item + ln + "Done." + ln));
        assertThat(tracker.findAll().get(0).getName(), is("Simple item"));
        System.setOut(def);
    }
}