package hotciv.variants;

import hotciv.framework.*;

public interface AttackStrategy {
    public boolean attack(Game g, Position attacker, Position defender);
}
