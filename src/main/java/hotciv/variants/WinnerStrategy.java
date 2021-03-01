package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Player;

public interface WinnerStrategy {
    public Player getWinner(Game g);
}
