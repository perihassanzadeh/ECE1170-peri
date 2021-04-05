package hotciv.factories;

import hotciv.variants.*;

public class EtaFactory implements GameFactory{
    public AgeStrategy makeAgeStrategy() {
        return new AlphaAgeStrategy();
    }

    public WinnerStrategy makeWinnerStrategy() {
        return new AlphaWinnerStrategy();
    }

    public AttackStrategy makeAttackStrategy() {
        return new AlphaAttackStrategy();
    }

    public UnitActionStrategy makeUnitActionStrategy() {
        return new AlphaUnitActionStrategy();
    }

    @Override
    public PopulationStrategy makePopulationStrategy() {
        return new EtaPopulationStrategy();
    }

    public ProductionStrategy makeProductionStrategy()
    {
        return new EtaProductionStrategy();
    }
}
