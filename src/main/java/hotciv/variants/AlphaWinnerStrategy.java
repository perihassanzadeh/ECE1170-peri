package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Player;

public class AlphaWinnerStrategy implements WinnerStrategy{

    public Player getWinner(Game g)
    {
        if(g.getAge()==-3000 && g.getPlayerInTurn()==Player.RED)
        {
            return Player.RED;
        }
        else
        {
            return null;
        }
    }
}
