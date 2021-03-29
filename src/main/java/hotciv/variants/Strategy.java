package hotciv.variants;

public class Strategy {
    public AgeStrategy makeAlphaAgingStrategy() { return new AlphaAgeStrategy();}
    public AgeStrategy makeBetaAgingStrategy() {return new BetaAgeStrategy();}
    public WinnerStrategy makeAlphaWinnerStrategy() {return new AlphaWinnerStrategy();}
    public WinnerStrategy makeBetaWinnerStrategy() {return new BetaWinnerStrategy();}
    public WinnerStrategy makeEpsilonWinnerStrategy()
    {
        return new EpsilonWinnerStrategy();
    }
    public WinnerStrategy makeZetaWinnerStrategy()
    {
        return new ZetaWinnerStrategy(makeBetaWinnerStrategy(), makeEpsilonWinnerStrategy());
    }
    public UnitActionStrategy makeAlphaUnitActionStrategy() {return new AlphaUnitActionStrategy();}
    public UnitActionStrategy makeGammaUnitActionStrategy() {return new GammaUnitActionStrategy();}
    public WorldLayoutStrategy makeAlphaWorldLayoutStrategy() {return new AlphaWorldLayoutStrategy();}
    public WorldLayoutStrategy makeDeltaWorldLayoutStrategy() {return new DeltaWorldLayoutStrategy();}
    public AttackStrategy makeAlphaAttackStrategy()
    {
        return new AlphaAttackStrategy();
    }
    public AttackStrategy makeEpsilonAttackStrategy()
    {
        return new EpsilonAttackStrategy();
    }
}
