package hotciv.factories;

import hotciv.variants.*;

public class BetaFactory implements GameFactory{
    public AgeStrategy makeAgeStrategy() {
        return new BetaAgeStrategy();
    }

    public WinnerStrategy makeWinnerStrategy() {
        return new BetaWinnerStrategy();
    }

    public AttackStrategy makeAttackStrategy() {
        return new AlphaAttackStrategy();
    }

    public UnitActionStrategy makeUnitActionStrategy() {
        return new AlphaUnitActionStrategy();
    }

    public WorldLayoutStrategy makeWorldLayoutStrategy(){
        return new AlphaWorldLayoutStrategy();
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
