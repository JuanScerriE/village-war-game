import troop.Troop;
import troop.types.Brawler;
import troop.types.Scout;
import troop.types.Wizard;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        new Game().start();
        CategoryList<Troop> _troops = new CategoryList<>();
        _troops.add(new Wizard());
        _troops.add(new Wizard());
        _troops.add(new Brawler());
        _troops.add(new Brawler());
        _troops.add(new Brawler());
        _troops.add(new Scout());
        _troops.add(new Scout());
        List<Scout> scouts  = _troops.getCategory(Scout.class);
        for (var troop : _troops) {
            System.out.println(troop);
        }
    }
}
