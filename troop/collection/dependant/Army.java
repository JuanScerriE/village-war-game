package troop.collection.dependant;

import map.Point;
import troop.collection.TroopCollection;
import troop.types.Troop;
import resource.collection.ResourceCollection;
import village.Village;

// Standard Library
import java.util.List;
import java.util.WeakHashMap;

public class Army implements TroopCollection.Interface {
    private final Village _from;
    private final Village _to;
    private final TroopCollection _collection;

    private final Point _location;
    private final int _movementSpeed;
    private final int _carryingCapacity;

    private final ResourceCollection _resources = new ResourceCollection.Builder().build();
    private boolean _goBack = false;

    public Army(Village from, Village to, TroopCollection collection) {
        _from = from;
        _to = to;
        _collection = collection;

        _location = new Point(_from.getLocation());
        _movementSpeed = _collection.getSlowestMovementSpeed();
        _carryingCapacity = _collection.getTotalCarryingCapacity();
    }

    public Village getSender() {
        return _from;
    }

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

    public Army march() {
        Village destination = !_goBack ? _to : _from;

        for (int i = 0; i < _movementSpeed; i++) {
            _location.add(_location.directionTo(destination.getLocation()));
        }

        return this;
    }

    public Army goBack() {
        _goBack = true; return this;
    }

    public Army attack() {
        Station defenderStation = _to.getStation();

        defenderStation.kill(getTotalAttackPower());
        kill(defenderStation.getTotalAttackPower());

        if (!isEmpty()) {
            _goBack = true;

            _to.damageVillage(getTotalAttackPower());
            _resources.add(_to.getResources().take(_carryingCapacity));
        }

        return this;
    }

    public Army disband() {
        _from.getResources().move(_resources);
        _from.getStation().receive(_collection);

        return this;
    }

    public boolean isFriendly(Village village) {
        return village == _from;
    }

    public boolean arrivedBack() {
        return _location.distanceFrom(_from.getLocation()) <= _movementSpeed && _goBack;
    }

    public boolean isEnemy(Village village) {
        return village == _to;
    }

    public boolean arrived() {
        return _location.distanceFrom(_to.getLocation()) <= _movementSpeed;
    }

    public boolean isSuccessful() {
        return _goBack;
    }

    @Override
    public String toString() {
        return  "<Army>" +
                "\nSent By: " + _from.getPlayer().getName() +
                "\nAttacking: " + _to.getPlayer().getName() +
                "\nLocation: " + _location +
                "\n" + _collection;
    }
}
