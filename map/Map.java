package map;

import village.Village;

// Standard Library
import java.util.List;

public class Map {
    private int _rows;
    private int _cols;

    private List<Village> _villages;

    public Map(int rows, int cols, List<Village> villages) {
        _rows = rows;
        _cols = cols;
        _villages = villages;
    }

    public List<Village> getVillages() {
        return _villages;
    }
}
