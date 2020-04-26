package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenFindAllItems() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1");
        tracker.add(item1);
        Item item2 = new Item("test2");
        tracker.add(item2);
        Item item3 = new Item("test3");
        tracker.add(item3);
        String[] expected = {item1.getName(), item2.getName(), item3.getName()};
        Item[] items = tracker.findAll();
        String[] result = new String[items.length];
        for (int i = 0; i < items.length; i++) {
            result[i] = items[i].getName();
        }
        assertThat(result, is(expected));
    }

    @Test
    public void whenFindByNameItems() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1");
        tracker.add(item1);
        Item item2 = new Item("test2");
        tracker.add(item2);
        Item item3 = new Item("test1");
        tracker.add(item3);
        Item item4 = new Item("test4");
        tracker.add(item4);
        String[] expected = {item1.getName(), item3.getName()};
        Item[] items = tracker.findByName("test1");
        String[] result = new String[items.length];
        for (int i = 0; i < items.length; i++) {
            result[i] = items[i].getName();
        }
        assertThat(result, is(expected));
    }
}