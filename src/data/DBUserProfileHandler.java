package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUserProfileHandler {

    // update/ initialize user profile
    public void updateUserProfile(int profileId, String profileName, int currentCardAppearance, int currentTableAppearance, int currentProfileIcon) {
        
        String updateQuery = "UPDATE user_profile SET profile_name = ?, current_card_apearance = ?, current_table_apearance = ?, current_profile_icon = ? WHERE user_profile_id = ?";

      
        String insertQuery = "INSERT INTO user_profile (user_profile_id, profile_name, current_card_apearance, current_table_apearance, current_profile_icon) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmtUpdate = conn.prepareStatement(updateQuery);
             PreparedStatement stmtInsert = conn.prepareStatement(insertQuery)) {

           
            stmtUpdate.setString(1, profileName);
            stmtUpdate.setInt(2, currentCardAppearance);
            stmtUpdate.setInt(3, currentTableAppearance);
            stmtUpdate.setInt(4, currentProfileIcon);
            stmtUpdate.setInt(5, profileId);

            int rowsUpdated = stmtUpdate.executeUpdate();

            
            if (rowsUpdated == 0) {
                stmtInsert.setInt(1, profileId);
                stmtInsert.setString(2, profileName);
                stmtInsert.setInt(3, currentCardAppearance);
                stmtInsert.setInt(4, currentTableAppearance);
                stmtInsert.setInt(5, currentProfileIcon);

                stmtInsert.executeUpdate();
                System.out.println("New user profile created successfully!");
            } else {
                System.out.println("User profile updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update or insert user profile.");
        }
    }

    
    
    
    // Update profile name
    public void updateProfileName(int profileId, String profileName) {
        String query = "UPDATE user_profile SET profile_name = ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, profileName);
            stmt.setInt(2, profileId);

            stmt.executeUpdate();
            System.out.println("Profile name updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update profile name.");
        }
    }

    
    
    
    // Update current card appearance
    public void updateCardAppearance(int profileId, int cardAppearance) {
        String query = "UPDATE user_profile SET current_card_apearance = ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, cardAppearance);
            stmt.setInt(2, profileId);

            stmt.executeUpdate();
            System.out.println("Card appearance updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update card appearance.");
        }
    }

    
    
    
    // Update current table appearance
    public void updateTableAppearance(int profileId, int tableAppearance) {
        String query = "UPDATE user_profile SET current_table_apearance = ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, tableAppearance);
            stmt.setInt(2, profileId);

            stmt.executeUpdate();
            System.out.println("Table appearance updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update table appearance.");
        }
    }

    
    
    
    // Update profile icon
    public void updateProfileIcon(int profileId, int profileIcon) {
        String query = "UPDATE user_profile SET current_profile_icon = ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, profileIcon);
            stmt.setInt(2, profileId);

            stmt.executeUpdate();
            System.out.println("Profile icon updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update profile icon.");
        }
    }

    
    
    
    // Get profile name for a given profile ID
    public String getProfileName(int profileId) {
        String query = "SELECT profile_name FROM user_profile WHERE user_profile_id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, profileId); 
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("profile_name");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching profile name for ID: " + profileId);
            e.printStackTrace();
        }
        return null;
    }

    
    
    
    // Get current card appearance for a given profile ID
    public int getCardAppearance(int profileId) {
        String query = "SELECT current_card_apearance FROM user_profile WHERE user_profile_id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, profileId); 
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("current_card_apearance");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching card appearance for ID: " + profileId);
            e.printStackTrace();
        }
        return -1; // -1 means no data found
    }

    
    
    
    // Get current table appearance for a given profile ID
    public int getTableAppearance(int profileId) {
        String query = "SELECT current_table_apearance FROM user_profile WHERE user_profile_id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, profileId); 
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("current_table_apearance");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching table appearance for ID: " + profileId);
            e.printStackTrace();
        }
        return -1; 
    }

    
    
    
    // Get current profile icon for a given profile ID
    public int getProfileIcon(int profileId) {
        String query = "SELECT current_profile_icon FROM user_profile WHERE user_profile_id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, profileId); 
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("current_profile_icon");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching profile icon for ID: " + profileId);
            e.printStackTrace();
        }
        return -1; 
    }
    
    // Delete user profile
    public void deleteProfile(int profileId) {
        String query = "UPDATE user_profile SET profile_name = NULL WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, profileId);  

            int rowsUpdated = stmt.executeUpdate();  

            if (rowsUpdated > 0) {
                System.out.println("Profile name set to null for user profile ID: " + profileId);
            } else {
                System.out.println("No profile found for user profile ID: " + profileId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating profile for user profile ID: " + profileId);
        }
    }
}
