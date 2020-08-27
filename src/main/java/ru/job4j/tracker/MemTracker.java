package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class MemTracker
 * @author Dmitry Razumov
 * @version $Id$
 * @since 0.1
 */
public class MemTracker implements Store {
    /**
     * Список для хранения заявок.
     */
    private final List<Item> items = new ArrayList<>();

    @Override
    public void init() {

    }

    @Override
    public void close() throws Exception {

    }

    /**
     * Метод добавления заявки в хранилище.
     * @param item Новая заявка
     */
    @Override
    public Item add(Item item) {
        item.setId(generateId());
        items.add(item);
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
     * Метод возвращает список заявок.
     * @return Список заявок
     */
    @Override
    public List<Item> findAll() {
        return items;
    }

    /**
     * Метод возвращает список содержащий заявки с заданным именем.
     * @param name Имя
     * @return Список заявок
     */
    @Override
    public List<Item> findByName(String name) {
        List<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().equals(name)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Метод возвращает заявку по ее уникальному ключу.
     * @param id Ключ
     * @return Заявка
     */
    @Override
    public Item findById(String id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }

    /**
     * Метод находит индекс заявки по ее ключу.
     * @param id Ключ
     * @return Индекс
     */
    private int indexOf(String id) {
        int rsl = -1;
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index).getId().equals(id)) {
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
    @Override
    public boolean replace(String id, Item item) {
        int index  = indexOf(id);
        if (index != -1) {
            item.setId(id);
            items.set(index, item);
        }
        return index != -1;
    }

    /**
     * Метод осуществляет удаление заявки с заданным идентификатором.
     * @param id Идентификатор заявки, которую нужно удалить
     * @return true, если удаление прошло успешно, иначе false
     */
    @Override
    public boolean delete(String id) {
        int index = indexOf(id);
        if (index != -1) {
           items.remove(index);
        }
        return index != -1;
    }
}