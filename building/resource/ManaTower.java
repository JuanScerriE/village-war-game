package building.resource;

import building.Building;
import building.ResourceBuilding;
import resources.ResourceCollection;

public class ManaTower extends ResourceBuilding {
    public static final ResourceCollection CostToBuild = new ResourceCollection.Builder()
            .setFood(10)
            .setMetal(25)
            .setMana(5)
            .build();

    public static final ResourceCollection CostToUpgrade = new ResourceCollection.Builder()
            .setFood(5)
            .setMetal(20)
            .setMana(15)
            .build();

    public static final int MaximumLevel = 4;

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
        return new ResourceCollection.Builder().setMana(_level * 4).build();
    }

    @Override
    public String toString() {
        return "Mana Tower, Level " + _level;
    }
}
