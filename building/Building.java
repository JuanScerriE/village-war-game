package building;

import resources.ResourceCollection;

public abstract class Building {
    protected int _level = 1;

    public abstract boolean hasEnoughToBuild(ResourceCollection villageResources);
    public abstract boolean hasEnoughToUpgrade(ResourceCollection villageResources);
    public abstract Building upgrade(ResourceCollection villageResources);
    @Override
    public abstract String toString();
}
