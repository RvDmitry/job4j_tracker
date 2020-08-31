package ru.job4j.tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Class SqlTrackerTest
 * Класс тестирует взаимодействие с базой данных.
 * @author Dmitry Razumov
 * @version 1
 */
public class SqlTrackerTest {

    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name"));
            assertThat(tracker.findByName("name").size(), is(1));
        }
    }

    @Test
    public void findAllItems() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name 1"));
            tracker.add(new Item("name 2"));
            tracker.add(new Item("name 3"));
            assertThat(tracker.findAll().size(), is(3));
        }
    }

    @Test
    public void findByNameItems() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name 1"));
            tracker.add(new Item("name 2"));
            tracker.add(new Item("name 3"));
            tracker.add(new Item("name 2"));
            assertThat(tracker.findByName("name 2").size(), is(2));
        }
    }

    @Test
    public void findByIdItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = tracker.add(new Item(("name")));
            assertThat(tracker.findById(item.getId()).getName(), is("name"));
        }
    }

    @Test
    public void replaceItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = tracker.add(new Item("name 1"));
            tracker.replace(item.getId(), new Item("name 2"));
            assertThat(tracker.findById(item.getId()).getName(), is("name 2"));
        }
    }

    @Test
    public void  deleteItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item = tracker.add(new Item(("name")));
            tracker.delete(item.getId());
            assertNull(tracker.findById(item.getId()));
        }
    }
}