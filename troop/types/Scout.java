package troop.types;

import troop.Troop;

public class Scout extends Troop {
    public Scout() {
        super(
                15,
                15,
                20,
                4,
                3
        );
    }

    @Override
    public Troop train() {
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
