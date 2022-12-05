package troop.types.specific;

import troop.types.Troop;

public class Wizard extends Troop {
    public Wizard() {
        _health = 15;
        _attackPower = 20;
        _carryingCapacity = 5;
        _movementSpeed = 2;
        _maximumLevel = 3;
    }

    @Override
    public Wizard train() {
        if (canBeTrained()) {
            _level++;

            _attackPower += 2;
            _health += 3;
            _carryingCapacity += 1;
            _movementSpeed += 2;
        }

        return this;
    }
}
