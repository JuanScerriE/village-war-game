package troop;

import troop.types.Brawler;
import troop.types.Scout;
import troop.types.Wizard;

// Standard Library
import java.util.LinkedList;
import java.util.List;

public class TroopCollection {
    private final List<Wizard> _wizards = new LinkedList<>();
    private final List<Brawler> _brawlers = new LinkedList<>();
    private final List<Scout> _scouts = new LinkedList<>();

    public TroopCollection addAll(List<Wizard> wizards) {
        _wizards.addAll(wizards);
        return this;
    }

    public TroopCollection addAll(List<Brawler> brawlers) {
        _brawlers.addAll(brawlers);
        return this;
    }

    public TroopCollection addAll(List<Scout> scouts) {
        _scouts.addAll(scouts);
        return this;
    }

    public boolean isEmpty() {
        return _scouts.isEmpty() && _wizards.isEmpty() && _brawlers.isEmpty();
    }

    public int getTotalCarryingCapacity() {
        int carryingCapacity = 0;

        for (var wizard : _wizards) {
            carryingCapacity += wizard._carryingCapacity;
        }

        for (var brawler : _brawlers) {
            carryingCapacity += brawler._carryingCapacity;
        }

        for (var scout : _scouts) {
            carryingCapacity += scout._carryingCapacity;
        }

        return carryingCapacity;
    }

    public int getTotalAttackPower() {
        int attackPower = 0;

        for (var wizard : _wizards) {
            attackPower += wizard._attackPower;
        }

        for (var brawler : _brawlers) {
            attackPower += brawler._attackPower;
        }

        for (var scout : _scouts) {
            attackPower += scout._attackPower;
        }

        return attackPower;
    }

    public int getSlowestMovementSpeed() {
        int movementSpeed = Integer.MAX_VALUE;

        for (var wizard : _wizards) {
            movementSpeed = Math.min(wizard._movementSpeed, movementSpeed);
        }

        for (var brawler : _brawlers) {
            movementSpeed = Math.min(brawler._movementSpeed, movementSpeed);
        }

        for (var scout : _scouts) {
            movementSpeed = Math.min(scout._movementSpeed, movementSpeed);
        }

        return movementSpeed;
    }

    public TroopCollection receiveTroops(TroopCollection collection) {
        _wizards.addAll(collection._wizards);
        _brawlers.addAll(collection._brawlers);
        _scouts.addAll(collection._scouts);

        return this;
    }

    public boolean canSendTroops(int numOfWizards, int numOfBrawlers, int numOfScouts) {
        return 0 <= numOfWizards && numOfWizards <= _wizards.size() && 0 <= numOfBrawlers && numOfBrawlers <= _brawlers.size() && 0 <= numOfScouts && numOfScouts <= _scouts.size();
    }

    public TroopCollection sendTroops(int numOfWizards, int numOfBrawlers, int numOfScouts) {
        if (canSendTroops(numOfWizards, numOfBrawlers, numOfScouts)) {
            TroopCollection troops = new TroopCollection();

            if (0 < numOfWizards) {
                List<Wizard> wizards = _wizards.subList(0,numOfWizards-1);
                troops._wizards.addAll(wizards);
                _wizards.removeAll(wizards);
            }

            if (0 < numOfBrawlers) {
                List<Brawler> brawlers = _brawlers.subList(0,numOfBrawlers-1);
                troops._brawlers.addAll(brawlers);
                _brawlers.removeAll(brawlers);
            }

            if (0 < numOfScouts) {
                List<Scout> scouts = _scouts.subList(0,numOfScouts-1);
                troops._scouts.addAll(scouts);
                _scouts.removeAll(scouts);
            }

            return troops;
        }

        return null;
    }

    public TroopCollection killTroops(int attackPower) {
        while (attackPower > 0) {
            double prob = Math.random();

            if (prob > .6) {
                if (!_wizards.isEmpty()) {
                    Wizard wizard = _wizards.get(0);
                    if (attackPower >= wizard._health) {
                        attackPower -= wizard._health;
                        _wizards.remove(wizard);
                    } else {
                        wizard._health -= attackPower;
                        attackPower = 0;
                    }
                }
            } else if (prob > .3) {
                if (!_brawlers.isEmpty()) {
                    Brawler brawler = _brawlers.get(0);
                    if (attackPower >= brawler._health) {
                        attackPower -= brawler._health;
                        _brawlers.remove(brawler);
                    } else {
                        brawler._health -= attackPower;
                        attackPower = 0;
                    }
                }
            } else {
                if (!_scouts.isEmpty()) {
                    Scout scout = _scouts.get(0);
                    if (attackPower >= scout._health) {
                        attackPower -= scout._health;
                        _scouts.remove(scout);
                    } else {
                        scout._health -= attackPower;
                        attackPower = 0;
                    }
                }
            }
        }

        return this;
    }
}
