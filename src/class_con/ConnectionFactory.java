package class_con;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Света on 07.11.2016.
 */
public class ConnectionFactory {

    public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    public static final String LOGIN = "Entities";
    public static final String PASSWORD = "oracle";


        private static ConnectionFactory instance;

        private ConnectionFactory() {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Not found class " + DRIVER, e);
            }
            }


        public static synchronized ConnectionFactory getInstance() {
            if (instance == null) {
                instance = new ConnectionFactory();
            }
            return instance;
        }

        public Connection getConnection() throws SQLException {
            return DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
        }

   }