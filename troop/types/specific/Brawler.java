package troop.types.specific;

import troop.types.Troop;

public class Brawler extends Troop {
    public Brawler() {
        _health = 20;
        _attackPower = 25;
        _carryingCapacity = 10;
        _movementSpeed = 1;
        _maximumLevel = 3;
    }

    @Override
    public Brawler train() {
        if (canBeTrained()) {
            _level++;

            _attackPower += 2;
            _health += 1;
            _carryingCapacity += 3;
            _movementSpeed += 1;
        }

        return this;
    }
}
