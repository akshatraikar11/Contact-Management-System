package ContactManagementSystem;

import java.sql.SQLException;
import java.util.ArrayList;

public class TestDatabaseConnection {
    public static void main(String[] args) {
        try {
            Database db = new Database(); // Attempt to create a Database instance
            System.out.println("Connection successful!");

            // Optionally, test fetching contacts
            ArrayList<Contact> contacts = db.getContacts();
            for (Contact c : contacts) {
                System.out.println(c.getFirstName() + " " + c.getLastName());
            }
            
            db.close(); // Close the database connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
