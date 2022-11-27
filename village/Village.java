package village;

import player.Player;

public class Village {
    private Player _player;
    public Village friendlyTroopArrival() {
        return this;
    }

    public Village enemyTroopArrival() {
        return this;
    }

    public Village resourceEarning() {
        return this;
    }

    public Village playerActions() {
        _player.actions();

        return this;
    }
}
