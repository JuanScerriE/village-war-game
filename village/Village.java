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
import troop.TroopCollection;

// Standard Library
import java.util.LinkedList;
import java.util.List;

public class Village {
    private final Player _player;
    private Point _location = null;
    private List<Village> _enemyVillages = null;

    private final List<Army> _armies = Map.getInstance().getArmies();

    private final List<TroopBuilding> _troopBuildings = new LinkedList<>();
    private final List<ResourceBuilding> _resourceBuildings = new LinkedList<>();
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

    public boolean buildAcademy() {
        boolean hasEnough = _resources.hasEnough(Academy.CostToBuild);

        if (!hasEnough) {
            return false;
        }

        _troopBuildings.add(new Academy());

        return true;
    }
    public boolean buildFoundation() {
        boolean hasEnough = _resources.hasEnough(Foundation.CostToBuild);

        if (!hasEnough) {
            return false;
        }

        _troopBuildings.add(new Foundation());

        return true;
    }
    public boolean buildArena() {
        boolean hasEnough = _resources.hasEnough(Arena.CostToBuild);

        if (!hasEnough) {
            return false;
        }

        _troopBuildings.add(new Arena());

        return true;
    }
    public boolean buildFarm() {
        boolean hasEnough = _resources.hasEnough(Farm.CostToBuild);

        if (!hasEnough) {
            return false;
        }

        _resourceBuildings.add(new Farm());

        return true;
    }
    public boolean buildMine() {
        boolean hasEnough = _resources.hasEnough(Mine.CostToBuild);

        if (!hasEnough) {
            return false;
        }

        _resourceBuildings.add(new Mine());

        return true;
    }
    public boolean buildManaTower() {
        boolean hasEnough = _resources.hasEnough(ManaTower.CostToBuild);

        if (!hasEnough) {
            return false;
        }

        _resourceBuildings.add(new ManaTower());

        return true;
    }

//    public <T extends BuilVding> T getBuilding(Class<T> type) {
//        for (var building : _resourceBuildings) {
//            if (building.getClass().isInstance()T.) {
//                return (T)building;
//            }
//        }
//
//        for (var building : _troopBuildings) {
//            if (building != null) {
//                return (T)building;
//            }
//        }
//
//        return null;
//    }

    public boolean upgradeAcademy() {
//        Academy academy = getBuilding();
//
//        if (academy == null) {
//            System.out.println("No academy buildings");
//            return false;
//        }
//
//        if (!academy.hasEnoughToUpgrade(_resources)) {
//            return false;
//        }
//
//        academy.upgrade(_resources);

        return true;
    }
    public boolean upgradeFoundation() {
//        Foundation foundation = getBuilding();
//
//        if (foundation == null) {
//            System.out.println("No foundation buildings");
//            return false;
//        }
//
//        if (!foundation.hasEnoughToUpgrade(_resources)) {
//            return false;
//        }
//
//        foundation.upgrade(_resources);

        return true;
    }

    public boolean upgradeArena() {
//        Arena arena = getBuilding();
//
//        if (arena == null) {
//            System.out.println("No arena buildings");
//            return false;
//        }
//
//        if (!arena.hasEnoughToUpgrade(_resources)) {
//            return false;
//        }
//
//        arena.upgrade(_resources);

        return true;
    }

    public boolean upgradeFarm() {
//        Farm farm = getBuilding();
//
//        if (farm == null) {
//            System.out.println("No farm buildings");
//            return false;
//        }
//
//        if (!farm.hasEnoughToUpgrade(_resources)) {
//            return false;
//        }
//
//        farm.upgrade(_resources);

        return true;
    }

    public boolean upgradeMine() {
//        Mine mine = getBuilding();
//
//        if (mine == null) {
//            System.out.println("No mine buildings");
//            return false;
//        }
//
//        if (!mine.hasEnoughToUpgrade(_resources)) {
//            return false;
//        }
//
//        mine.upgrade(_resources);

        return true;
    }

    public boolean upgradeManaTower() {
//        ManaTower manaTower = getBuilding();
//
//        if (manaTower == null) {
//            System.out.println("No mana tower buildings");
//            return false;
//        }
//
//        if (!manaTower.hasEnoughToUpgrade(_resources)) {
//            return false;
//        }
//
//        manaTower.upgrade(_resources);
//
        return true;
    }

    public Village playerActions() {
        System.out.println(_player.getName());
        _player.actions(this);

        return this;
    }
}
