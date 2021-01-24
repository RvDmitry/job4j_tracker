package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class FindByNameActionTest
 * @author Dmitry Razumov
 * @version 1
 */
public class FindByNameActionTest {

    @Test
    public void whenCheckOutput() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        Store tracker = new MemTracker();
        Item item = new Item("Java Hot");
        tracker.add(item);
        FindByNameAction act = new FindByNameAction();
        act.execute(new StubInput(new String[] {"Java Hot"}), tracker);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("=== Find items by name ===")
                .add(item.toString())
                .add("Done.")
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }

    @Test
    public void whenCheckOutputWithMock() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        Store tracker = new MemTracker();
        Item item = new Item("Simple item");
        tracker.add(item);
        FindByNameAction find = new FindByNameAction();
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(item.getName());
        find.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find items by name ===" + ln + item + ln + "Done." + ln));
        assertThat(tracker.findAll().get(0).getName(), is("Simple item"));
        System.setOut(def);
    }
}