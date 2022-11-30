package troop.types;

import troop.Troop;

public class Wizard extends Troop {
    public Wizard() {
        super(
                15,
                30,
                5,
                2,
                3
        );
    }

    @Override
    public Troop train() {
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
