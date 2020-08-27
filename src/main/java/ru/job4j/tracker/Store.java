package ru.job4j.tracker;

import java.util.List;

/**
 * Interface Store
 * Интерфейс определяет методы взаимодействия с базой данных.
 * @author Dmitry Razumov
 * @version 1
 */
public interface Store extends AutoCloseable {
    void init();
    Item add(Item item);
    boolean replace(String id, Item item);
    boolean delete(String id);
    List<Item> findAll();
    List<Item> findByName(String key);
    Item findById(String id);
}
