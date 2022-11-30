package troop.types;

import troop.Troop;

public class Brawler extends Troop {
    public Brawler() {
        super(
                20,
                25,
                10,
                1,
                3
        );
    }

    @Override
    public Troop train() {
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
