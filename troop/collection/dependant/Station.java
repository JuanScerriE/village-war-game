package troop.collection.dependant;

import troop.collection.TroopCollection;
import troop.types.Troop;

// Standard Library
import java.util.List;

public class Station implements TroopCollection.Interface {
    private final TroopCollection _collection = new TroopCollection();

    public <T extends Troop> boolean add(T troop) {
        return _collection.add(troop);
    }

    public <T extends Troop> boolean addList(List<T> list) {
        return _collection.addList(list);
    }

    public boolean addAll(TroopCollection collection) {
        return _collection.addAll(collection);
    }

    public <T extends Troop> boolean remove(T troop) {
        return _collection.remove(troop);
    }

    public <T extends Troop> boolean removeList(List<T> list) {
        return _collection.removeList(list);
    }

    public boolean removeAll(TroopCollection collection) {
        return _collection.removeAll(collection);
    }

    public boolean isEmpty() {
        return _collection.isEmpty();
    }

    public int size() {
        return _collection.size();
    }

    public <T extends Troop> int sizeOfCategory(Class<T> valueType) {
        return _collection.sizeOfCategory(valueType);
    }

    public <T extends Troop> List<T> getCategory(Class<T> valueType) {
        return _collection.getCategory(valueType);
    }

    public int getTotalAttackPower() {
        return _collection.getTotalAttackPower();
    }

    public int getTotalCarryingCapacity() {
        return _collection.getTotalCarryingCapacity();
    }

    public int getSlowestMovementSpeed() {
        return _collection.getSlowestMovementSpeed();
    }

    public TroopCollection receive(TroopCollection collection) {
        return _collection.receive(collection);
    }

    public boolean canSend(int numOfWizards, int numOfBrawlers, int numOfScouts) {
        return _collection.canSend(numOfWizards, numOfBrawlers, numOfScouts);
    }

    public TroopCollection send(int numOfWizards, int numOfBrawlers, int numOfScouts) {
        return _collection.send(numOfWizards, numOfBrawlers, numOfScouts);
    }

    public TroopCollection kill(int attackPower) {
        return _collection.kill(attackPower);
    }

    @Override
    public String toString() {
        return "<Station>\n" + _collection;
    }
}
