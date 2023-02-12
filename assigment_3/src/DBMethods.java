import java.sql.*;
public class DBMethods {
    Connection conn = connect_to_db("postgres", "postgres", "1234");
    //Enter table name
    String table_name = "users";
    Statement statement = null;

    {
        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertRow(String username, String password) {
        try {
            String query = String.format("insert into %s(username,password) values('%s', '%s');", table_name, username, password);
            statement.executeUpdate(query);
            System.out.println("New user added.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public Connection connect_to_db(String dbname, String username, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, username, password);
            if (conn == null) {
                System.out.println("Connection Failed!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return conn;
    }
}