package ru.job4j.tracker;

import java.util.Comparator;

/**
 * Class ItemCompareIncrease
 * Класс сортирует заявки по возрастанию.
 * @author Dmitry Razumov
 * @version 1
 */
public class ItemCompareIncrease implements Comparator<Item> {
    /**
     * Метод сравнивает заявки
     * @param first Первая заявка
     * @param second Вторая заявка
     * @return Число, характеризует результат сравнения
     */
    @Override
    public int compare(Item first, Item second) {
        return first.getName().compareTo(second.getName());
    }
}
