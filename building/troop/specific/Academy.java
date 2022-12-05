package building.troop.specific;

import building.TroopBuilding;
import resource.collection.ResourceCollection;
import troop.collection.TroopCollection;
import troop.types.specific.Wizard;

public class Academy extends TroopBuilding {
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
            .setMetal(0)
            .setMana(3)
            .build();

    public Academy() {
        _maximumLevel = 3;
    }

    @Override
    public boolean hasEnoughToUpgrade(ResourceCollection villageResources) {
        return villageResources.hasEnough(CostToUpgrade);
    }

    @Override
    public Academy upgrade(ResourceCollection villageResources) {
        if (hasEnoughToUpgrade(villageResources) && canBeUpgraded()) {
            villageResources.use(CostToUpgrade);
            _level++;
        }

        return this;
    }

    @Override
    public TroopCollection generateTroops() {
        TroopCollection wizards = new TroopCollection();

        for (int i = 1; i <= _level; i++) {
            if (Math.random() > 0.5) {
                wizards.add(new Wizard());
            }
        }

        return wizards;
    }

    @Override
    public String toString() {
        return "Academy, Level " + _level;
    }
}
