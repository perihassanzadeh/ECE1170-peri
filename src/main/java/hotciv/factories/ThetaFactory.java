package hotciv.factories;

import hotciv.variants.*;

public class ThetaFactory implements GameFactory{

    public AgeStrategy makeAgeStrategy() {
        return new AlphaAgeStrategy();
    }

    public WinnerStrategy makeWinnerStrategy() {
        return new AlphaWinnerStrategy();
    }

    public AttackStrategy makeAttackStrategy() {
        return new ThetaAttackStrategy();
    }

    public UnitActionStrategy makeUnitActionStrategy() {
        return new AlphaUnitActionStrategy();
    }

    @Override
    public PopulationStrategy makePopulationStrategy() {
        return new AlphaPopulationStrategy();
    }

    public ProductionStrategy makeProductionStrategy()
    {
        return new AlphaProductionStrategy();
    }
}
