package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseInitialization {

    // Initialize profiles with default values 
	public void initializeProfiles() {
	    for (int i = 1; i <= 5; i++) {
	        System.out.println("Checking profile " + i);
	        if (!isProfileCreated(i)) {
	            System.out.println("Initializing profile " + i);
	            initializeProfile(i);
	            System.out.println("Profile " + i + " initialized.");
	        } else {
	            System.out.println("Profile " + i + " already exists.");
	        }
	    }
	}


	
	
    // Check if a profile has already been created
    private boolean isProfileCreated(int profileId) {
        String query = "SELECT profile_name FROM user_profile WHERE user_profile_id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, profileId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String profileName = rs.getString("profile_name");
                return profileName != null && !profileName.trim().isEmpty();
            }
        } catch (SQLException e) {
            System.out.println("Error checking if profile exists for ID: " + profileId);
            e.printStackTrace();
        }
        return false;
    }


    
    
    // Initialize a profile with default values
    private void initializeProfile(int profileId) {
        String query = "INSERT IGNORE INTO user_profile (user_profile_id, profile_name, current_card_apearance, current_table_apearance, current_profile_icon) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, profileId); 
            stmt.setString(2, null); 
            stmt.setInt(3, 1); 
            stmt.setInt(4, 1); 
            stmt.setInt(5, 0); 

            stmt.executeUpdate();
            System.out.println("Profile " + profileId + " initialized with default values!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to initialize profile " + profileId);
        }

        initializeAchievements(profileId);
        initializeStats(profileId);
    }

    
    
    
    
    // Initialize achievements 
    private void initializeAchievements(int profileId) {
        String query = "INSERT IGNORE INTO achievements (achievement_id, achievement1, achievement2, achievement3, achievement4, achievement5, achievement6, achievement7, achievement8, achievement9, achievement10, user_profile_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, profileId); 
            stmt.setInt(2, 0); 
            stmt.setInt(3, 0); 
            stmt.setInt(4, 0); 
            stmt.setInt(5, 0); 
            stmt.setInt(6, 0); 
            stmt.setInt(7, 0); 
            stmt.setInt(8, 0); 
            stmt.setInt(9, 0); 
            stmt.setInt(10, 0); 
            stmt.setInt(11, 0); 
            stmt.setInt(12, profileId); 

            stmt.executeUpdate();
            System.out.println("Achievements initialized for profile " + profileId + "!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to initialize achievements for profile " + profileId);
        }
    }

    // Initialize stats
    private void initializeStats(int profileId) {
        String query = "INSERT IGNORE INTO stats (chip_count, wins, losses, chips_won, chips_lost, total_winnings, times_bankrupt, highest_chip_count, user_profile_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, 0); 
            stmt.setInt(2, 0); 
            stmt.setInt(3, 0); 
            stmt.setInt(4, 0); 
            stmt.setInt(5, 0); 
            stmt.setInt(6, 0); 
            stmt.setInt(7, 0); 
            stmt.setInt(8, 0); 
            stmt.setInt(9, profileId); 

            stmt.executeUpdate();
            System.out.println("Stats initialized for profile " + profileId + "!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to initialize stats for profile " + profileId);
        }
    }

    public static void main(String[] args) {
        DatabaseInitialization dbInit = new DatabaseInitialization();
        dbInit.initializeProfiles(); 
    }
}
