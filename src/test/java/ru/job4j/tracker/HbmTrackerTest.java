package ru.job4j.tracker;

import org.junit.Test;

import static org.junit.Assert.*;

public class HbmTrackerTest {

    @Test
    public void whenAddAndFindById() {
        HbmTracker hbmTracker = new HbmTracker();
        Item item = new Item("Item 1");
        item.setDescription("Desc 1");
        hbmTracker.add(item);
        assertEquals(item.getName(), hbmTracker.findById(item.getId()).getName());
        assertEquals(item.getDescription(), hbmTracker.findById(item.getId()).getDescription());
    }

    @Test
    public void whenAddAndFindAll() {
        HbmTracker hbmTracker = new HbmTracker();
        Item first = new Item("Item 1");
        first.setDescription("Desc 1");
        Item second = new Item("Item 2");
        second.setDescription("Desc 2");
        hbmTracker.add(first);
        hbmTracker.add(second);
        assertEquals(2, hbmTracker.findAll().size());
    }

    @Test
    public void whenAddAndDelete() {
        HbmTracker hbmTracker = new HbmTracker();
        Item first = new Item("Item 1");
        first.setDescription("Desc 1");
        Item second = new Item("Item 2");
        second.setDescription("Desc 2");
        hbmTracker.add(first);
        hbmTracker.add(second);
        assertTrue(hbmTracker.delete(second.getId()));
        assertEquals(1, hbmTracker.findAll().size());
    }

    @Test
    public void whenAddAndFindByName() {
        HbmTracker hbmTracker = new HbmTracker();
        Item first = new Item("Item 1");
        first.setDescription("Desc 1");
        Item second = new Item("Item 2");
        second.setDescription("Desc 2");
        hbmTracker.add(first);
        hbmTracker.add(second);
        assertEquals(first.getName(), hbmTracker.findByName(first.getName()).get(0).getName());
    }

    @Test
    public void whenReplace() {
        HbmTracker hbmTracker = new HbmTracker();
        Item first = new Item("Item 1");
        first.setDescription("Desc 1");
        first = hbmTracker.add(first);
        Item second = new Item("Item 2");
        second.setDescription("Desc 2");
        assertTrue(hbmTracker.replace(first.getId(), second));
        assertEquals(0, hbmTracker.findByName(first.getName()).size());
        assertEquals(second.getName(), hbmTracker.findByName(second.getName()).get(0).getName());
    }
}