package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Class HibernateRun
 * В классе демонстрируется работа с Hibernate.
 * @author Dmitry Razumov
 * @version 1
 */
public class HibernateRun {
    /**
     * Главный метод программы.
     * @param args Параметры командной строки.
     */
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Item item = create(new Item("Learn Hibernate"), sf);
            System.out.println(item);
            item.setName("Learn Hibernate 5.");
            update(item, sf);
            System.out.println(item);
            Item rsl = findById(item.getId(), sf);
            System.out.println(rsl);
            delete(rsl.getId(), sf);
            List<Item> list = findAll(sf);
            for (Item it : list) {
                System.out.println(it);
            }
            for (int i = 1; i <= 5; i++) {
                item = new Item("Item" + i);
                item.setDescription("Desc" + i);
                item.setCreated(Timestamp.valueOf(LocalDateTime.now()));
                create(item, sf);
            }
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    /**
     * Метод сохраняет заявку в базу данных.
     * @param item Заявка.
     * @param sf Объект конфигуратор.
     * @return Сохраненная заявка.
     */
    public static Item create(Item item, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    /**
     * Метод обновляет заявку в базе данных.
     * @param item Заявка.
     * @param sf Объект конфигуратор.
     */
    public static void update(Item item, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Метод удаляет заявку из базы данных по ее идентификатору.
     * @param id Идентификатор заявки.
     * @param sf Объект конфигуратор.
     */
    public static void delete(Integer id, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item item = new Item(null);
        item.setId(id);
        session.delete(item);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Метод ищет все заявки в базе данных.
     * @param sf Объект конфигуратор.
     * @return Список заявок.
     */
    public static List<Item> findAll(SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("from ru.job4j.tracker.Item").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    /**
     * Метод ищет заявку в базе данных по ее идентификатору.
     * @param id Идентификатор заявки.
     * @param sf Объект конфигуратор.
     * @return Заявка.
     */
    public static Item findById(Integer id, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item result = session.get(Item.class, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
