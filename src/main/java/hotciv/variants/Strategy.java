package hotciv.variants;

public class Strategy {
    public AgeStrategy makeAlphaAgingStrategy() { return new AlphaAgeStrategy();}
    public AgeStrategy makeBetaAgingStrategy() {return new BetaAgeStrategy();}
    public WinnerStrategy makeAlphaWinnerStrategy() {return new AlphaWinnerStrategy();}
    public WinnerStrategy makeBetaWinnerStrategy() {return new BetaWinnerStrategy();}
    public UnitActionStrategy makeAlphaUnitActionStrategy() {return new AlphaUnitActionStrategy();}
    public UnitActionStrategy makeGammaUnitActionStrategy() {return new GammaUnitActionStrategy();}
    public WorldLayoutStrategy makeAlphaWorldLayoutStrategy() {return new AlphaWorldLayoutStrategy();}
    public WorldLayoutStrategy makeDeltaWorldLayoutStrategy() {return new DeltaWorldLayoutStrategy();}
}
