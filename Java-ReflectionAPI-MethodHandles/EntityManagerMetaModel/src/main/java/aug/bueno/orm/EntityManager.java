package aug.bueno.orm;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface EntityManager<T> {

    static <T> EntityManager<T> of(Class<T> clss) {
        return new EntityManagerImpl<>();
    }

    void persist(T t) throws SQLException, IllegalAccessException;

    T find(Class<T> clss, Object primaryKey) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

}
