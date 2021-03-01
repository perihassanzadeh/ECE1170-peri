package hotciv.variants;

public class AlphaAgeStrategy implements AgeStrategy{

    public int calcNextWorldAge(int currAge)
    {
        int newage;

        newage = currAge+100;

        return newage;
    }
}
