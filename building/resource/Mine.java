package building.resource;

import building.Building;
import building.ResourceBuilding;
import resources.ResourceCollection;

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

    public static final int MaximumLevel = 3;

    @Override
    public boolean hasEnoughToUpgrade(ResourceCollection villageResources) {
        return villageResources.hasEnough(CostToUpgrade);
    }

    @Override
    public Building upgrade(ResourceCollection villageResources) {
        if (hasEnoughToUpgrade(villageResources) && _level <= MaximumLevel) {
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
