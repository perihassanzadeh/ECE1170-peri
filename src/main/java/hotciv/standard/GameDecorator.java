package hotciv.standard;

import hotciv.framework.*;

public class GameDecorator implements Game {

    private Game theGame;
    private String transcript;

    public GameDecorator(Game g)
    {
        theGame = g;
        transcript = "Start of game. \n";
    }

    public Tile getTileAt(Position p )
    {
        return theGame.getTileAt(p);
    }

    public Unit getUnitAt(Position p )
    {
        return theGame.getUnitAt(p);
    }

    public City getCityAt(Position p )
    {
        return theGame.getCityAt(p);
    }

    public Player getPlayerInTurn()
    {
        return theGame.getPlayerInTurn();
    }

    public Player getWinner()
    {
        Player wnner = getWinner();

        if (wnner!= null)
        {
            transcript = transcript + "" + wnner + " is the winner!";
        }

        return theGame.getWinner();
    }

    public int getAge()
    {
        return theGame.getAge();
    }

    // === Mutator methods ======================================

    public boolean moveUnit( Position from, Position to )
    {
        Player player = getPlayerInTurn();
        String unitType  = getUnitAt(from).getTypeString();

        transcript = transcript + "" + player + " moves " + unitType + " from " + from + " to " + to + ". \n";

        return theGame.moveUnit(from, to);
    }

    public void endOfTurn()
    {
        transcript = transcript + "" + getPlayerInTurn() + " ends turn.\n";
        theGame.endOfTurn();
    }

    public void changeWorkForceFocusInCityAt( Position p, String balance )
    {
        String workForce;

        if(balance == "hammer")
        {
            workForce = "productionFocus";
        }
        else {
            workForce = "foodFocus";
        }

        Player player = getPlayerInTurn();
        transcript = transcript + "" + player + " changes work force focus in city at " + p + " to " + workForce + ".\n";

        theGame.changeWorkForceFocusInCityAt(p, balance);
    }

    public void changeProductionInCityAt( Position p, String unitType )
    {
        Player player = getPlayerInTurn();
        transcript = transcript + "" + player + " changes production in city at " + p + " to " + unitType + ". \n";

        theGame.changeProductionInCityAt(p, unitType);
    }

    public void performUnitActionAt( Position p )
    {
        Player player = getPlayerInTurn();
        String unitType = getUnitAt(p).getTypeString();

        transcript = transcript + "" + player + " performs unit action on " + unitType + " at " + p + ". \n";

        theGame.performUnitActionAt(p);
    }

    @Override
    public void addObserver(GameObserver observer) {

    }

    @Override
    public void setTileFocus(Position position) {

    }

    @Override
    public void worldChangedAt(Position pos) {

    }

    public String getTranscript(){
        return transcript;
    }
}
