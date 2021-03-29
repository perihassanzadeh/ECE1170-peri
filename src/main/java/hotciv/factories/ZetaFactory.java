package hotciv.factories;

import hotciv.variants.*;

public class ZetaFactory implements GameFactory{

    public AgeStrategy makeAgeStrategy() {
        return new AlphaAgeStrategy();
    }

    public WinnerStrategy makeWinnerStrategy() {
        return new ZetaWinnerStrategy(new BetaWinnerStrategy(), new EpsilonWinnerStrategy(20));
    }

    public AttackStrategy makeAttackStrategy() {
        return new AlphaAttackStrategy();
    }

    public UnitActionStrategy makeUnitActionStrategy() {
        return new AlphaUnitActionStrategy();
    }
}
