package aug.bueno.database;

import org.h2.tools.Server;

import java.sql.SQLException;

public class MainDatabase {

    public static void main(String[] args) throws SQLException {

        //TODO https://www.javaguides.net/2019/08/java-h2-database-tutorial.html
        //TODO https://www.tutorialspoint.com/h2_database/h2_database_jdbc_connection.html

        Server.main();
        System.out.println("DB H2 Launched");
    }
}
