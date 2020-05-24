package ru.job4j.tracker;

import java.util.Objects;

/**
 * Class Item
 * Класс описывает бизнес модель заявки.
 * @author Dmitry Razumov
 * @version 1
 */
public class Item {
    /**
     * Уникальный ключ заявки.
     */
    private String id;

    /**
     * Имя заявки.
     */
    private String name;

    /**
     * Конструктор создает заявку и инициализирует ее имя.
     * @param name Имя
     */
    public Item(String name) {
        this.name = name;
    }

    /**
     * Метод возвращает уникальный ключ.
     * @return Ключ
     */
    public String getId() {
        return id;
    }

    /**
     * Метод задает уникальный ключ.
     * @param id Ключ
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Метод возвращает имя заявки.
     * @return Имя
     */
    public String getName() {
        return name;
    }

    /**
     * Метод задает имя заявки.
     * @param name Имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Метод переопределяет вывод заявки в виде строки.
     * @return Строка
     */
    @Override
    public String toString() {
        return "Item{" + "id='" + id + '\'' + ", name='" + name + '\'' + '}';
    }
}