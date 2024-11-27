package data;

import java.time.LocalDateTime;

public class UserProfile {
    private String profileName;
    private int profileId;
    private int chipsAmount;
    private int wins;
    private int losses;
    private int chipsWon;
    private int chipsLost;
    private LocalDateTime lastLogin;

    public UserProfile(String profileName, int profileId) {
        this.profileName = profileName;
        this.profileId = profileId;
        this.chipsAmount = 0;
        this.wins = 0;
        this.losses = 0;
        this.chipsWon = 0;
        this.chipsLost = 0;
        this.lastLogin = LocalDateTime.now();  
    }

    // Getters
    public String getProfileName() { return profileName; }
    public int getProfileId() { return profileId; }
    public int getChipsAmount() { return chipsAmount; }
    public int getWins() { return wins; }
    public int getLosses() { return losses; }
    public int getChipsWon() { return chipsWon; }
    public int getChipsLost() { return chipsLost; }
    public LocalDateTime getLastLogin() { return lastLogin; }

    // Setters
    public void setChipsAmount(int chipsAmount) { this.chipsAmount = chipsAmount; }
    public void setWins(int wins) { this.wins = wins; }
    public void setLosses(int losses) { this.losses = losses; }
    public void setChipsWon(int chipsWon) { this.chipsWon = chipsWon; }
    public void setChipsLost(int chipsLost) { this.chipsLost = chipsLost; }
    public void setLastLogin(LocalDateTime lastLogin) { this.lastLogin = lastLogin; }

    // Methods to update profile stats
    public void addChips(int amount) { this.chipsAmount += amount; }
    public void subtractChips(int amount) { this.chipsAmount -= amount; }
    public void recordWin(int chipsWon) {
        this.wins++;
        this.chipsWon += chipsWon;
        addChips(chipsWon);
    }
    public void recordLoss(int chipsLost) {
        this.losses++;
        this.chipsLost += chipsLost;
        subtractChips(chipsLost);
    }

    // Display Profile Info
    @Override
    public String toString() {
        return "UserProfile [Name=" + profileName + ", ID=" + profileId +
               ", Chips=" + chipsAmount + ", Wins=" + wins +
               ", Losses=" + losses + ", Chips Won=" + chipsWon +
               ", Chips Lost=" + chipsLost + ", Last Login=" + lastLogin + "]";
    }
}
