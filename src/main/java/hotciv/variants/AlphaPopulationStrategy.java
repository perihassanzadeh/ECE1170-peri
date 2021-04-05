package hotciv.variants;

public class AlphaPopulationStrategy implements  PopulationStrategy{
    @Override
    public int foodNextPopIncrease(int currPopulation) {
        return -1;
    }

    @Override
    public int populationCap() {
        return 1;
    }
}
