package building.resource.specific;

import building.ResourceBuilding;
import resource.collection.ResourceCollection;

public class Mine extends ResourceBuilding {
    public static final ResourceCollection CostToBuild = new ResourceCollection.Builder()
            .setFood(15)
            .setMetal(20)
            .setMana(5)
            .build();

    public static final ResourceCollection CostToUpgrade = new ResourceCollection.Builder()
            .setFood(10)
            .setMetal(20)
            .setMana(10)
            .build();

    public Mine() {
        _maximumLevel = 3;
    }

    @Override
    public boolean hasEnoughToUpgrade(ResourceCollection villageResources) {
        return villageResources.hasEnough(CostToUpgrade);
    }

    @Override
    public Mine upgrade(ResourceCollection villageResources) {
        if (hasEnoughToUpgrade(villageResources) && canBeUpgraded()) {
            villageResources.use(CostToUpgrade);
            _level++;
        }

        return this;
    }

    @Override
    public ResourceCollection generateResources() {
        return new ResourceCollection.Builder().setMetal(_level * 6).build();
    }

    @Override
    public String toString() {
        return "Mine, Level " + _level;
    }
}
