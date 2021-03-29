package hotciv.factories;

import hotciv.variants.*;

public interface GameFactory {
    public AgeStrategy makeAgeStrategy();

    public WinnerStrategy makeWinnerStrategy();

    public UnitActionStrategy makeUnitActionStrategy();

    public AttackStrategy makeAttackStrategy();
}
