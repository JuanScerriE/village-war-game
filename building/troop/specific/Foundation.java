package building.troop.specific;

import building.TroopBuilding;
import resource.collection.ResourceCollection;
import troop.collection.TroopCollection;
import troop.types.specific.Scout;

// Standard Library

public class Foundation extends TroopBuilding {
    public static final ResourceCollection CostToBuild = new ResourceCollection.Builder()
            .setFood(10)
            .setMetal(15)
            .setMana(10)
            .build();

    public static final ResourceCollection CostToUpgrade = new ResourceCollection.Builder()
            .setFood(5)
            .setMetal(10)
            .setMana(20)
            .build();

    public static final ResourceCollection CostToTrainTroop = new ResourceCollection.Builder()
            .setFood(2)
            .setMetal(2)
            .setMana(1)
            .build();

    public Foundation() {
        _maximumLevel = 3;
    }

    @Override
    public boolean hasEnoughToUpgrade(ResourceCollection villageResources) {
        return villageResources.hasEnough(CostToUpgrade);
    }

    @Override
    public Foundation upgrade(ResourceCollection villageResources) {
        if (hasEnoughToUpgrade(villageResources) && canBeUpgraded()) {
            villageResources.use(CostToUpgrade);
            _level++;
        }

        return this;
    }

    @Override
    public TroopCollection generateTroops() {
        TroopCollection scouts = new TroopCollection();

        for (int i = 1; i <= _level; i++) {
            if (Math.random() > 0.5) {
                scouts.add(new Scout());
            }
        }

        return scouts;
    }

    @Override
    public String toString() {
        return "Foundation, Level " + _level;
    }
}
