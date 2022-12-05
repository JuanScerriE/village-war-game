package player;

import village.Village;

public abstract class Player {
    private final String _name;
    public Player(String name) {
        _name = name;
    }
    public String getName() {
        return _name;
    }
    public abstract void actions(Village village);
}
