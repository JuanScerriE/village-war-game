package building;

import troop.Troop;
import troop.TroopCollection;
import resources.ResourceCollection;

// Standard Library
import java.util.List;

public abstract class TroopBuilding<T extends Troop> extends Building {
    public abstract TroopCollection generateTroops();
    // TODO: Remove this to work better
    public abstract void trainTroops(List<T> troops, ResourceCollection villageResources);
}
