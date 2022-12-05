package building.resource.specific;

import building.ResourceBuilding;
import resource.collection.ResourceCollection;

public class Farm extends ResourceBuilding {
    public static final ResourceCollection CostToBuild = new ResourceCollection.Builder()
            .setFood(10)
            .setMetal(20)
            .setMana(5)
            .build();

    public static final ResourceCollection CostToUpgrade = new ResourceCollection.Builder()
            .setFood(5)
            .setMetal(25)
            .setMana(5)
            .build();

    public Farm() {
        _maximumLevel = 3;
    }

    @Override
    public boolean hasEnoughToUpgrade(ResourceCollection villageResources) {
        return villageResources.hasEnough(CostToUpgrade);
    }

    @Override
    public Farm upgrade(ResourceCollection villageResources) {
        if (hasEnoughToUpgrade(villageResources) && canBeUpgraded()) {
            villageResources.use(CostToUpgrade);
            _level++;
        }

        return this;
    }

    @Override
    public ResourceCollection generateResources() {
        return new ResourceCollection.Builder().setFood(_level * 5).build();
    }

    @Override
    public String toString() {
        return "Farm, Level " + _level;
    }
}
