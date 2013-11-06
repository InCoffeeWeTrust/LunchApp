package se.mdh.dva217.incoffeewetrust.db;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 2013-11-05
 * Time: 11:35
 * To change this template use File | Settings | File Templates.
 */
public interface IStorage {

    enum Type {
        School,
        Favorite,
        Menu
    }

    interface Listener {
        void storageChanged(Type type);
    }

    // queries

    String[] getSchools();

    String[] getFavorites();

    String[] getMenu(String school, int weekNumber);

    // modifiers

    boolean addSchools(String... schools);

    boolean addFavorite(String school);

    boolean removeFavorite(String school);

    boolean addMenu(String school, int weekNumber, String[] dishes);

    public String[][] getMenusForFavorites();

    void addListener(Listener listener);
}
