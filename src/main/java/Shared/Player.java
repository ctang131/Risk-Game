package Shared;

import java.util.HashSet;

public class Player {
    private String playerName;
    private HashSet<Territory> territories;

    public Player(String playerName) {
        this.playerName = playerName;
        this.territories = new HashSet<>();
    }

    public String getName() {
        return playerName;
    }

    public HashSet<Territory> getTerritories() {
        return territories;
    }

    public void addTerritory(Territory t) {
        territories.add(t);
    }

    public void removeTerritory(Territory t) {
        try {
            territories.remove(t);
        }
        catch (Exception e) {
            System.out.println("Cannot remove territory!");
        }
    }

}
