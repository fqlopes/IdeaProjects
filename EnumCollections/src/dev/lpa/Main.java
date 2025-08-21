package dev.lpa;

import java.util.*;

public class Main {

    enum WeekDay { SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY}

    public static void main(String[] args) {

        List<WeekDay> annsWorkDays = new ArrayList<>(List.of(WeekDay.MONDAY, WeekDay.TUESDAY, WeekDay.THURSDAY, WeekDay.FRIDAY));

        var annsDaySet = EnumSet.copyOf(annsWorkDays);

        System.out.println(annsDaySet.getClass().getSimpleName());
        annsDaySet.forEach(System.out::println);

        var allDaysSet = EnumSet.allOf(WeekDay.class);
        System.out.println("-".repeat(30));
        allDaysSet.forEach(System.out::println);

        Set<WeekDay> newPersonDays = EnumSet.complementOf(annsDaySet); // a set plus it's complement equals the full set
        System.out.println("-".repeat(30));
        newPersonDays.forEach(System.out::println);

        //The same result can be reached by taking the full set and removing ann's days set
        Set<WeekDay> anotherWay = EnumSet.copyOf(allDaysSet);
        anotherWay.removeAll(annsDaySet);
        System.out.println("-".repeat(30));
        anotherWay.forEach(System.out::println);

        //takes from element a to element b, both inclusive
        Set<WeekDay> businessDays = EnumSet.range(WeekDay.MONDAY, WeekDay.FRIDAY);
        System.out.println("-".repeat(30));
        businessDays.forEach(System.out::println);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        EnumMaps

//        Enum maps doesn't have a no-args constructor, so the custom enum class type should be passed as an argument

        Map<WeekDay, String[]> employeeMap = new EnumMap<>(WeekDay.class);
//        Elements can be added with put, putAll, putIfAbsent, computeIfPresent, replaceAll
        employeeMap.put(WeekDay.FRIDAY, new String[]{ "Ann", "Mary", "Bob"});
        employeeMap.put(WeekDay.MONDAY, new String[]{"Mary", "Bob"});
        System.out.println("-".repeat(30));
        employeeMap.forEach((k,v) ->{
            System.out.println(k + " : " + Arrays.toString(v));
        });



    }
}
