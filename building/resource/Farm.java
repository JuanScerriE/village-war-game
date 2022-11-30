package building.resource;

import building.Building;
import building.ResourceBuilding;
import resources.ResourceCollection;

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

    public static final int MaximumLevel = 3;

    @Override
    public boolean hasEnoughToBuild(ResourceCollection villageResources) {
        return villageResources.hasEnough(CostToBuild);
    }

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
        return new ResourceCollection.Builder().setFood(_level * 5).build();
    }
}