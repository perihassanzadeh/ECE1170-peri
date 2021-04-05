package hotciv.factories;

import hotciv.variants.*;

public class SemiFactory implements GameFactory {

    public AgeStrategy makeAgeStrategy() {
        return new BetaAgeStrategy();
    }

    public WinnerStrategy makeWinnerStrategy() {
        return new EpsilonWinnerStrategy();
    }

    public AttackStrategy makeAttackStrategy() {
        return new EpsilonAttackStrategy();
    }

    public UnitActionStrategy makeUnitActionStrategy() {
        return new GammaUnitActionStrategy();
    }

    public PopulationStrategy makePopulationStrategy() {
        return new AlphaPopulationStrategy();
    }

    public ProductionStrategy makeProductionStrategy()
    {
        return new AlphaProductionStrategy();
    }
}
