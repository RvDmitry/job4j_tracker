package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class ItemCompareIncreaseTest
 * Класс тестирует сортировку.
 * @author Dmitry Razumov
 * @version 1
 */
public class ItemCompareIncreaseTest {
    /**
     * Метод тестирует сортировку по возрастанию.
     */
    @Test
    public void compare() {
        Item a = new Item("a");
        a.setId("1");
        Item b = new Item("b");
        b.setId("2");
        Item c = new Item("c");
        c.setId("3");
        List<Item> items = new ArrayList<>();
        items.add(b);
        items.add(c);
        items.add(a);
        List<Item> expected = new ArrayList<>();
        expected.add(a);
        expected.add(b);
        expected.add(c);
        Collections.sort(items, new ItemCompareIncrease());
        assertThat(items, is(expected));
    }
}