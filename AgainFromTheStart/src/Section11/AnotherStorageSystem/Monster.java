package Section11.AnotherStorageSystem;

import java.util.ArrayList;
import java.util.List;

public class Monster implements ISaveable{

    private String name;
    private int hitPoints;
    private int strength;

    public Monster (String name, int hitPoints, int strength){
        this.name = name;
        this.hitPoints = hitPoints;
        this.strength = strength;

    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getStrength() {
        return strength;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){

        return String.format("Player {name= '%s', hitPoints= %d, strength=%d}",name, hitPoints, strength);
    }

    @Override
    public List<String> write() {
        List<String> values = new ArrayList<String>();
        values.add(0, name);
        values.add(1, "" + hitPoints);
        values.add(2, "" + strength);
        return values;
    }

    @Override
    public void read(List<String> list) {
        if (list != null && !list.isEmpty()){
            name = list.get(0);
            hitPoints =  Integer.parseInt(list.get(1));
            strength = Integer.parseInt(list.get(2));
        }
    }
}
