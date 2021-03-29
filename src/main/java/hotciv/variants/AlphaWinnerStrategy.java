package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Player;

public class AlphaWinnerStrategy implements WinnerStrategy{

    public Player getWinner(Game g)
    {
        Player inTurn = g.getPlayerInTurn();
        int gameAge = g.getAge();

        if(gameAge==-3000 && inTurn==Player.RED)
        {
            return Player.RED;
        }
        else
        {
            return null;
        }
    }
}
