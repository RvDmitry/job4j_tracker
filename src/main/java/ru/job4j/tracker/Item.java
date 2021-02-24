package ru.job4j.tracker;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class Item
 * Класс описывает бизнес модель заявки.
 * @author Dmitry Razumov
 * @version 1
 */
@Entity
@Table(name = "items")
public class Item {
    /**
     * Уникальный ключ заявки.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Имя заявки.
     */
    private String name;

    public Item() {
    }

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
    public Integer getId() {
        return id;
    }

    /**
     * Метод задает уникальный ключ.
     * @param id Ключ
     */
    public void setId(Integer id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
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