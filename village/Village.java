package village;

import building.Building;
import building.ResourceBuilding;
import building.TroopBuilding;
import building.resource.Farm;
import building.resource.ManaTower;
import building.resource.Mine;
import building.troop.Academy;
import building.troop.Arena;
import building.troop.Foundation;
import map.Map;
import player.Player;
import map.Point;
import resources.ResourceCollection;
import troop.Army;
import troop.Troop;
import troop.TroopCollection;
import troop.types.Wizard;
import util.CategoryList;

// Standard Library
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Village {
    private final Player _player;
    private Point _location = null;
    private List<Village> _enemyVillages = null;

    private final List<Army> _armies = Map.getInstance().getArmies();

    private final CategoryList<TroopBuilding> _troopBuildings = new CategoryList<>();
    private final CategoryList<ResourceBuilding> _resourceBuildings = new CategoryList<>();
    private final TroopCollection _troops = new TroopCollection();
    private final ResourceCollection _resources = new ResourceCollection.Builder().setFood(50).setMana(50).setMetal(50).build();
    private int _health = 1000;

    public Village(Player player) {
        _player = player;
    }

    public Village setLocation(Point location) {
        if (_location == null) {
            _location = location;
        }

        return this;
    }

    public Point getLocation() {
        return _location;
    }

    public Village setEnemyVillages(List<Village> villages) {
        if (_enemyVillages == null) {
            _enemyVillages = villages;
        }

        return this;
    }

    public List<Village> getEnemyVillages() {
        return _enemyVillages;
    }

    public TroopCollection getTroops() {
        return _troops;
    }
    public ResourceCollection getResources() {
        return _resources;
    }

    public Village damageVillage(int attackPower) {
        if (attackPower > _health) {
            _health = 0;
        } else {
            _health -= attackPower;
        }

        return this;
    }

    public Village friendlyTroopArrival() {
        List<Army> clonedArmies = new LinkedList<>(_armies);

        for (var army : clonedArmies) {
            if (army.isFriendly(this) && army.arrivedAtAttacker()) {
                _armies.remove(army.disband());
            }
        }

        return this;
    }

    public void removeEnemyVillage(Village enemyVillage) {
        _enemyVillages.remove(enemyVillage);
    }

    public boolean isDestroyed() {
        return _health <= 0;
    }

    public void removeSelfFromOthers() {
        for (var village : _enemyVillages) {
            village.removeEnemyVillage(this);
        }
    }

    public Village enemyTroopArrival() {
        List<Army> clonedArmies = new LinkedList<>(_armies);

        for (var army : clonedArmies) {
            if (army.isEnemy(this) && army.arrivedAtDefender()) {
                if (isDestroyed()) {
                    army.goBack();
                } else {
                    if (!army.attack().isSuccessful()) {
                        _armies.remove(army);
                    }
                }
            }
        }

        return this;
    }

    public Village resourceEarning() {
        for (var building : _resourceBuildings) {
            _resources.add(building.generateResources());
        }

        for (var building : _troopBuildings) {
            _troops.add(building.generateTroops());
        }

        return this;
    }

    public void PrintVillageStats() {
        System.out.println("Health: " + _health);
        System.out.println(_resources);
    }

    public void PrintStationedTroopsStats() {
        if (_troops.isEmpty()) {
            System.out.println("No troops!");
        } else {
            System.out.println(_troops);
        }
    }

    public void PrintBuildingStats() {
        System.out.println("Resource Buildings:");

        if (_resourceBuildings.isEmpty()) {
            System.out.println("No buildings!");
        }

        for (var building : _resourceBuildings) {
            System.out.println("-" + building);
        }

        System.out.println("Troop Buildings:");

        if (_troopBuildings.isEmpty()) {
            System.out.println("No buildings!");
        }

        for (var building : _troopBuildings) {
            System.out.println("-" + building);
        }
    }

    public void PrintEnemyVillages() {
        System.out.println("Enemy Villages:");

        if (_enemyVillages.isEmpty()) {
            System.out.println("No enemy villages!");
        }

        for (int i = 0; i < _enemyVillages.size(); i++) {
            System.out.println((i + 1) + ". " + _enemyVillages.get(i)._player.getName());
            _enemyVillages.get(i).PrintVillageStats();
        }
    }

    public Status buildAcademy() {
        boolean hasEnough = _resources.hasEnough(Academy.CostToBuild);

        if (!hasEnough) {
            return Status.NOT_ENOUGH_RESOURCES;
        }

        _resources.use(Academy.CostToBuild);
        _troopBuildings.add(new Academy());

        return Status.SUCCESS;
    }
    public Status buildFoundation() {
        boolean hasEnough = _resources.hasEnough(Foundation.CostToBuild);

        if (!hasEnough) {
            return Status.NOT_ENOUGH_RESOURCES;
        }

        _resources.use(Foundation.CostToBuild);
        _troopBuildings.add(new Foundation());

        return Status.SUCCESS;
    }
    public Status buildArena() {
        boolean hasEnough = _resources.hasEnough(Arena.CostToBuild);

        if (!hasEnough) {
            return Status.NOT_ENOUGH_RESOURCES;
        }

        _resources.use(Arena.CostToBuild);
        _troopBuildings.add(new Arena());

        return Status.SUCCESS;
    }
    public Status buildFarm() {
        boolean hasEnough = _resources.hasEnough(Farm.CostToBuild);

        if (!hasEnough) {
            return Status.NOT_ENOUGH_RESOURCES;
        }

        _resources.use(Farm.CostToBuild);
        _resourceBuildings.add(new Farm());

        return Status.SUCCESS;
    }
    public Status buildMine() {
        boolean hasEnough = _resources.hasEnough(Mine.CostToBuild);

        if (!hasEnough) {
            return Status.NOT_ENOUGH_RESOURCES;
        }

        _resources.use(Mine.CostToBuild);
        _resourceBuildings.add(new Mine());

        return Status.SUCCESS;
    }
    public Status buildManaTower() {
        boolean hasEnough = _resources.hasEnough(ManaTower.CostToBuild);

        if (!hasEnough) {
            return Status.NOT_ENOUGH_RESOURCES;
        }

        _resources.use(ManaTower.CostToBuild);
        _resourceBuildings.add(new ManaTower());

        return Status.SUCCESS;
    }

    private Status upgrade(LinkedList<? extends Building> buildings) {
        if (buildings == null) {
            return Status.NO_BUILDINGS;
        }

        for (var building : buildings) {
            if (!building.hasEnoughToUpgrade(_resources)) {
                return Status.NOT_ENOUGH_RESOURCES;
            }

            if (building.canBeUpgraded()) {
                building.upgrade(_resources);

                return Status.SUCCESS;
            }
        }

        return Status.NO_BUILDINGS;
    }

    public Status upgradeAcademy() {
        return upgrade(_troopBuildings.getCategory(Academy.class));
    }
    public Status upgradeFoundation() {
        return upgrade(_troopBuildings.getCategory(Foundation.class));
    }

    public Status upgradeArena() {
        return upgrade(_troopBuildings.getCategory(Arena.class));
    }

    public Status upgradeFarm() {
        return upgrade(_resourceBuildings.getCategory(Farm.class));
    }

    public Status upgradeMine() {
        return upgrade(_resourceBuildings.getCategory(Mine.class));
    }

    public Status upgradeManaTower() {
        return upgrade(_resourceBuildings.getCategory(ManaTower.class));
    }

    private Status trainTroops(List<? extends Troop> troops, ResourceCollection cost, int numOfTroops) {
        int numOfTrainedTroops = 0;

        for (var troop : troops) {
            if (numOfTrainedTroops < numOfTroops && troop.canBeTrained()) {
                troop.train();

                numOfTrainedTroops++;
            }
        }

        _resources.use(cost.mult(numOfTrainedTroops));

        if (numOfTrainedTroops < numOfTroops) {
            return Status.SOME_TROOPS_CANNOT_BE_TRAINED;
        }

        return Status.SUCCESS;
    }
    
    public Status trainWizards(int numOfTroops) {
        if (_troops.getWizards().size() < numOfTroops) {
            return Status.NOT_ENOUGH_TROOPS;
        }

        if (!_troopBuildings.hasCategory(Academy.class)) {
            return Status.NO_BUILDINGS;
        }

        if (!_resources.hasEnough(Academy.CostToTrainTroop.mult(numOfTroops))) {
            return Status.NOT_ENOUGH_RESOURCES;
        }

        return trainTroops(_troops.getWizards(), Academy.CostToTrainTroop, numOfTroops);
    }

    public Status trainBrawlers(int numOfTroops) {
        if (_troops.getBrawlers().size() < numOfTroops) {
            return Status.NOT_ENOUGH_TROOPS;
        }

        if (!_troopBuildings.hasCategory(Arena.class)) {
            return Status.NO_BUILDINGS;
        }

        if (!_resources.hasEnough(Arena.CostToTrainTroop.mult(numOfTroops))) {
            return Status.NOT_ENOUGH_RESOURCES;
        }

        return trainTroops(_troops.getBrawlers(), Arena.CostToTrainTroop, numOfTroops);
    }

    public Status trainScouts(int numOfTroops) {
        if (_troops.getScouts().size() < numOfTroops) {
            return Status.NOT_ENOUGH_TROOPS;
        }

        if (!_troopBuildings.hasCategory(Foundation.class)) {
            return Status.NO_BUILDINGS;
        }

        if (!_resources.hasEnough(Foundation.CostToTrainTroop.mult(numOfTroops))) {
            return Status.NOT_ENOUGH_RESOURCES;
        }

        return trainTroops(_troops.getScouts(), Foundation.CostToTrainTroop, numOfTroops);
    }

    public Village playerActions() {
        System.out.println(_player.getName());

        _player.actions(this);

        return this;
    }
}
