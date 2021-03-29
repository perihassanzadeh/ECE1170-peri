package hotciv.factories;


import hotciv.variants.*;

public class EpsilonFactory implements GameFactory {
    public AgeStrategy makeAgeStrategy() {
        return new AlphaAgeStrategy();
    }

    public WinnerStrategy makeWinnerStrategy() {
        return new EpsilonWinnerStrategy();
    }

    public AttackStrategy makeAttackStrategy() {
        return new EpsilonAttackStrategy();
    }

    public UnitActionStrategy makeUnitActionStrategy() {
        return new AlphaUnitActionStrategy();
    }

}
