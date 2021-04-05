package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Position;

public class AlphaProductionStrategy implements ProductionStrategy{

    public ProductionBook produce(Game game, Position position) {

        int production = 6;
        int food = 0;

        ProductionBook productionBook = new ProductionBook(production, food);

        return productionBook;
    }
}
