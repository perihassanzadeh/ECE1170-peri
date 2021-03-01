package hotciv.variants;

import hotciv.framework.*;

public class BetaWinnerStrategy implements WinnerStrategy{

    public Player getWinner(Game g) {

        Player winner = null;
        for(int i=0; i<=16; i++)
        {
            for(int z=0; z<=16; z++)
            {
                Position p = new Position(i, z);
                City c = g.getCityAt(p);

                if(c != null)
                {
                    Player owner = c.getOwner();

                    if(winner != null && owner!=winner) //No winner
                    {
                        return null;
                    }
                    else
                    {
                        winner = owner;
                    }
                }
            }
        }
        return winner;
    }
}
