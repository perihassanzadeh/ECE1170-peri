package hotciv.framework;

public class SpyGameObserver implements GameObserver{
    private Boolean worldChange, tileFocChange;
    private Player nextPlayer;
    private Position observedPos;
    private int age;

    public SpyGameObserver(Position pos)
    {
        worldChange=false;
        observedPos=pos;
        tileFocChange=false;
    }

    @Override
    public void worldChangedAt(Position pos) {
        if(pos == observedPos)
        {
            worldChange=true;
        }
    }

    @Override
    public void turnEnds(Player next, int a) {
        nextPlayer=next;
        age=a;
    }

    @Override
    public void tileFocusChangedAt(Position position) {
        if(position == observedPos)
        {
            tileFocChange=true;
        }
    }

    public boolean worldChanged()
    {
        return worldChange;
    }

    public boolean tileFocChanged()
    {
        return tileFocChange;
    }

    public Player getNextPlayer()
    {
        return nextPlayer;
    }

    public int getAge()
    {
        return age;
    }
}
