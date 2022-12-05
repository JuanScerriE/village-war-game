package troop.types.specific;

import troop.types.Troop;

public class Scout extends Troop {
    public Scout() {
        _health = 15;
        _attackPower = 15;
        _carryingCapacity = 20;
        _movementSpeed = 4;
        _maximumLevel = 3;
    }

    @Override
    public Scout train() {
        if (canBeTrained()) {
            _level++;

            _attackPower += 3;
            _health += 2;
            _carryingCapacity += 1;
            _movementSpeed += 1;
        }

        return this;
    }
}
