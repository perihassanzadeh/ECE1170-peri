package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.standard.GameImpl;

public class ZetaWinnerStrategy implements WinnerStrategy{

    private WinnerStrategy pre20RoundsStrategy;
    private WinnerStrategy post20RoundsStrategy;
    private WinnerStrategy currStrategy;

    public ZetaWinnerStrategy(WinnerStrategy pre20, WinnerStrategy post20)
    {
        pre20RoundsStrategy = pre20;
        post20RoundsStrategy = post20;
        currStrategy = null;
    }

    public Player getWinner(Game g) {

        GameImpl theGame = (GameImpl) g;

        int round = theGame.getRound();

        if(round < 20)
        {
            currStrategy = pre20RoundsStrategy;
        }
        else
        {
            currStrategy = post20RoundsStrategy;
        }

        return currStrategy.getWinner(g);
    }
}
