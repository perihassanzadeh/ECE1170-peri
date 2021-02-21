package hotciv.standard;

import hotciv.framework.*;

public class CityImpl implements City {

    int pop, treasure;
    Player owner;
    String focus, unit;

    public void CityImpl()
    {
        treasure = 0;
        pop = 1;
        focus = GameConstants.productionFocus;
        unit = GameConstants.ARCHER;
    }

    public void CityImpl(Player p, String foc)
    {
        pop = 1;
        treasure = 0;
        owner = p;
        focus = foc;
        unit = GameConstants.ARCHER;
    }

    /** return the owner of this city.
     * @return the player that controls this city.
     */
    public Player getOwner()
    {
        return owner;
    }

    /** return the size of the population.
     * @return population size.
     */
    public int getSize()
    {
        return pop;
    }

    /** return the treasury, i.e. the
     * number of 'money'/production in the
     * city's treasury which can be used to
     * produce a unit in this city
     * @return an integer, the amount of production
     * in the city treasury
     */
    public int getTreasury()
    {
        return treasure;
    }

    /** return the type of unit this city is currently producing.
     * @return a string type defining the unit under production,
     * see GameConstants for valid values.
     */
    public String getProduction()
    {
        return unit;
    }

    public void setProduction(String u)
    {
        unit = u;
    }

    /** return the work force's focus in this city.
     * @return a string type defining the focus, see GameConstants
     * for valid return values.
     */
    public String getWorkforceFocus()
    {
        return focus;
    }

    public void setTreasury(int t)
    {
        treasure = t;
    }
}
