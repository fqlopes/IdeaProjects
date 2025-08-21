package dev.lpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

record Baseballplayer (String name, String position){};


record CounterStrikeTeam<T> (List<T> name){};

record generalTeam(String name, String position, List<String> peripheral) implements aTeam {};

interface aTeam {
    String name();
    String position();
    List<String> peripheral = List.of("Things");
}



public class Main {
    public static void main(String[] args) {

//        BaseballTeam phillies = new BaseballTeam("Philadelphia Phillies");
//        BaseballTeam astros = new BaseballTeam("Houston Astros");
//        scoreResult(phillies, 3, astros, 5);
//
//        var harper = new Baseballplayer("B Harper", "Right Fielder");
//        var marsh = new Baseballplayer("B Marsh", "Right Fielder");
//        phillies.addTeamMember(harper);
//        phillies.addTeamMember(marsh);
//        phillies.listTeamMembers();
//
       var brazil = new CounterStrikeTeam<>(new ArrayList<>(List.of("FURIA","PAIN")));
       brazil.name().add("MIBR");

       var aux = new CounterStrikeTeam<>(new ArrayList<Integer>(Arrays.asList(1,2,3,4)));

       aTeam newteam = new generalTeam("Navi", "Leader", Arrays.asList("Mouse", "Keyboard"));


//
//
//        for (var member : brazil.name()){
//            System.out.println(member);
//        }
//
//        for (var member : aux.name()){
//            System.out.println(member);
//        }
//        System.out.println(brazil.getClass());
    }

    public static void scoreResult(BaseballTeam team1, int team1_score, BaseballTeam team2, int team2_score){

        String message = team1.setScore(team1_score, team2_score);
        team2.setScore(team2_score, team1_score);
        System.out.printf("%s %s %s %n",team1, message, team2);

    }
}
