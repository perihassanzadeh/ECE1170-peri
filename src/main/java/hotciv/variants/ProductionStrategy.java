package hotciv.variants;
import hotciv.framework.*;

public interface ProductionStrategy {
    public ProductionBook produce(Game game, Position position);
}
