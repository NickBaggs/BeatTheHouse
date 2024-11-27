package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBAchievementsHandler {

    // Update all achievements for a given user profile
    public void updateAchievements(int profileId, int achievement1, int achievement2, int achievement3, int achievement4, int achievement5, int achievement6, int achievement7, int achievement8, int achievement9, int achievement10) {
        String query = "UPDATE achievements SET achievement1 = ?, achievement2 = ?, achievement3 = ?, achievement4 = ?, achievement5 = ?, achievement6 = ?, achievement7 = ?, achievement8 = ?, achievement9 = ?, achievement10 = ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, achievement1);
            stmt.setInt(2, achievement2);
            stmt.setInt(3, achievement3);
            stmt.setInt(4, achievement4);
            stmt.setInt(5, achievement5);
            stmt.setInt(6, achievement6);
            stmt.setInt(7, achievement7);
            stmt.setInt(8, achievement8);
            stmt.setInt(9, achievement9);
            stmt.setInt(10, achievement10);
            stmt.setInt(11, profileId);

            stmt.executeUpdate();
            System.out.println("Achievements updated for user profile ID: " + profileId);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update achievements for user profile ID: " + profileId);
        }
    }

    //Update achievement 1
    public void updateAchievement1(int profileId, int achievement1) {
        String query = "UPDATE achievements SET achievement1 = ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, achievement1);
            stmt.setInt(2, profileId);

            stmt.executeUpdate();
            System.out.println("Achievement 1 updated for user profile ID: " + profileId);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update achievement 1 for user profile ID: " + profileId);
        }
    }

    
    
    // Update achievement 2
    public void updateAchievement2(int profileId, int achievement2) {
        String query = "UPDATE achievements SET achievement2 = ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, achievement2);
            stmt.setInt(2, profileId);

            stmt.executeUpdate();
            System.out.println("Achievement 2 updated for user profile ID: " + profileId);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update achievement 2 for user profile ID: " + profileId);
        }
    }

    
    
    // Update achievement 3
    public void updateAchievement3(int profileId, int achievement3) {
        String query = "UPDATE achievements SET achievement3 = ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, achievement3);
            stmt.setInt(2, profileId);

            stmt.executeUpdate();
            System.out.println("Achievement 3 updated for user profile ID: " + profileId);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update achievement 3 for user profile ID: " + profileId);
        }
    }

    
    
    // Update achievement 4
    public void updateAchievement4(int profileId, int achievement4) {
        String query = "UPDATE achievements SET achievement4 = ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, achievement4);
            stmt.setInt(2, profileId);

            stmt.executeUpdate();
            System.out.println("Achievement 4 updated for user profile ID: " + profileId);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update achievement 4 for user profile ID: " + profileId);
        }
    }

    
    
    // Update achievement 5
    public void updateAchievement5(int profileId, int achievement5) {
        String query = "UPDATE achievements SET achievement5 = ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, achievement5);
            stmt.setInt(2, profileId);

            stmt.executeUpdate();
            System.out.println("Achievement 5 updated for user profile ID: " + profileId);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update achievement 5 for user profile ID: " + profileId);
        }
    }

    
    
    // Update achievement 6
    public void updateAchievement6(int profileId, int achievement6) {
        String query = "UPDATE achievements SET achievement6 = ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, achievement6);
            stmt.setInt(2, profileId);

            stmt.executeUpdate();
            System.out.println("Achievement 6 updated for user profile ID: " + profileId);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update achievement 6 for user profile ID: " + profileId);
        }
    }

    
    
    // Update achievement 7
    public void updateAchievement7(int profileId, int achievement7) {
        String query = "UPDATE achievements SET achievement7 = ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, achievement7);
            stmt.setInt(2, profileId);

            stmt.executeUpdate();
            System.out.println("Achievement 7 updated for user profile ID: " + profileId);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update achievement 7 for user profile ID: " + profileId);
        }
    }

    
    
    // Update achievement 8
    public void updateAchievement8(int profileId, int achievement8) {
        String query = "UPDATE achievements SET achievement8 = ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, achievement8);
            stmt.setInt(2, profileId);

            stmt.executeUpdate();
            System.out.println("Achievement 8 updated for user profile ID: " + profileId);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update achievement 8 for user profile ID: " + profileId);
        }
    }

    
    
    // Update achievement 9
    public void updateAchievement9(int profileId, int achievement9) {
        String query = "UPDATE achievements SET achievement9 = ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, achievement9);
            stmt.setInt(2, profileId);

            stmt.executeUpdate();
            System.out.println("Achievement 9 updated for user profile ID: " + profileId);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update achievement 9 for user profile ID: " + profileId);
        }
    }

    
    
    // Update achievement 10
    public void updateAchievement10(int profileId, int achievement10) {
        String query = "UPDATE achievements SET achievement10 = ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, achievement10);
            stmt.setInt(2, profileId);

            stmt.executeUpdate();
            System.out.println("Achievement 10 updated for user profile ID: " + profileId);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update achievement 10 for user profile ID: " + profileId);
        }
    }
}
