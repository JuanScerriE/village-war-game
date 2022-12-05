package building;

import resource.collection.ResourceCollection;

public abstract class Building {
    protected int _maximumLevel;
    protected int _level = 1;

    public abstract boolean hasEnoughToUpgrade(ResourceCollection villageResources);

    public boolean canBeUpgraded() {
        return _level < _maximumLevel;
    }

    public abstract Building upgrade(ResourceCollection villageResources);
    @Override
    public abstract String toString();
}
