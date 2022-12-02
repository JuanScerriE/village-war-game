package building.troop;

import building.Building;
import building.TroopBuilding;
import resources.ResourceCollection;
import troop.TroopCollection;
import troop.types.Wizard;

import java.util.LinkedList;
import java.util.List;

public class Academy extends TroopBuilding<Wizard> {
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
    public void trainTroops(List<Wizard> troops, ResourceCollection villageResources) {
        for (var troop : troops) {
            if (villageResources.hasEnough(CostToTrainTroop)) {
                if (troop.canBeTrained()) {
                    troop.train();
                }
            } else {
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Academy, Level " + _level;
    }
}
