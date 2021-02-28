package ru.job4j.tracker;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Class HbmTracker
 * Класс осуществляет взаимодействие с базой данных с помощью Hibernate.
 * @author Dmitry Razumov
 * @version 1
 */
public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    /**
     * Метод добавляет заявку в базу данных.
     * @param item Заявка.
     * @return Добавленная заявка.
     */
    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        session.save(item);
        tx.commit();
        session.close();
        return item;
    }

    /**
     * Метод заменяет заявку содержащуюся в БД.
     * @param id Идентификатор заявки, которую нужно заменить.
     * @param item Новая заявка на которую нужно заменить старую.
     * @return true, если замена прошла успешно, иначе false.
     */
    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = true;
        try (Session session = sf.openSession()) {
            Transaction tx = session.beginTransaction();
            item.setId(id);
            session.update(item);
            tx.commit();
        } catch (HibernateException e) {
            rsl = false;
        }
        return rsl;
    }

    /**
     * Метод осуществляет удаление заявки из БД с заданным идентификатором.
     * @param id Идентификатор заявки, которую нужно удалить.
     * @return true, если удаление прошло успешно, иначе false.
     */
    @Override
    public boolean delete(int id) {
        boolean rsl = true;
        try (Session session = sf.openSession()) {
            Transaction tx = session.beginTransaction();
            Item item = session.get(Item.class, id);
            session.delete(item);
            tx.commit();
        } catch (HibernateException e) {
            rsl = false;
        }
        return rsl;
    }

    /**
     * Метод возвращает список заявок из БД.
     * @return Список заявок.
     */
    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Item");
        List items = query.list();
        tx.commit();
        session.close();
        return items;
    }

    /**
     * Метод возвращает список содержащий заявки с заданным именем.
     * @param key Имя.
     * @return Список заявок.
     */
    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Item I where I.name = :key");
        query.setParameter("key", key);
        List items = query.list();
        tx.commit();
        session.close();
        return items;
    }

    /**
     * Метод возвращает заявку по ее идентификатору.
     * @param id Идентификатор заявки.
     * @return Заявка.
     */
    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        Item item = session.get(Item.class, id);
        tx.commit();
        session.close();
        return item;
    }

    /**
     * Метод закрывает соединение с базой данных.
     * @throws Exception Исключение.
     */
    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
