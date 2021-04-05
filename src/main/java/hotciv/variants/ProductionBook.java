package hotciv.variants;

public class ProductionBook {
    private int production, food;

    public ProductionBook(int prod, int food_in)
    {
        production = prod;
        food = food_in;
    }

    public int getProduction()
    {
        return production;
    }

    public int getFood()
    {
        return food;
    }
}
