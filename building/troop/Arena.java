package building.troop;

import building.TroopBuilding;
import building.Building;
import troop.TroopCollection;
import troop.types.Brawler;
import village.ResourceCollection;

// Standard Library
import java.util.List;

public class Arena extends TroopBuilding<Brawler> {
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
            .setMetal(3)
            .setMana(1)
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
        TroopCollection brawlers = new TroopCollection();

        for (int i = 1; i <= _level; i++) {
            if (Math.random() > 0.5) {
                brawlers.add(new Brawler());
            }
        }

        return brawlers;
    }

    @Override
    public void trainTroops(List<Brawler> troops, ResourceCollection villageResources) {
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
    public boolean canBeUpgraded() {
        return _level < MaximumLevel;
    }

    @Override
    public String toString() {
        return "Arena, Level " + _level;
    }
}
