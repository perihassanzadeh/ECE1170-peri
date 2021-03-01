package hotciv.standard;

import hotciv.framework.*;

public class UnitImpl implements Unit {

    int movecount;
    Player player;
    String type;
    Position pos;
    boolean fortified;

    public void UnitImpl(Position p, String t)
    {
        pos = p;
        movecount = 0;
        type = t;
        fortified = false;
    }

    public String getTypeString()
    {
        return type;
    }

    public Player getOwner()
    {
        return player;
    }

    public int getMoveCount()
    {
        return movecount;
    }

    public void setMovecount(int moves)
    {
        movecount = moves;
    }

    public int getDefensiveStrength()
    {
        return 0;
    }

    public int getAttackingStrength()
    {
        return 0;
    }

    public void fortifyUnit()
    {
        if(fortified==true)
        {
            fortified = false;
        }
        else
        {
            fortified = true;
        }
    }

    public void setOwner(Player p)
    {
        player = p;
    }

}
