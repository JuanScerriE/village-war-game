package building.resource.specific;

import building.ResourceBuilding;
import resource.collection.ResourceCollection;

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

    public ManaTower() {
        _maximumLevel = 4;
    }

    @Override
    public boolean hasEnoughToUpgrade(ResourceCollection villageResources) {
        return villageResources.hasEnough(CostToUpgrade);
    }

    @Override
    public ManaTower upgrade(ResourceCollection villageResources) {
        if (hasEnoughToUpgrade(villageResources) && canBeUpgraded()) {
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
