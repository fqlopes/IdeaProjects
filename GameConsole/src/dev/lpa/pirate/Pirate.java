package dev.lpa.pirate;

import dev.lpa.game.Player;

import java.util.*;

public final class Pirate extends Combatant {


    private final List<String> townsVisited = new LinkedList<>();
    private List<Loot> loot;
    private List<Combatant> opponents;
    private List<Feature> features;

    public Pirate (String name){
        super(name);
    }

    public Pirate(String name, List<Loot> loot) {
        super(name, Map.of("level", 0, "townIndex", 0));
        visitTown();


    }

    boolean useWeapon(){
        System.out.println("Used the " + super.getCurrentWeapon());
        return visitNextTown();
    }

    boolean visitTown(){

        List<String> levelTowns = PirateGame.getTowns(value("level"));

        if (levelTowns ==null) return true;
        String town = levelTowns.get(value("townIndex"));

        if (town != null){
            townsVisited.add(town);
            return false;
        }
        return true;
    }

    private boolean visitNextTown (){

        int townIndex =value("townIndex");
        var towns = PirateGame.getTowns(value("level"));
        if (towns == null){
            return true;
        }

        if (townIndex >= (towns.size() -1)){
            System.out.println("leveled up! fuck you");
            adjustValue("score", 500);
            adjustValue("level", 1);
            setValue("townIndex", 0);
        } else {
            System.out.println("Pirate sailing to another town");
            adjustValue("townIndex", 1);
            adjustValue( "score",50);

        }
        return visitTown();
    }


    public String information() {
        var current = ((LinkedList<String>) townsVisited).getLast();
        String[] simpleNames = new String[townsVisited.size()];
        Arrays.setAll(simpleNames, i -> townsVisited.get(i).split(",")[0]);
        return" --->" + current + "\n" + super.information() +
                "\n\tTownsVisited = " + Arrays.toString(simpleNames);
    }
}
