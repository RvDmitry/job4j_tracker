package ru.job4j.tracker;

import java.util.Scanner;

/**
 * Class StartUI
 * Класс реализует меню заявок.
 * @author Dmitry Razumov
 * @version 1
 */
public class StartUI {
    /**
     * Метод обрабатывает действия пользователя при работе с меню.
     * @param scanner Объект Scanner для считывания выбора пользователя пунктов меню
     * @param tracker Объект Tracker обрабатывает действия пользователя при работе с заявками
     */
    public void init(Scanner scanner, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            System.out.print("Select: ");
            int select = Integer.valueOf(scanner.nextLine());
            switch (select) {
                case 0 :
                    System.out.println("=== Create a new Item ====");
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    Item item = new Item(name);
                    tracker.add(item);
                    System.out.println("Done.");
                    break;
                case 1 :
                    System.out.println("=== All Items ====");
                    Item[] items = tracker.findAll();
                    for (Item current :items) {
                        System.out.println("Id: " + current.getId() + "   name: " + current.getName());
                    }
                    System.out.println("Done.");
                    break;
                case 2 :
                    System.out.println("=== Edit item ===");
                    System.out.print("Enter Item Id you will edit: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter new name: ");
                    name = scanner.nextLine();
                    item = new Item(name);
                    if (tracker.replace(id, item)) {
                        System.out.println("Item successfully edited");
                    } else {
                        System.out.println("Item with this Id was not found.");
                    }
                    break;
                case 3 :
                    System.out.println("=== Delete item ====");
                    System.out.print("Enter Item Id: ");
                    id = scanner.nextLine();
                    if (tracker.delete(id)) {
                        System.out.println("Item successfully deleted");
                    } else {
                        System.out.println("Item with this Id was not found.");
                    }
                    break;
                case 4 :
                    System.out.println("=== Find item by Id ====");
                    System.out.print("Enter Item Id: ");
                    id = scanner.nextLine();
                    item = tracker.findById(id);
                    if (item == null) {
                        System.out.println("No item with this Id.");
                    } else {
                        System.out.println("Id: " + item.getId() + "    name: " + item.getName());
                        System.out.println("Done.");
                    }
                    break;
                case 5 :
                    System.out.println("=== Find items by name ===");
                    System.out.print("Enter Item name: ");
                    name = scanner.nextLine();
                    items = tracker.findByName(name);
                    if (items.length == 0) {
                        System.out.println("No item with this name.");
                    } else {
                        for (Item current : items) {
                            System.out.println("Id: " + current.getId() + "   name: " + current.getName());
                        }
                        System.out.println("Done.");
                    }
                    break;
                case 6 :
                    run = false;
                    break;
            }
        }
    }

    /**
     * Метод отображает меню приложения на экране.
     */
    private void showMenu() {
        System.out.println("=== Menu ===");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }

    /**
     * Главный метод программы. Запускает выполнение приложения.
     * @param args Параметры командной строки
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tracker tracker = new Tracker();
        new StartUI().init(scanner, tracker);
    }
}
