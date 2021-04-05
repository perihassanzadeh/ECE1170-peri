package hotciv.variants;

public interface PopulationStrategy {

    public int foodNextPopIncrease(int currPopulation);

    public int populationCap();
}
