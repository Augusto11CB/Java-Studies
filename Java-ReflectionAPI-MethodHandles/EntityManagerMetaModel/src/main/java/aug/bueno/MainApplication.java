package aug.bueno;

import aug.bueno.model.Person;
import aug.bueno.util.ColumnField;
import aug.bueno.util.Metamodel;
import aug.bueno.util.PrimaryKeyField;

import java.lang.reflect.Field;
import java.util.List;

public class MainApplication {

    private static Field[] X;

    public static void main(String[] args) {
        Metamodel metamodel = Metamodel.of(Person.class);

        X = Person.class.getDeclaredFields();

        PrimaryKeyField primaryKeyField = metamodel.getPrimaryKey();
        List<ColumnField> columnFields = metamodel.getColumns();

        System.out.println("PK Name=" + primaryKeyField.getName() +
                " type = " + primaryKeyField.getType()
        );

        for (ColumnField columnField : columnFields) {

            System.out.println("PK Name=" + columnField.getName() +
                    " type = " + columnField.getType()
            );
        }
    }
}
