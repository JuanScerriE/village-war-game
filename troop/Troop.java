package troop;

public abstract class Troop {
    protected int _health;
    protected int _attackPower;
    protected int _carryingCapacity;
    protected int _movementSpeed;
    protected int _level = 1;
    protected final int _maximumLevel;

    protected Troop(int health, int attackPower, int carryingCapacity, int movementSpeed, int maximumLevel) {
        _health = health;
        _attackPower = attackPower;
        _carryingCapacity = carryingCapacity;
        _movementSpeed = movementSpeed;
        _maximumLevel = maximumLevel;
    }

    public boolean canBeTrained() {
        return _level < _maximumLevel;
    }
    public abstract Troop train();
}
