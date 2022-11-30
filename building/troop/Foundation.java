package building.troop;

import building.TroopBuilding;
import building.Building;
import troop.types.Scout;
import resources.ResourceCollection;

// Standard Library
import java.util.LinkedList;
import java.util.List;

public class Foundation extends TroopBuilding<Scout> {
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
    public List<Scout> generateTroops() {
        List<Scout> scouts = new LinkedList<>();

        for (int i = 1; i <= _level; i++) {
            if (Math.random() > 0.5) {
                scouts.add(new Scout());
            }
        }

        return scouts;
    }

    @Override
    public void trainTroops(List<Scout> troops, ResourceCollection villageResources) {
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
}
