package aug.bueno.database;

import org.h2.tools.Server;

import java.sql.SQLException;

public class MainDatabase {
    public static void main(String[] args) throws SQLException {
        Server.main();
        System.out.println("DB H2 Launched");
    }
}
