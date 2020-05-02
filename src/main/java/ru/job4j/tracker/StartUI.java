package ru.job4j.tracker;

/**
 * Class StartUI
 * Класс реализует меню заявок.
 * @author Dmitry Razumov
 * @version 1
 */
public class StartUI {
    /**
     * Метод создает новую заявку.
     * @param input Объект для считывания ответов пользователя
     * @param tracker Объект Tracker обрабатывает действия пользователя при работе с заявками
     */
    public static void createItem(Input input, Tracker tracker) {
        System.out.println("=== Create a new Item ====");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        tracker.add(item);
        System.out.println("Done.");
    }

    /**
     * Метод выводит все заявки на экран.
     * @param tracker Объект Tracker обрабатывает действия пользователя при работе с заявками
     */
    public static void findAllItems(Tracker tracker) {
        System.out.println("=== All Items ====");
        Item[] items = tracker.findAll();
        for (Item current :items) {
            System.out.println("Id: " + current.getId() + "   name: " + current.getName());
        }
        System.out.println("Done.");
    }

    /**
     * Метод выполняет редактирование заявки.
     * @param input Объект для считывания ответов пользователя
     * @param tracker Объект Tracker обрабатывает действия пользователя при работе с заявками
     */
    public static void replaceItem(Input input, Tracker tracker) {
        System.out.println("=== Edit item ===");
        String id =  input.askStr("Enter Item Id you will edit: ");
        String name =  input.askStr("Enter new name: ");
        Item item = new Item(name);
        if (tracker.replace(id, item)) {
            System.out.println("Item successfully edited");
        } else {
            System.out.println("Item with this Id was not found.");
        }
    }

    /**
     * Метод выполняет удаление заявки.
     * @param input Объект для считывания ответов пользователя
     * @param tracker Объект Tracker обрабатывает действия пользователя при работе с заявками
     */
    public static void deteleItem(Input input, Tracker tracker) {
        System.out.println("=== Delete item ====");
        String id =  input.askStr("Enter Item Id: ");
        if (tracker.delete(id)) {
            System.out.println("Item successfully deleted");
        } else {
            System.out.println("Item with this Id was not found.");
        }
    }

    /**
     * Метод осуществляет поиск заявки по ее идентификационному номеру и выводит ее на экран.
     * @param input Объект для считывания ответов пользователя
     * @param tracker Объект Tracker обрабатывает действия пользователя при работе с заявками
     */
    public static void findItemById(Input input, Tracker tracker) {
        System.out.println("=== Find item by Id ====");
        String id =  input.askStr("Enter Item Id: ");
        Item item = tracker.findById(id);
        if (item == null) {
            System.out.println("No item with this Id.");
        } else {
            System.out.println("Id: " + item.getId() + "    name: " + item.getName());
            System.out.println("Done.");
        }
    }

    /**
     * Метод осуществляет поиск заявки по ее имени и выводит ее на экран.
     * Если найдено несколько заявок с одинаковым именем, то выводит их все.
     * @param input Объект для считывания ответов пользователя
     * @param tracker Объект Tracker обрабатывает действия пользователя при работе с заявками
     */
    public static void findItemsByName(Input input, Tracker tracker) {
        System.out.println("=== Find items by name ===");
        String name =  input.askStr("Enter Item name: ");
        Item[] items = tracker.findByName(name);
        if (items.length == 0) {
            System.out.println("No item with this name.");
        } else {
            for (Item current : items) {
                System.out.println("Id: " + current.getId() + "   name: " + current.getName());
            }
            System.out.println("Done.");
        }
    }

    /**
     * Метод обрабатывает действия пользователя при работе с меню.
     * @param input Объект для считывания ответов пользователя
     * @param tracker Объект Tracker обрабатывает действия пользователя при работе с заявками
     */
    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            int select = input.askInt("Select: ");
            switch (select) {
                case 0 :
                    StartUI.createItem(input, tracker);
                    break;
                case 1 :
                    StartUI.findAllItems(tracker);
                    break;
                case 2 :
                    StartUI.replaceItem(input, tracker);
                    break;
                case 3 :
                    StartUI.deteleItem(input, tracker);
                    break;
                case 4 :
                    StartUI.findItemById(input, tracker);
                    break;
                case 5 :
                    StartUI.findItemsByName(input, tracker);
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
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker);
    }
}
