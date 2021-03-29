package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Position;

public class AlphaAttackStrategy implements AttackStrategy{

    public boolean attack(Game g, Position attacker, Position defender) {
        return true;
    }
}
