package ru.job4j.di;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Store
 * Класс реализует хранилище строк.
 * @author Dmitry Razumov
 * @version 1
 */
@Scope("prototype")
@Component
public class Store {
    /**
     * Коллекция хранит список строк.
     */
    private List<String> data = new ArrayList<String>();

    /**
     * Метод добавляет строку в хранилище.
     * @param value Строка.
     */
    public void add(String value) {
        data.add(value);
    }

    /**
     * Метод возвращает список строк из хранилища.
     * @return Список строк.
     */
    public List<String> getAll() {
        return data;
    }
}
