package ru.job4j.di;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class Context
 * Класс создает контекст.
 * @author Dmitry Razumov
 * @version 1
 */
public class Context {
    /**
     * Карта хранит созданные объекты.
     */
    private Map<String, Object> els = new HashMap<String, Object>();

    /**
     * Метод регистрирует классы в контексте.
     * @param cl
     */
    public void reg(Class cl) {
        Constructor[] constructors = cl.getDeclaredConstructors();
        if (constructors.length > 1) {
            throw new IllegalStateException("Class has multiple constructors : " + cl.getCanonicalName());
        }
        Constructor con = constructors[0];
        List<Object> args = new ArrayList<Object>();
        for (Class arg : con.getParameterTypes()) {
            if (!els.containsKey(arg.getCanonicalName())) {
                throw new IllegalStateException("Object doesn't found in context : " + arg.getCanonicalName());
            }
            args.add(els.get(arg.getCanonicalName()));
        }
        try {
            els.put(cl.getCanonicalName(), con.newInstance(args.toArray()));
        } catch (Exception e) {
            throw new IllegalStateException("Coun't create an instance of : " + cl.getCanonicalName(), e);
        }
    }

    /**
     * Метод возвращает класс из контекста.
     * @param inst Объект, который нужно вернуть.
     * @param <T> Тип объекта.
     * @return Объект.
     */
    public <T> T get(Class<T> inst) {
        return (T) els.get(inst.getCanonicalName());
    }
}
