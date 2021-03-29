package hotciv.variants;

import hotciv.framework.*;
import hotciv.standard.Utility;

public class EpsilonAttackStrategy implements AttackStrategy{

    DiceRoller dice = new RandomDiceRoll();

    public boolean attack(Game g, Position attacker, Position defender)
    {

        boolean attacksuccess;

        int first_roll = dice.rollDie();
        int second_roll = dice.rollDie();

        int attack = calcAttackStrength(g, attacker);
        int defense = calcDefensiveStrength(g, defender);

        if(first_roll*attack > second_roll*defense)
        {
            attacksuccess=true;
        }
        else
        {
            attacksuccess=false;
        }

        return attacksuccess;
    }

    public int calcAttackStrength(Game g, Position attacker)
    {
        int totalAttackStrength;

        Unit unit = g.getUnitAt(attacker);
        int terrain = terrainFactor(g, attacker);
        int adjacentFriend = adjacentFriendlyFactor(g, attacker, unit);

        totalAttackStrength = terrain*(unit.getAttackingStrength() + adjacentFriend);

        return totalAttackStrength;
    }

    public int calcDefensiveStrength(Game g, Position defender)
    {
        int totalDefenseStrength;

        Unit unit = g.getUnitAt(defender);
        int terrain = terrainFactor(g, defender);
        int adjacentFriend = adjacentFriendlyFactor(g, defender, unit);

        totalDefenseStrength = terrain*(unit.getDefensiveStrength()+adjacentFriend);

        return totalDefenseStrength;
    }

    private int adjacentFriendlyFactor(Game g, Position p, Unit u)
    {
        int factor = 0;

        for (Position position : Utility.get8neighborhoodOf(p))
        {
            Unit adjacentUnit = g.getUnitAt(position);

            if(adjacentUnit != null)
            {
                if(u.getOwner()==adjacentUnit.getOwner())
                {
                    factor++;
                }
            }
        }

        return factor;
    }

    private int terrainFactor(Game g, Position p)
    {
        int factor = 1;

        Tile tile = g.getTileAt(p);
        String tileType = tile.getTypeString();

        if(tileType == GameConstants.FOREST || tileType == GameConstants.HILLS)
        {
            factor = 2;
        }
        else if(g.getCityAt(p) != null)
        {
            factor = 3;
        }

        return factor;
    }

}
