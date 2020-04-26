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
        return Arrays.copyOf(items, position);
    }

    /**
     * Метод возвращает массив содержащий заявки с заданным именем.
     * @param name Имя
     * @return Массив заявок
     */
    public Item[] findByName(String name) {
        int size = 0;
        Item[] result = new Item[items.length];
        for (int i = 0; i < position; i++) {
            if (items[i].getName().equals(name)) {
                result[size++] = items[i];
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
        int index = indexOf(id);
        return index != -1 ? items[index] : null;
    }

    /**
     * Метод находит индекс заявки по ее ключу.
     * @param id Ключ
     * @return Индекс
     */
    private int indexOf(String id) {
        int rsl = -1;
        for (int index = 0; index < position; index++) {
            if (items[index].getId().equals(id)) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    /**
     * Метод осуществляет замену заявки на новую. При этом идентификатор от старой заявки сохраняется.
     * @param id Индентификатор заявки, которую нужно заменить
     * @param item Новая заявка на которую нужно заменить старую
     * @return true, если замена прошла успешно, иначе false
     */
    public boolean replace(String id, Item item) {
        int index  = indexOf(id);
        if (index != -1) {
            item.setId(id);
            items[index] = item;
        }
        return index != -1;
    }

    /**
     * Метод осуществляет удаление заявки с заданным идентификатором.
     * @param id Идентификатор заявки, которую нужно удалить
     * @return true, если удаление прошло успешно, иначе false
     */
    public boolean delete(String id) {
        int index = indexOf(id);
        if (index != -1) {
            System.arraycopy(items, index + 1, items, index, position - 1 - index);
            items[position - 1] = null;
            position--;
        }
        return index != -1;
    }
}