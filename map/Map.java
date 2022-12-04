package map;

import troop.Army;
import village.Village;

// Standard Library
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

    public Map setDimensionsRef(Dimensions dimensions) {
        if (_dimensions == null) {
            _dimensions = dimensions;
        }

        return this;
    }

    public Map setVillagesRef(List<Village> villages) {
        if (_villages == null) {
            _villages = villages;
        }

        return this;
    }

    public Map setArmiesRef(List<Army> armies) {
        if (_armies == null) {
            _armies = armies;
        }

        return this;
    }
    public Dimensions dimensionsRef() {
        return _dimensions;
    }

    public List<Village> villagesRef() {
        return _villages;
    }

    public List<Army> armiesRef() {
        return _armies;
    }
}
