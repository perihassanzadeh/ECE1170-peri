package hotciv.variants;

import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.framework.Player;
import hotciv.framework.Battle;
import java.util.*;

public class EpsilonWinnerStrategy implements WinnerStrategy{

    int round;

    public EpsilonWinnerStrategy()
    {
        round = 0;
    }

    public EpsilonWinnerStrategy(int round_in)
    {
        round = round_in;
    }

    public Player getWinner(Game g){
        GameImpl currentgame = (GameImpl) g;
        List<Battle> battles = currentgame.getBattles();
        int redWins = 0;
        int blueWins = 0;
        int totalbattles = battles.size();

        for (int i=0; i<totalbattles; i++)
        {
            Battle battle = battles.get(i);

            if (battle.getRound() > round)
            {
                if (battle.isSuccessful()==true)
                {
                    Player attacker = battle.getAttacker();
                    if(attacker == Player.RED)
                    {
                        redWins++;
                    }
                    else if(attacker==Player.BLUE)
                    {
                        blueWins++;
                    }
                }

                if (redWins >= 3)
                {
                    return Player.RED;
                }
                else if (blueWins >= 3)
                {
                    return Player.BLUE;
                }
            }
        }
        return null;

    }
}
