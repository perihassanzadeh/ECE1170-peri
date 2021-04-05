package hotciv.variants;

import hotciv.framework.*;
import hotciv.standard.*;
import hotciv.variants.EpsilonAttackStrategy;

public class ThetaAttackStrategy implements AttackStrategy{

    public boolean attack(Game g, Position attacker, Position defender) {

        AttackStrategy winner = new EpsilonAttackStrategy();
        City c = g.getCityAt(attacker);
        Unit def = g.getUnitAt(defender);

        if(c != null && def != null)
        {
            Boolean wins = winner.attack(g, attacker, defender);

            Unit attack = g.getUnitAt(attacker);
            Player newOwner = attack.getOwner();
            c.setOwner(newOwner);

            return wins;

        }

        return false;
    }
}
