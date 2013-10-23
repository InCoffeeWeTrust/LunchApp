package se.mdh.dva217.incoffeewetrust.containers;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 2013-10-22
 * Time: 14:31
 * To change this template use File | Settings | File Templates.
 */
public interface FavoritesIF {

    Set<Dish> getDishes(int id, long lastUpdate);
}
