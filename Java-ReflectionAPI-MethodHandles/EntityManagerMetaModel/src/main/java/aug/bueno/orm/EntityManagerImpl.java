package aug.bueno.orm;

import aug.bueno.util.ColumnField;
import aug.bueno.util.Metamodel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class EntityManagerImpl<T> implements EntityManager<T> {

    private AtomicLong idGenerator = new AtomicLong(0L);


    @Override
    public void persist(T t) throws SQLException, IllegalAccessException {

        Metamodel metamodel = Metamodel.of(t.getClass());

        String sql = metamodel.buildInsertRequest();
        PreparedStatement statement = prepareStatementWith(sql).andParameters(t);

        statement.executeUpdate();

    }

    @Override
    public T find(Class<T> clss, Object primaryKey) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Metamodel metamodel = Metamodel.of(clss.getClass());

        String sql = metamodel.buildSelectRequest();

        PreparedStatement statement = prepareStatementWith(sql).andPrimaryKey(primaryKey);

        ResultSet resultSet = statement.executeQuery();

        return this.buildInstanceFrom(clss, resultSet);
    }

    private T buildInstanceFrom(Class<T> clss, ResultSet resultSet) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException {

        Metamodel metamodel = Metamodel.of(clss);

        T t = clss.getConstructor().newInstance();

        Field primaryKeyField = metamodel.getPrimaryKey().getField();
        String primaryKeyColumnName = primaryKeyField.getName();
        Class<?> primaryKeyType = primaryKeyField.getType();

        resultSet.next(); // Advancing to the first line of the result;

        if (primaryKeyType == Long.class) {
            long primaryKey = resultSet.getInt(primaryKeyColumnName);
            primaryKeyField.set(t, primaryKey);
        }

        for (ColumnField columnField : metamodel.getColumns()) {

            Field field = columnField.getField();
            field.setAccessible(true);

            Class<?> columnType = columnField.getType();
            String columnName = field.getName();

            if (columnType == int.class) {
                int value = resultSet.getInt(columnName);
                field.set(t, value);
            } else if (columnType == String.class) {
                String value = resultSet.getString(columnName);
                field.set(t, value);
            }

        }
        return t;
    }

    private PreparedStatementWrapper prepareStatementWith(String sql) throws SQLException {

        Connection connection = buildConnection();

        PreparedStatement statement = connection.prepareStatement(sql);

        return new PreparedStatementWrapper(statement);
    }

    public Connection buildConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:C:\\Users\\AsusAugusto\\Documents\\Java-Studies\\Java-ReflectionAPI-MethodHandles\\EntityManagerMetaModel\\database-files\\db-meta", "admin", "");
    }

    private class PreparedStatementWrapper {

        private PreparedStatement statement;

        public PreparedStatementWrapper(PreparedStatement statement) {

            this.statement = statement;
        }

        public PreparedStatement andParameters(T t) throws SQLException, IllegalAccessException {

            Metamodel metamodel = Metamodel.of(t.getClass());


            // Getting PK
            Class<?> primaryKeyType = metamodel.getPrimaryKey().getType();

            if (primaryKeyType == long.class) {
                long id = idGenerator.incrementAndGet();

                statement.setLong(1, id);

                Field field = metamodel.getPrimaryKey().getField();
                field.setAccessible(true);
                field.set(t, id);
            }

            // Getting Columns Entity
            List<ColumnField> columns = metamodel.getColumns();

            for (int columnIndex = 0; columnIndex < columns.size(); columnIndex++) {

                ColumnField columnField = columns.get(columnIndex);

                Class<?> fieldType = columnField.getType();

                Field field = columnField.getField();
                field.setAccessible(true);

                Object value = field.get(t);

                if (fieldType == int.class) {
                    statement.setInt(columnIndex + 2, (int) value);
                } else if (fieldType == String.class) {
                    statement.setString(columnIndex + 2, (String) value);
                }
            }
            return statement;
        }

        public PreparedStatement andPrimaryKey(Object primaryKey) throws SQLException {

            if (primaryKey.getClass() == Long.class) {
                statement.setLong(1, (Long) primaryKey);
            }

            return statement;
        }
    }
}
