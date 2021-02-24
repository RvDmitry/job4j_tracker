package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

public class MemTrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        MemTracker tracker = new MemTracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenFindAllItems() {
        MemTracker tracker = new MemTracker();
        Item item1 = new Item("test1");
        tracker.add(item1);
        Item item2 = new Item("test2");
        tracker.add(item2);
        Item item3 = new Item("test3");
        tracker.add(item3);
        String[] expected = {item1.getName(), item2.getName(), item3.getName()};
        List<Item> items = tracker.findAll();
        String[] result = new String[items.size()];
        for (int i = 0; i < items.size(); i++) {
            result[i] = items.get(i).getName();
        }
        assertThat(result, is(expected));
    }

    @Test
    public void whenFindByNameItems() {
        MemTracker tracker = new MemTracker();
        Item item1 = new Item("test1");
        tracker.add(item1);
        Item item2 = new Item("test2");
        tracker.add(item2);
        Item item3 = new Item("test1");
        tracker.add(item3);
        Item item4 = new Item("test4");
        tracker.add(item4);
        String[] expected = {item1.getName(), item3.getName()};
        List<Item> items = tracker.findByName("test1");
        String[] result = new String[items.size()];
        for (int i = 0; i < items.size(); i++) {
            result[i] = items.get(i).getName();
        }
        assertThat(result, is(expected));
    }

    @Test
    public void whenReplace() {
        MemTracker tracker = new MemTracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        int id = bug.getId();
        Item bugWithDesc = new Item("Bug with description");
        tracker.replace(id, bugWithDesc);
        assertThat(tracker.findById(id).getName(), is("Bug with description"));
    }

    @Test
    public void whenDelete() {
        MemTracker tracker = new MemTracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        int id = bug.getId();
        tracker.delete(id);
        assertThat(tracker.findById(id), is(nullValue()));
    }

    @Test
    public void whenDeleteOfFiveItems() {
        MemTracker tracker = new MemTracker();
        Item item1 = new Item("Item1");
        Item item2 = new Item("Item2");
        Item item3 = new Item("Item3");
        Item item4 = new Item("Item4");
        Item item5 = new Item("Item5");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        tracker.add(item5);
        int id = item2.getId();
        tracker.delete(id);
        String[] expected = {item1.getName(), item3.getName(), item4.getName(), item5.getName()};
        List<Item> items = tracker.findAll();
        String[] result = new String[items.size()];
        for (int i = 0; i < items.size(); i++) {
            result[i] = items.get(i).getName();
        }
        assertThat(result, is(expected));
    }
}