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

    public TroopCollection add(Wizard wizard) {
        _wizards.add(wizard);
        return this;
    }
    public TroopCollection add(Brawler brawler) {
        _brawlers.add(brawler);
        return this;
    }
    public TroopCollection add(Scout scout) {
        _scouts.add(scout);
        return this;
    }
    public List<Wizard> getWizards() {
        return _wizards;
    }
    public List<Brawler> getBrawlers() {
        return _brawlers;
    }
    public List<Scout> getScouts() {
        return _scouts;
    }
    public TroopCollection add(TroopCollection troops) {
        _wizards.addAll(troops._wizards);
        _brawlers.addAll(troops._brawlers);
        _scouts.addAll(troops._scouts);
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
                LinkedList<Wizard> wizards = new LinkedList<>(_wizards.subList(0,numOfWizards-1));
                troops._wizards.addAll(wizards);
                _wizards.removeAll(wizards);
            }

            if (0 < numOfBrawlers) {
                LinkedList<Brawler> brawlers = new LinkedList<>(_brawlers.subList(0,numOfBrawlers-1));
                troops._brawlers.addAll(brawlers);
                _brawlers.removeAll(brawlers);
            }

            if (0 < numOfScouts) {
                LinkedList<Scout> scouts = new LinkedList<>(_scouts.subList(0,numOfScouts-1));
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

    public int size() {
        return _scouts.size() + _brawlers.size() + _wizards.size();
    }

    @Override
    public String toString() {
        return "Total Attack Power: " + getTotalAttackPower() + "\nTotal Carrying Capacity: "
                + getTotalCarryingCapacity() + "\nSlowest Movement Speed: " + getSlowestMovementSpeed();
    }
}
