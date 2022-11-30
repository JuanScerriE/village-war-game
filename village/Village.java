package village;

import building.Building;
import building.ResourceBuilding;
import building.TroopBuilding;
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
            _troops.addAll(building.generateTroops());
        }
        return this;
    }

    public Village playerActions() {
        _player.actions(this);

        return this;
    }
}
