package aug.bueno.orm;

import java.sql.SQLException;

public interface EntityManager<T> {

    static <T> EntityManager<T> of (Class<T> clss){
        return new EntityManagerImpl<>();
    }

    void persist(T t) throws SQLException, IllegalAccessException;

}
