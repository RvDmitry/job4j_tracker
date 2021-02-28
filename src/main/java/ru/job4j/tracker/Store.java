package ru.job4j.tracker;

import java.util.List;

/**
 * Interface Store
 * Интерфейс определяет методы взаимодействия с базой данных.
 * @author Dmitry Razumov
 * @version 1
 */
public interface Store extends AutoCloseable {
    Item add(Item item);
    boolean replace(int id, Item item);
    boolean delete(int id);
    List<Item> findAll();
    List<Item> findByName(String key);
    Item findById(int id);
}
