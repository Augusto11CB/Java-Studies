package aug.bueno.util;

import aug.bueno.annotation.Column;
import aug.bueno.annotation.PrimaryKey;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Metamodel {

    private Class<?> clss;

    public static Metamodel of(Class<?> clss) {
        return new Metamodel(clss);
    }

    private Metamodel(Class<?> clss) {
        this.clss = clss;
    }

    public PrimaryKeyField getPrimaryKey() {

        Field[] fields = clss.getDeclaredFields();

        for (Field field : fields) {
            PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);

            if (primaryKey != null) {
                PrimaryKeyField primaryKeyField = new PrimaryKeyField(field);
                return primaryKeyField;
            }
        }
        throw new IllegalArgumentException("No primary key found in class " + clss.getSimpleName());
    }

    public List<ColumnField> getColumns() {

        List<ColumnField> columnFields = new ArrayList<>();

        Field[] fields = clss.getDeclaredFields();

        for (Field field : fields) {
            Column column = field.getAnnotation(Column.class);

            if (column != null) {
                ColumnField columnField = new ColumnField(field);
                columnFields.add(columnField);
            }
        }

        return columnFields;
    }


    /**
     * Building the SQL Query to select an object in the Database given a PrimaryKey
     * <p>
     * Ex: select column1, column2 from ClassName where id = ?
     */
    public String buildSelectRequest() {
        String columnElement = buildColumnElements();

        return "select " + columnElement + " from " + this.clss.getSimpleName() +
                "where " + getPrimaryKey().getName() + " = ?";
    }

    /**
     * Building the SQL Query to insert an object in the Database
     * <p>
     * Ex: insert into ClassName (column1, column2) values (?, ?)
     */
    public String buildInsertRequest() {

        final String columnElements = this.buildColumnElements();
        final String questionMarkElements = this.buildQuestionMarkElements();

        return "insert into " + this.clss.getSimpleName() + " (" + columnElements + ") values(" + questionMarkElements + ")";
    }

    private String buildColumnElements() {

        String name = getPrimaryKey().getName();
        List<String> columnNames = getColumns().stream().map(ColumnField::getName).collect(Collectors.toList());

        columnNames.add(0, name);

        return String.join(", ", columnNames);
    }

    private String buildQuestionMarkElements() {

        int numberOfColumns = getColumns().size() + 1;

        return IntStream.range(0, numberOfColumns).mapToObj(index -> "?").collect(Collectors.joining(","));
    }

}
