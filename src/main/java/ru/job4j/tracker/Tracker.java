package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

/**
 * Class Tracker
 * @author Dmitry Razumov
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    /**
     * Массив для хранения заявок.
     */
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод добавления заявки в хранилище.
     * @param item Новая заявка
     */
    public Item add(Item item) {
        item.setId(generateId());
        items[position++] = item;
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * @return Уникальный ключ.
     */
    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    /**
     * Метод возвращает копию массива items без null элементов.
     * @return Копия массива items
     */
    public Item[] findAll() {
        int size = 0;
        Item[] result = new Item[items.length];
        for (Item item : items) {
            if (item != null) {
                result[size++] = item;
            }
        }
        return Arrays.copyOf(result, size);
    }

    /**
     * Метод возвращает массив содержащий заявки с заданным именем.
     * @param name Имя
     * @return Массив заявок
     */
    public Item[] findByName(String name) {
        int size = 0;
        Item[] result = new Item[items.length];
        for (Item item: items) {
            if (item != null && item.getName().equals(name)) {
                result[size++] = item;
            }
        }
        return Arrays.copyOf(result, size);
    }

    /**
     * Метод возвращает заявку по ее уникальному ключу.
     * @param id Ключ
     * @return Заявка
     */
    public Item findById(String id) {
        Item rsl = null;
        for (Item item : items) {
            if (item.getId().equals(id)) {
                rsl = item;
                break;
            }
        }
        return rsl;
    }
}