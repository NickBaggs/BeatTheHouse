package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    
    
    // Method to update wins
    public void incrementWins(int userProfileId) {
        String query = "UPDATE stats SET wins = wins + 1 WHERE user_profile_id = ?";
        
        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userProfileId); 
            stmt.executeUpdate();  
            System.out.println("Wins incremented for user profile ID: " + userProfileId);
        } catch (SQLException e) {
            System.out.println("Error incrementing wins for user profile ID: " + userProfileId);
            e.printStackTrace();
        }
    }

    
    
    // Method to update losses
    public void incrementLosses(int userProfileId) {
        String query = "UPDATE stats SET losses = losses + 1 WHERE user_profile_id = ?";
        
        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userProfileId); 
            stmt.executeUpdate();  
            System.out.println("Losses incremented for user profile ID: " + userProfileId);
        } catch (SQLException e) {
            System.out.println("Error incrementing losses for user profile ID: " + userProfileId);
            e.printStackTrace();
        }
    }
    
    public void incrementTimesBankrupt(int userProfileId) {
        String query = "UPDATE stats SET times_bankrupt = times_bankrupt + 1 WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userProfileId); 
            stmt.executeUpdate();  
            System.out.println("Times bankrupt incremented for user profile ID: " + userProfileId);
        } catch (SQLException e) {
            System.out.println("Error incrementing times bankrupt for user profile ID: " + userProfileId);
            e.printStackTrace();
        }
    }


    
 // Method to add (or subtract) an amount from chip_count
    public void addToChipCount(int userProfileId, int amount) {
        String query = "UPDATE stats SET chip_count = chip_count + ? WHERE user_profile_id = ?";
        
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, amount);  
            stmt.setInt(2, userProfileId);  
            stmt.executeUpdate();

            
            System.out.println("Chip count updated by " + amount + " for user profile ID: " + userProfileId);

        } catch (SQLException e) {
            System.out.println("Error updating chip count for user profile ID: " + userProfileId);
            e.printStackTrace();
        }
    }
    
    
 // Method to add (or subtract) an amount from chips_won
    public void addToChipsWon(int userProfileId, int amount) {
        String query = "UPDATE stats SET chips_won = chips_won + ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, amount);  
            stmt.setInt(2, userProfileId);
            stmt.executeUpdate();

            
            System.out.println("Chips won updated by " + amount + " for user profile ID: " + userProfileId);

        } catch (SQLException e) {
            System.out.println("Error updating chips won for user profile ID: " + userProfileId);
            e.printStackTrace();
        }
    }

    
    // Method to add (or subtract) an amount from chips_lost
    public void addToChipsLost(int userProfileId, int amount) {
        String query = "UPDATE stats SET chips_lost = chips_lost + ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, amount); 
            stmt.setInt(2, userProfileId);
            stmt.executeUpdate();

           
            System.out.println("Chips lost updated by " + amount + " for user profile ID: " + userProfileId);

           
        } catch (SQLException e) {
            System.out.println("Error updating chips lost for user profile ID: " + userProfileId);
            e.printStackTrace();
        }
    }


    
    
    // Method to update chip count
    public void updateChipCount(int userProfileId, int newChipCount) {
        String query = "UPDATE stats SET chip_count = ? WHERE user_profile_id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, newChipCount);  
            stmt.setInt(2, userProfileId);
            stmt.executeUpdate();  
            System.out.println("Chip count updated to " + newChipCount + " for user profile ID: " + userProfileId);
        } catch (SQLException e) {
            System.out.println("Error updating chip count for user profile ID: " + userProfileId);
            e.printStackTrace();
        }
    }

    
    
    // method to update chips won 
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

    
    // Method to update highest chip count
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
    
    
    public int getWins(int userProfileId) {
        String query = "SELECT wins FROM stats WHERE user_profile_id = ?";
        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userProfileId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("wins");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving wins for user profile ID: " + userProfileId);
            e.printStackTrace();
        }
        return 0;  
    }

    
    // Getter method for losses
    public int getLosses(int userProfileId) {
        String query = "SELECT losses FROM stats WHERE user_profile_id = ?";
        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userProfileId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("losses");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving losses for user profile ID: " + userProfileId);
            e.printStackTrace();
        }
        return 0;
    }

    
    
    // Getter method for chip count
    public int getChipCount(int userProfileId) {
        String query = "SELECT chip_count FROM stats WHERE user_profile_id = ?";
        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userProfileId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("chip_count");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving chip count for user profile ID: " + userProfileId);
            e.printStackTrace();
        }
        return 0;
    }

    
    
    // Getter method for chips won
    public int getChipsWon(int userProfileId) {
        String query = "SELECT chips_won FROM stats WHERE user_profile_id = ?";
        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userProfileId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("chips_won");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving chips won for user profile ID: " + userProfileId);
            e.printStackTrace();
        }
        return 0;
    }

    
    
    // Getter method for chips lost
    public int getChipsLost(int userProfileId) {
        String query = "SELECT chips_lost FROM stats WHERE user_profile_id = ?";
        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userProfileId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("chips_lost");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving chips lost for user profile ID: " + userProfileId);
            e.printStackTrace();
        }
        return 0;
    }

    
    
    // Getter method for total winnings
    public int getTotalWinnings(int userProfileId) {
        String query = "SELECT total_winnings FROM stats WHERE user_profile_id = ?";
        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userProfileId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total_winnings");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving total winnings for user profile ID: " + userProfileId);
            e.printStackTrace();
        }
        return 0;
    }

    
    
    // Getter method for times bankrupt
    public int getTimesBankrupt(int userProfileId) {
        String query = "SELECT times_bankrupt FROM stats WHERE user_profile_id = ?";
        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userProfileId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("times_bankrupt");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving times bankrupt for user profile ID: " + userProfileId);
            e.printStackTrace();
        }
        return 0;
    }

    
    
    // Getter method for highest chip count
    public int getHighestChipCount(int userProfileId) {
        String query = "SELECT highest_chip_count FROM stats WHERE user_profile_id = ?";
        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userProfileId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("highest_chip_count");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving highest chip count for user profile ID: " + userProfileId);
            e.printStackTrace();
        }
        return 0;
    }

}
