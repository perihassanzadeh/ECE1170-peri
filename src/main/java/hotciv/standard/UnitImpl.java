package hotciv.standard;

import hotciv.framework.*;

public class UnitImpl implements Unit {

    int movecount=1;
    Player player;
    String type;
    Position pos;
    boolean fortified = false;

    public void UnitImpl(Position p, String t)
    {
        pos = p;
        type = t;
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
        int strength;

        if(type == GameConstants.LEGION)
        {
            strength=2;
        }
        else if(type == GameConstants.ARCHER)
        {
            strength=3;
        }
        else if (type == GameConstants.SETTLER){ //Settler
            strength = 3;
        }
        else
        {
            strength = 8;
        }

        if(fortified == true)
        {
            strength = strength*2;
        }

        return strength;
    }

    public int getAttackingStrength()
    {
        int strength;

        if(type == GameConstants.ARCHER)
        {
            strength=2;
        }
        else if(type == GameConstants.LEGION)
        {
            strength=4;
        }
        else if(type == GameConstants.SETTLER)
        {
            strength=0;
        }
        else
        {
            //ufo
            strength = 1;
        }

        return strength;
    }

    public void fortifyUnit()
    {
        if(fortified==true)
        {
            fortified = false;
        }
        else
        {
            setMovecount(0);
            fortified = true;
        }
    }

    public void setOwner(Player p)
    {
        player = p;
    }


}
