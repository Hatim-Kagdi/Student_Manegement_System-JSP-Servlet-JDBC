package in.keen.Utils;

import in.keen.Connection.DBconnection;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

public class PasswordMigrator{
    public static void main(String[] args) {
        String selectSQL = "SELECT user_id, user_password FROM users";
        String updateSQL = "UPDATE users SET user_password = ? WHERE user_id = ?";

        try (Connection con = DBconnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL);
             PreparedStatement pstmt = con.prepareStatement(updateSQL)) {

            while (rs.next()) {
                int id = rs.getInt("user_id");
                String oldPass = rs.getString("user_password");

                // Check if it's already hashed (BCrypt hashes start with $2a$)
                if (!oldPass.startsWith("$2a$")) {
                    String hashedPass = BCrypt.hashpw(oldPass, BCrypt.gensalt());
                    
                    pstmt.setString(1, hashedPass);
                    pstmt.setInt(2, id);
                    pstmt.executeUpdate();
                    System.out.println("Migrated User ID: " + id);
                }
            }
            System.out.println("Migration Complete!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}