package hotciv.variants;

public class EtaPopulationStrategy implements PopulationStrategy{

    public int foodNextPopIncrease(int currPopulation) {

        int foodNeeded = 5 + (3 * currPopulation);

        return foodNeeded;
    }

    public int populationCap() {

        int limit = 9;

        return limit;
    }
}
