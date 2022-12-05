package troop.collection;

import troop.types.Troop;
import troop.types.specific.Wizard;
import troop.types.specific.Brawler;
import troop.types.specific.Scout;
import util.CategoryList;

// Standard Library
import java.util.List;

public class TroopCollection {
    private final CategoryList<Troop> _troops = new CategoryList<>();

    public <T extends Troop> boolean add(T troop) {
        return _troops.add(troop);
    }

    public <T extends Troop> boolean addList(List<T> list) {
        return _troops.addList(list);
    }

    public boolean addAll(TroopCollection collection) {
        return _troops.addAll(collection._troops);
    }

    public <T extends Troop> boolean remove(T troop) {
        return _troops.remove(troop);
    }
    public <T extends Troop> boolean removeList(List<T> list) {
        return _troops.removeList(list);
    }
    public boolean removeAll(TroopCollection collection) {
        return _troops.removeAll(collection._troops);
    }

    public boolean isEmpty() {
        return _troops.isEmpty();
    }

    public int size() {
        return _troops.size();
    }

    public <T extends Troop> int sizeOfCategory(Class<T> valueType) {
        return _troops.sizeOfCategory(valueType);
    }

    public <T extends Troop> List<T> getCategory(Class<T> valueType) {
        return _troops.getCategory(valueType);
    }

    public int getTotalAttackPower() {
        int totalAttackPower = 0;

        for (var troop : _troops) {
            totalAttackPower += troop.getAttackPower();
        }

        return totalAttackPower;
    }

    public int getTotalCarryingCapacity() {
        int totalCarryingCapacity = 0;

        for (var troop : _troops) {
            totalCarryingCapacity += troop.getCarryingCapacity();
        }

        return totalCarryingCapacity;
    }

    public int getSlowestMovementSpeed() {
        if (isEmpty()) {
            return 0;
        }

        int slowestMovementSpeed = Integer.MAX_VALUE;

        for (var troop : _troops) {
            slowestMovementSpeed = Math.min(troop.getMovementSpeed(), slowestMovementSpeed);
        }

        return slowestMovementSpeed;
    }

    public TroopCollection receive(TroopCollection collection) {
        addAll(collection); return this;
    }

    public boolean canSend(int numOfWizards, int numOfBrawlers, int numOfScouts) {
        return 0 <= numOfWizards && numOfWizards <= _troops.sizeOfCategory(Wizard.class) &&
               0 <= numOfBrawlers && numOfBrawlers <= _troops.sizeOfCategory(Brawler.class) &&
               0 <= numOfScouts && numOfScouts <= _troops.sizeOfCategory(Scout.class);
    }

    public TroopCollection send(int numOfWizards, int numOfBrawlers, int numOfScouts) {
        if (canSend(numOfWizards, numOfBrawlers, numOfScouts)) {
            TroopCollection collection = new TroopCollection();

            if (_troops.sizeOfCategory(Wizard.class) > 0) {
                collection.addList(_troops
                        .getCategory(Wizard.class).subList(0, numOfWizards)
                );
            }

            if (_troops.sizeOfCategory(Brawler.class) > 0) {
                collection.addList(_troops
                        .getCategory(Brawler.class).subList(0, numOfBrawlers)
                );
            }

            if (_troops.sizeOfCategory(Scout.class) > 0) {
                collection.addList(_troops
                        .getCategory(Scout.class).subList(0, numOfScouts)
                );
            }

            removeAll(collection);

            return collection;
        }

        return null;
    }

    public TroopCollection kill(int attackPower) {

        while (attackPower > 0) {
            if (isEmpty()) {
                return this;
            }

            double prob = Math.random();

            Troop troop = null;

            if (prob > .6 && _troops.sizeOfCategory(Wizard.class) > 0) {
                troop = _troops.getCategory(Wizard.class).getFirst();
            } else if (prob > .3 && _troops.sizeOfCategory(Brawler.class) > 0) {
                troop = _troops.getCategory(Brawler.class).getFirst();
            } else if (_troops.sizeOfCategory(Scout.class) > 0) {
                troop = _troops.getCategory(Scout.class).getFirst();
            }

            if (troop == null) {
                continue;
            }

            if (attackPower >= troop.getHealth()) {
                attackPower -= troop.getHealth();

                _troops.remove(troop);
            } else {
                troop.damage(attackPower);

                attackPower = 0;
            }
        }

        return this;
    }

    @Override
    public String toString() {
        return  "Number of Wizards: " + _troops.sizeOfCategory(Wizard.class)
              + "\nNumber of Brawlers: " + _troops.sizeOfCategory(Brawler.class)
              + "\nNumber of Scouts: " + _troops.sizeOfCategory(Scout.class)
              + "\nTotal Attack Power: " + getTotalAttackPower()
              + "\nTotal Carrying Capacity: " + getTotalCarryingCapacity()
              + "\nSlowest Movement Speed: " + getSlowestMovementSpeed();
    }

    public interface Interface {
        <T extends Troop> boolean add(T troop);
        <T extends Troop> boolean addList(List<T> list);
        boolean addAll(TroopCollection collection);
        <T extends Troop> boolean remove(T troop);
        <T extends Troop> boolean removeList(List<T> list);
        boolean removeAll(TroopCollection collection);
        boolean isEmpty();
        int size();
        <T extends Troop> int sizeOfCategory(Class<T> valueType);
        <T extends Troop> List<T> getCategory(Class<T> valueType);
        int getTotalAttackPower();
        int getTotalCarryingCapacity();
        int getSlowestMovementSpeed();
        TroopCollection receive(TroopCollection collection);
        boolean canSend(int numOfWizards, int numOfBrawlers, int numOfScouts);
        TroopCollection send(int numOfWizards, int numOfBrawlers, int numOfScouts);
        TroopCollection kill(int attackPower);
    }
}
