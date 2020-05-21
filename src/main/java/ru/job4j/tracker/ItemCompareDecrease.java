package ru.job4j.tracker;

import java.util.Comparator;

/**
 * Class ItemCompareDecrease
 * Класс сортирует заяки по убыванию.
 * @author Dmitry Razumov
 * @version 1
 */
public class ItemCompareDecrease implements Comparator<Item> {
    /**
     * Метод сравнивает заявки
     * @param first Первая заявка
     * @param second Вторая заявка
     * @return Число, характеризует результат сравнения
     */
    @Override
    public int compare(Item first, Item second) {
        return second.getName().compareTo(first.getName());
    }
}
