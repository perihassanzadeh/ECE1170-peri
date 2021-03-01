package hotciv.variants;

public class BetaAgeStrategy implements AgeStrategy{

    public int calcNextWorldAge(int currAge) {
        int newage;

        if(currAge>=-4000 && currAge<-100)
        {
            newage=currAge+100;
        }
        else if(currAge==-100)
        {
            newage=-1;
        }
        else if(currAge ==-1)
        {
            newage=1;
        }
        else if(currAge==1)
        {
            newage=50;
        }
        else if(currAge<1750)
        {
            newage=currAge+50;
        }
        else if(currAge<1900)
        {
            newage=currAge+25;
        }
        else if(currAge<1970)
        {
            newage=currAge+5;
        }
        else
        {
            newage=currAge+1;
        }

        return newage;
    }
}
