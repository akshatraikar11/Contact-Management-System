package ContactManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
    
    // Database connection details
	String url = "jdbc:mysql://localhost/contacts_management_system"; 
    String username = "root";  
    String password = "";  
    private Statement statement;
    private Connection connection; // Declare connection variable

    // Constructor
    public Database() throws SQLException {
        // Use the correct variable names
        connection = DriverManager.getConnection(url, username, password); 
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    // Fetch contacts
    public ArrayList<Contact> getContacts() throws SQLException {
        ArrayList<Contact> contacts = new ArrayList<>();
        String select = "SELECT * FROM `contacts`;";
        ResultSet rs = statement.executeQuery(select);
        while (rs.next()) {
            Contact c = new Contact();
            c.setID(rs.getInt("ID"));
            c.setFirstName(rs.getString("First Name"));
            c.setLastName(rs.getString("Last Name"));
            c.setPhoneNumber(rs.getString("Phone Number"));
            c.setEmail(rs.getString("Email"));
            contacts.add(c);
        }
        return contacts;
    }

    // Insert a contact
    public void insertContact(Contact c) throws SQLException {
        String insert = "INSERT INTO `contacts`(`ID`, `First Name`, `Last Name`, "
                + "`Phone Number`, `Email`) VALUES ('"+c.getID()+"','"+c.getFirstName()+
                "','"+c.getLastName()+"','"+c.getPhoneNumber()+"','"+c.getEmail()+"')";
        statement.execute(insert);
    }

    // Update a contact
    public void updateContact(Contact c) throws SQLException {
        String update = "UPDATE `contacts` SET `First Name`='"+c.getFirstName()+
                "',`Last Name`='"+c.getLastName()+"',`Phone Number`='"+c.getPhoneNumber()
                +"',`Email`='"+c.getEmail()+"' WHERE `ID` = "+c.getID()+" ;";
        statement.execute(update);
    }

    // Delete a contact
    public void deleteContact(Contact c) throws SQLException {
        String delete = "DELETE FROM `contacts` WHERE `ID` = "+c.getID()+" ;";
        statement.execute(delete);
    }

    // Get next ID
    public int getNextID() throws SQLException {
        int id = 0;
        ArrayList<Contact> contacts = getContacts();
        if (contacts.size() != 0) {
            Contact last = contacts.get(contacts.size() - 1);
            id = last.getID() + 1;
        }
        return id;
    }

    // Close the connection and statement
    public void close() throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
