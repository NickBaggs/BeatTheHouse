package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBStatsHandler {

    // Method to update the entire stats table
    public void updateStats(int userProfileId, int chipCount, int wins, int losses, int chipsWon, int chipsLost, int totalWinnings, int timesBankrupt, int highestChipCount) {
        String query = "UPDATE stats SET chip_count = ?, wins = ?, losses = ?, chips_won = ?, chips_lost = ?, total_winnings = ?, times_bankrupt = ?, highest_chip_count = ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            
            stmt.setInt(1, chipCount);  
            stmt.setInt(2, wins);  
            stmt.setInt(3, losses);  
            stmt.setInt(4, chipsWon);  
            stmt.setInt(5, chipsLost); 
            stmt.setInt(6, totalWinnings);  
            stmt.setInt(7, timesBankrupt);  
            stmt.setInt(8, highestChipCount);  
            stmt.setInt(9, userProfileId);  
            
           
            stmt.executeUpdate();
            System.out.println("Stats updated for user profile ID: " + userProfileId);
        } catch (SQLException e) {
            System.out.println("Error updating stats for user profile ID: " + userProfileId);
            e.printStackTrace();
        }
    }

    
    
    
    // Method to update chip  count 
    public void updateChipCount(int userProfileId, int chipCount) {
        String query = "UPDATE stats SET chip_count = ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setInt(1, chipCount);
            stmt.setInt(2, userProfileId);
            stmt.executeUpdate();
            System.out.println("Chip count updated for user profile ID: " + userProfileId);
        } catch (SQLException e) {
            System.out.println("Error updating chip count for user profile ID: " + userProfileId);
            e.printStackTrace();
        }
    }

    
    
    
    // Method to update wins
    public void updateWins(int userProfileId, int wins) {
        String query = "UPDATE stats SET wins = ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setInt(1, wins);
            stmt.setInt(2, userProfileId);
            stmt.executeUpdate();
            System.out.println("Wins updated for user profile ID: " + userProfileId);
        } catch (SQLException e) {
            System.out.println("Error updating wins for user profile ID: " + userProfileId);
            e.printStackTrace();
        }
    }

    
    
    
    // Method to update losses
    public void updateLosses(int userProfileId, int losses) {
        String query = "UPDATE stats SET losses = ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setInt(1, losses);
            stmt.setInt(2, userProfileId);
            stmt.executeUpdate();
            System.out.println("Losses updated for user profile ID: " + userProfileId);
        } catch (SQLException e) {
            System.out.println("Error updating losses for user profile ID: " + userProfileId);
            e.printStackTrace();
        }
    }

    
    
    
    // Method to update chips won 
    public void updateChipsWon(int userProfileId, int chipsWon) {
        String query = "UPDATE stats SET chips_won = ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setInt(1, chipsWon);
            stmt.setInt(2, userProfileId);
            stmt.executeUpdate();
            System.out.println("Chips won updated for user profile ID: " + userProfileId);
        } catch (SQLException e) {
            System.out.println("Error updating chips won for user profile ID: " + userProfileId);
            e.printStackTrace();
        }
    }

    
    
    
    // Method to update chips lost 
    public void updateChipsLost(int userProfileId, int chipsLost) {
        String query = "UPDATE stats SET chips_lost = ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setInt(1, chipsLost);
            stmt.setInt(2, userProfileId);
            stmt.executeUpdate();
            System.out.println("Chips lost updated for user profile ID: " + userProfileId);
        } catch (SQLException e) {
            System.out.println("Error updating chips lost for user profile ID: " + userProfileId);
            e.printStackTrace();
        }
    }

    
    
    
    // Method to update total winnings 
    public void updateTotalWinnings(int userProfileId, int totalWinnings) {
        String query = "UPDATE stats SET total_winnings = ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setInt(1, totalWinnings);
            stmt.setInt(2, userProfileId);
            stmt.executeUpdate();
            System.out.println("Total winnings updated for user profile ID: " + userProfileId);
        } catch (SQLException e) {
            System.out.println("Error updating total winnings for user profile ID: " + userProfileId);
            e.printStackTrace();
        }
    }

    
    
    
    // Method to update times bankrupt
    public void updateTimesBankrupt(int userProfileId, int timesBankrupt) {
        String query = "UPDATE stats SET times_bankrupt = ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setInt(1, timesBankrupt);
            stmt.setInt(2, userProfileId);
            stmt.executeUpdate();
            System.out.println("Times bankrupt updated for user profile ID: " + userProfileId);
        } catch (SQLException e) {
            System.out.println("Error updating times bankrupt for user profile ID: " + userProfileId);
            e.printStackTrace();
        }
    }

    
    
    
    // Method to update highest_chip count
    public void updateHighestChipCount(int userProfileId, int highestChipCount) {
        String query = "UPDATE stats SET highest_chip_count = ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setInt(1, highestChipCount);
            stmt.setInt(2, userProfileId);
            stmt.executeUpdate();
            System.out.println("Highest chip count updated for user profile ID: " + userProfileId);
        } catch (SQLException e) {
            System.out.println("Error updating highest chip count for user profile ID: " + userProfileId);
            e.printStackTrace();
        }
    }
}
