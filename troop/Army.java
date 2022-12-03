package troop;

import map.Point;
import resources.ResourceCollection;
import village.Village;

public class Army {
    private final Village _attacker;
    private final Village _defender;
    private final Point _location;
    private final TroopCollection _troops;
    private final int _movementSpeed;
    private final int _carryingCapacity;
    private boolean _goBack = false;
    private final ResourceCollection _resources = new ResourceCollection.Builder().build();

    public Army(Village attacker, Village defender, TroopCollection troops) {
        _attacker = attacker;
        _defender = defender;
        _location = new Point(attacker.getLocation());
        _troops = troops;
        _movementSpeed = troops.getSlowestMovementSpeed();
        _carryingCapacity = troops.getTotalCarryingCapacity();
    }

    public Army march() {
        for (int i = 0; i < _movementSpeed; i++) {
            if (_goBack) {
                _location.add(_location.directionTo(_defender.getLocation()));
            } else {
                _location.add(_location.directionTo(_attacker.getLocation()));
            }
        }

        return this;
    }

    public Army attack() {
        TroopCollection defenderTroops = _defender.getTroops();

        int defenderAttackPower = defenderTroops.getTotalAttackPower();
        int attackerAttackPower = _troops.getTotalAttackPower();

        defenderTroops.killTroops(attackerAttackPower);
        _troops.killTroops(defenderAttackPower);

        if (!_troops.isEmpty()) {
            goBack();
            _defender.damageVillage(_troops.getTotalAttackPower());
            _resources.add(_defender.getResources().takeResources(_carryingCapacity));
        }

        return this;
    }

    public Army disband() {
        _attacker.getResources().move(_resources);
        _attacker.getTroops().receiveTroops(_troops);

        return this;
    }

    public boolean isSuccessful() {
        return _goBack;
    }

    public Army goBack() {
        _goBack = true;

        return this;
    }

    public boolean isFriendly(Village village) {
        return village == _attacker;
    }

    public boolean arrivedAtAttacker() {
        return _location.distanceFrom(_attacker.getLocation()) <= _movementSpeed;
    }

    public boolean isEnemy(Village village) {
        return village == _defender;
    }

    public boolean arrivedAtDefender() {
        return _location.distanceFrom(_defender.getLocation()) <= _movementSpeed;
    }


    @Override
    public String toString() {
        return "Sent By: " + _attacker.getPlayer().getName() +
        "Attacking:" + _defender.getPlayer().getName() + "\n" +
        "Location: " + _location + "\n" +
        "Movement Speed: " + _troops.getSlowestMovementSpeed() + "\n" +
        "Carrying Capacity: " + _troops.getTotalCarryingCapacity() + "\n" +
        "Attack Power: " + _troops.getTotalAttackPower() + "\n" +
        "Movement Speed: " + _troops.getSlowestMovementSpeed() + "\n" +
        "Number of Troops: " + _troops.size();
    }
}
