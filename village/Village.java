package village;

import building.Building;
import building.ResourceBuilding;
import building.TroopBuilding;
import building.resource.specific.Farm;
import building.resource.specific.ManaTower;
import building.resource.specific.Mine;
import building.troop.specific.Academy;
import building.troop.specific.Arena;
import building.troop.specific.Foundation;
import map.Map;
import player.Player;
import map.Point;
import resource.collection.ResourceCollection;
import troop.collection.TroopCollection;
import troop.collection.dependant.Army;
import troop.types.Troop;
import troop.collection.dependant.Station;
import troop.types.specific.Brawler;
import troop.types.specific.Scout;
import troop.types.specific.Wizard;
import util.CategoryList;

// Standard Library
import java.util.LinkedList;
import java.util.List;

public class Village {
    private final Player _player;
    private Point _location = null;
    private List<Village> _enemyVillages = null;

    private final List<Army> _armies = Map.getInstance().armiesRef();
    private final CategoryList<TroopBuilding> _troopBuildings = new CategoryList<>();
    private final CategoryList<ResourceBuilding> _resourceBuildings = new CategoryList<>();
    private final Station _troops = new Station();

    // Default starting values
    private final ResourceCollection _resources = new ResourceCollection.Builder()
        .setFood(50)
        .setMana(50)
        .setMetal(50)
        .build();
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

    public Station getStation() {
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
            if (army.isFriendly(this) && army.arrivedBack()) {
                _player.notify(army + "\nHas Arrived Back!");
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

    public void removeSelfFromEnemies() {
        for (var village : _enemyVillages) {
            village.removeEnemyVillage(this);
        }
    }

    public Village enemyTroopArrival() {
        List<Army> clonedArmies = new LinkedList<>(_armies);

        for (var army : clonedArmies) {
            if (army.isEnemy(this) && army.arrived()) {
                // The armies check if the village has already
                // been destroyed
                if (isDestroyed()) {
                    army.goBack();
                } else {
                    // If not they will attack the village
                    if (!army.attack().isSuccessful()) {
                        army.getSender().getPlayer().notify("The below army has been defeated\n" + army);
                        _armies.remove(army);
                    }
                }
            }
        }

        return this;
    }

    public Village resourceEarning() {
        for (var building : _resourceBuildings) {
            _resources.move(building.generateResources());
        }

        for (var building : _troopBuildings) {
            _troops.addAll(building.generateTroops());
        }

        return this;
    }

    public void printVillageStats() {
        System.out.println(
                  "Location: " + _location
                + "\nHealth: " + _health
                + "\nResources: " + _resources
        );
    }

    public void printStationedTroopsStats() {
        if (_troops.isEmpty()) {
            System.out.println("No troops!");
        } else {
            System.out.println(_troops);
        }
    }

    public void printBuildingStats() {
        System.out.println("Resource Buildings:");

        if (_resourceBuildings.isEmpty()) {
            System.out.println("No buildings!");
        }

        for (var building : _resourceBuildings) {
            System.out.println("- " + building);
        }

        System.out.println("Troop Buildings:");

        if (_troopBuildings.isEmpty()) {
            System.out.println("No buildings!");
        }

        for (var building : _troopBuildings) {
            System.out.println("- " + building);
        }
    }

    public void printEnemyVillages() {
        System.out.println("Enemy Villages:");

        if (_enemyVillages.isEmpty()) {
            System.out.println("No enemy villages!");
        }

        for (int i = 0; i < _enemyVillages.size(); i++) {
            System.out.println((i + 1) + ". " + _enemyVillages.get(i)._player.getName());

            _enemyVillages.get(i).printVillageStats();
        }
    }

    public void printArmies() {
        if (_armies.isEmpty()) {
            System.out.println("No armies!");
        }

        for (var army : _armies) {
            System.out.println(army);
        }
    }

    public Player getPlayer() {
        return _player;
    }

    // The below are all related to actions which can be
    // performed by the players. Unfortunately, there is not
    // easy way to write a single method which uses Java
    // generics to solve the problem. Different implementations
    // with enumerations are possible for reducing size.

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

        _resources.use(cost.multiply(numOfTrainedTroops));

        if (numOfTrainedTroops < numOfTroops) {
            return Status.SOME_TROOPS_CANNOT_BE_TRAINED;
        }

        return Status.SUCCESS;
    }

    public Status trainWizards(int numOfTroops) {
        if (_troops.sizeOfCategory(Wizard.class) < numOfTroops) {
            return Status.NOT_ENOUGH_TROOPS;
        }

        if (!_troopBuildings.hasCategory(Academy.class)) {
            return Status.NO_BUILDINGS;
        }

        if (!_resources.hasEnough(Academy.CostToTrainTroop.multiply(numOfTroops))) {
            return Status.NOT_ENOUGH_RESOURCES;
        }

        return trainTroops(_troops.getCategory(Wizard.class), Academy.CostToTrainTroop, numOfTroops);
    }

    public Status trainBrawlers(int numOfTroops) {
        if (_troops.sizeOfCategory(Brawler.class) < numOfTroops) {
            return Status.NOT_ENOUGH_TROOPS;
        }

        if (!_troopBuildings.hasCategory(Arena.class)) {
            return Status.NO_BUILDINGS;
        }

        if (!_resources.hasEnough(Arena.CostToTrainTroop.multiply(numOfTroops))) {
            return Status.NOT_ENOUGH_RESOURCES;
        }

        return trainTroops(_troops.getCategory(Brawler.class), Arena.CostToTrainTroop, numOfTroops);
    }

    public Status trainScouts(int numOfTroops) {
        if (_troops.sizeOfCategory(Scout.class) < numOfTroops) {
            return Status.NOT_ENOUGH_TROOPS;
        }

        if (!_troopBuildings.hasCategory(Foundation.class)) {
            return Status.NO_BUILDINGS;
        }

        if (!_resources.hasEnough(Foundation.CostToTrainTroop.multiply(numOfTroops))) {
            return Status.NOT_ENOUGH_RESOURCES;
        }

        return trainTroops(_troops.getCategory(Scout.class), Foundation.CostToTrainTroop, numOfTroops);
    }

    public Status attackVillage(int villageNum, int numOfWizards, int numOfBrawlers, int numOfScouts) {
        if (numOfWizards + numOfBrawlers + numOfScouts <= 0) {
            return Status.NO_EMPTY_ARMY;
        }

        if (!_troops.canSend(numOfWizards, numOfBrawlers, numOfScouts)) {
            return Status.NOT_ENOUGH_TROOPS;
        }

        TroopCollection troops = _troops.send(numOfWizards, numOfBrawlers, numOfScouts);

        _armies.add(new Army(this, _enemyVillages.get(villageNum), troops));

        return Status.SUCCESS;
    }

    public Village playerActions() {
        System.out.println("--------------------------------------------\n" + _player.getName() + "\n");

        _player.printNotifications();

        _player.actions(this);

        return this;
    }
}
