package hotciv.framework;

public class Battle {
    private Player attacker;
    private boolean successful;
    private int round;

    public Battle(Player attackUnit, boolean good, int gameround)
    {
        attacker = attackUnit;
        successful = good;
        round = gameround;
    }

    public int getRound()
    {
        return round;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public Player getAttacker()
    {
        return attacker;
    }
}
