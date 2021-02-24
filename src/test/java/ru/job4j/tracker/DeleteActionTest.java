package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class DeleteActionTest
 * @author Dmitry Razumov
 * @version 1
 */
public class DeleteActionTest {

    @Test
    public void whenCheckOutputWithMock() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        Store tracker = new MemTracker();
        Item item = new Item("Deleted item");
        tracker.add(item);
        DeleteAction del = new DeleteAction();
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(item.getId());
        del.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Delete item ====" + ln + "Item successfully deleted" + ln));
        assertThat(tracker.findAll().size(), is(0));
        System.setOut(def);
    }
}