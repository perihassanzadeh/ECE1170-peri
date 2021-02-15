package hotciv.standard;

import hotciv.framework.*;

public class UnitImpl implements Unit {

    int movecount;
    Player player;
    String type;
    Position pos;

    public void UnitImpl(Position p)
    {
        pos = p;
        movecount = 0;
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

}
