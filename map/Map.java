package map;

import troop.Army;
import village.Village;

// Standard Library
import java.util.LinkedList;
import java.util.List;

public class Map {
    private static Map _instance = null;
    private Dimensions _dimensions = null;
    private List<Village> _villages = null;
    private List<Army> _armies = null;

    private Map() {}

    public static Map getInstance() {
        if (_instance == null) {
            _instance = new Map();
        }

        return _instance;
    }

    public Map setDimensions(Dimensions dimensions) {
        if (_dimensions == null) {
            _dimensions = dimensions;
        }

        return this;
    }

    public Map setVillages(List<Village> villages) {
        if (_villages == null) {
            _villages = villages;
        }

        return this;
    }

    public Map setArmies(List<Army> armies) {
        if (_armies == null) {
            _armies = armies;
        }

        return this;
    }
    public Dimensions getDimensions() {
        return _dimensions;
    }

    public List<Village> getVillages() {
        return _villages;
    }

    public List<Army> getArmies() {
        return _armies;
    }
}
