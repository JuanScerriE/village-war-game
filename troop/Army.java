package troop;

import map.Point;
import village.ResourceCollection;
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
            // TODO(juan): Check if you are getting a direction of (0, 0)
            if (_goBack) {
                _location.add(_location.directionTo(_attacker.getLocation()));
            } else {
                _location.add(_location.directionTo(_defender.getLocation()));
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
            _resources.add(_defender.getResources().take(_carryingCapacity));
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
        return _location.distanceFrom(_attacker.getLocation()) <= _movementSpeed && _goBack;
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
        "\nAttacking: " + _defender.getPlayer().getName() +
        "\nLocation: " + _location +
        "\nMovement Speed: " + _troops.getSlowestMovementSpeed() +
        "\nCarrying Capacity: " + _troops.getTotalCarryingCapacity() +
        "\nAttack Power: " + _troops.getTotalAttackPower() +
        "\nMovement Speed: " + _troops.getSlowestMovementSpeed() +
        "\nNumber of Troops: " + _troops.size();
    }
}
