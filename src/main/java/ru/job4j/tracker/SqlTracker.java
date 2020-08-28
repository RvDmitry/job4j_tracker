package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Class SqlTracker
 * Класс реализует операции записи, чтения и т.д. с базой данных.
 * @author Dmitry Razumov
 * @version 1
 */
public class SqlTracker implements Store {
    /**
     * Поле хранить подключение к базе данных.
     */
    private Connection cn;

    /**
     * Метод устанавливает соединение с базой данных.
     */
    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Метод закрывает соединение с базой данных.
     * @throws Exception Исключение, если соединение закрыть не удалось
     */
    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    /**
     * Метод добавляет заявку в базу данных.
     * @param item Заявка
     * @return true, если заявка добавлена успешна, иначе false
     */
    @Override
    public Item add(Item item) {
        try (PreparedStatement st = cn.prepareStatement("insert into items(name) values(?)")) {
            st.setString(1, item.getName());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Метод заменяет заявку содержщуюся в БД
     * @param id Индентификатор заявки, которую нужно заменить
     * @param item Новая заявка на которую нужно заменить старую
     * @return true, если замена прошла успешно, иначе false
     */
    @Override
    public boolean replace(String id, Item item) {
        int count = 0;
        try (PreparedStatement st = cn.prepareStatement("update items set name = ? where id = ?")) {
            st.setString(1, item.getName());
            st.setInt(2, Integer.parseInt(id));
            count = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count != 0;
    }

    /**
     * Метод осуществляет удаление заявки из БД с заданным идентификатором.
     * @param id Идентификатор заявки, которую нужно удалить
     * @return true, если удаление прошло успешно, иначе false
     */
    @Override
    public boolean delete(String id) {
        int count = 0;
        try (PreparedStatement st = cn.prepareStatement("delete from items where id = ?")) {
            st.setInt(1, Integer.parseInt(id));
            count = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count != 0;
    }

    /**
     * Метод возвращает список заявок из БД.
     * @return Список заявок
     */
    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("select * from items")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Item item = new Item(name);
                item.setId(String.valueOf(id));
                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Метод возвращает список содержащий заявки с заданным именем.
     * @param key Имя
     * @return Список заявок
     */
    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement st = cn.prepareStatement("select * from items where name = ?")) {
            st.setString(1, key);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Item item = new Item(name);
                item.setId(String.valueOf(id));
                items.add(item);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Метод возвращает заявку по ее уникальному ключу.
     * @param id Ключ
     * @return Заявка
     */
    @Override
    public Item findById(String id) {
        Item item = null;
        try (PreparedStatement st = cn.prepareStatement("select * from items where id = ?")) {
            st.setInt(1, Integer.parseInt(id));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                item = new Item(name);
                item.setId(id);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
}
