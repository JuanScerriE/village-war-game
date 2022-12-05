package troop.types;

public abstract class Troop {
    protected int _health;
    protected int _attackPower;
    protected int _carryingCapacity;
    protected int _movementSpeed;

    protected int _maximumLevel;
    protected int _level = 1;

    public int getHealth() {
        return _health;
    }

    public int getAttackPower() {
        return _attackPower;
    }

    public int getCarryingCapacity() {
        return _carryingCapacity;
    }

    public int getMovementSpeed() {
        return _movementSpeed;
    }

    public void damage(int attackPower) {
        _health -= attackPower;
    }

    public boolean canBeTrained() {
        return _level < _maximumLevel;
    }

    public abstract Troop train();
}
