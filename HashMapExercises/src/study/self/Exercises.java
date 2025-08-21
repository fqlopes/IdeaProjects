package study.self;

import java.util.*;

public class Exercises {

//    Write a Java program to associate the specified value with the specified key in a HashMap.

    public static HashMap<Integer, String> hashMapMaker (HashMap<Integer, String> aMap, Integer keys, String values){

        aMap.put(keys, values);
        return aMap;
    }

//    Write a Java program to insert a key-value pair into
//    a HashMap and then update the value if the key already exists.

    public static void addAndShow (HashMap<Integer, String> aMap, Integer key, String value){
        if (key == null || value == null){
            System.out.println("Null values not allowed");
        }
        if (aMap.containsKey(key)){
            System.out.println("This key is already in the Map and it holds" + aMap.get(key));
            if (!value.equals(aMap.get(key))){
                System.out.println("Updating the contents of " + key);
                aMap.put(key, value);
            }
        } else {
            aMap.put(key, value);
            System.out.println("Key and value added");
        }
    }

//    Write a Java program to merge two HashMaps such that if keys collide,
//    the values are concatenated into a single string.

    public static boolean mergeAndConcat (HashMap<Integer, String> aMap, HashMap<Integer, String> bMap){

        if(aMap == null || bMap == null){
            System.out.println("Null maps found");
            return false;
        } else {
            aMap.forEach((k,v) -> {
                if (bMap.containsKey(k)){
                    if (!(v.equals(bMap.get(k)))){
                        String concat = v.concat(bMap.get(k));
                        bMap.put(k, concat);
                    }
                } else {
                    bMap.put(k,v);
                }
            });
            return true;
        }

    }

    public static boolean mergeAndConcat2 (HashMap<Integer, String> aMap, HashMap<Integer, String> bMap){

        if(aMap == null || bMap == null) {
            System.out.println("Null maps found");
            return false;
        }
        aMap.forEach((key, value) -> {
            bMap.merge(key, value, (oldValue, newValue) ->
                oldValue.equals(newValue) ? oldValue : oldValue.concat(newValue));
        });
        return true;
    }

//    Write a Java program to create a HashMap, add key-value pairs,
//    and then remove a key only if it is mapped to a specific value.

    public static Map<Integer, String> mapFromList (List<Integer> keys, List<String> values){

        Map<Integer , String> myMap = new HashMap<>();
//        adding the same amount of keys and values, we pick keys because they're more important
        int keySize = keys.size() >= values.size() ? values.size() : keys.size();
        int aux = 0;
        for (Integer key : keys){
            myMap.put(key, values.get(aux++));
        }
        return  myMap;
    }

    public static void removeIfInserted (Map<Integer, String> aMap, Integer key, String value){
        if (aMap == null || key == null || value == null){
            System.out.println("Null element"); //i don't know how to throw D:
        }else {

            if (aMap.containsKey(key) && (aMap.get(key).equals(value))) {
                aMap.remove(key);
            }
        }
    }

//    Write a Java program to implement a method that adds a key-value pair
//    to a HashMap and returns the previous value associated with the key.

    public static String addAndGetPrevious (HashMap<Integer, String> aMap, Integer key, String value){
        if (aMap == null || key == null || value == null) {
            throw new IllegalArgumentException("Nah bruh,f* off");
        }

        if (aMap.containsKey(key) && !(aMap.get(key).equals(value))){
            System.out.println("Previous value is " + aMap.get(key));
            String previous = aMap.get(key);
            aMap.put(key, value);
            return previous;
        } else {
            System.out.println("The key was not found in the map, adding it and returning the value you passed");
            aMap.put(key,value);
            return value;
        }
    }

//    Write a Java program to count the number of key-value (size) mappings in a map.

    public static int keyCount (Map<Integer, String> aMap){
        return aMap == null ? 0 : aMap.size();
    }

//    Write a Java program to calculate the size of a HashMap after bulk insertion and deletion operations.

    public static int keyCountAfterAdd (Map<Integer,String> aMap, List<Integer> keys, List<String> values){

        if (aMap == null || keys == null || values == null) {
            throw new IllegalArgumentException("Nah bruh,f* off");
        }

        int bulkSize = Math.min(keys.size(), values.size());
        System.out.println("Current map size " + aMap.size());

        for(int i = 0; i < bulkSize; i++){
            aMap.put(keys.get(i), values.get(i));
        }

        System.out.println("The new size is " + aMap.size());

        return aMap.size();
    }

//    Write a Java program to compare the size of two maps and print the one with the greater number of mappings.

    public static int mapsSizeCompare (Map<Integer, String> aMap, Map<Integer, String> bMap){
        if (aMap == null || bMap == null){
            throw new IllegalArgumentException("Null elements");
        }
        return aMap.size() >= bMap.size() ? aMap.size() : bMap.size();
    }

//    Write a Java program to create a method that returns the size of a map
//    and then verifies it by iterating over the map entries.

    public static void mapSizeAndElementConfirmation (Map<Integer, String> aMap){
        if (aMap == null){
            throw new IllegalArgumentException("Null elements");
        }

        System.out.println("The map size is " + aMap.size());
        System.out.println("It's elements are: ");
        aMap.forEach((k,v)-> System.out.println("Key: " + k + "Value: " + v));
    }

//    Write a Java program to copy all mappings from the specified map to another map.

    public static Map<Integer, String> cloneMap (Map<Integer, String> aMap){
        if (aMap == null){
            throw new IllegalArgumentException("Null elements");
        }

        return new HashMap<>(aMap);
    }

//    Write a Java program to copy all entries from one HashMap
//    to another using putAll() and then verify both maps are equal.

    public static Map<Integer, String> cloneAndVerify (Map<Integer, String> aMap){
        if (aMap == null){
            throw new IllegalArgumentException("Null elements");
        }

        Map<Integer, String> myMap = new HashMap<>();
        myMap.putAll(aMap);
        System.out.println("myMap created, and cloned with putAll method. Are they equal? = " + myMap.equals(aMap));
        return myMap;
    }

//    Write a Java program to implement a custom deep copy method for a map containing mutable objects.

    public static <K , V> Map<K, V> deepCopy (Map<K, V> aMap){
        if (aMap == null){
            throw new IllegalArgumentException("Null elements");
        }

        Map<K, V> myMap = new HashMap<>();
        aMap.forEach((k,v) -> myMap.put(k,v));
        return myMap;
    }

//    Write a Java program to remove all mappings from a map.

    public static <K, V> Map<K,V> manualRemove (Map<K,V> aMap){
        if (aMap == null){
            throw new IllegalArgumentException("Null elements");
        }
        Set<K> keys = new HashSet<>(aMap.keySet()); // concurrent change of an object during iteration is not allowed
        keys.forEach(aMap::remove);

        return aMap;
    }

//    Write a Java program to remove all entries from a map that have a value matching a specified criterion.

    public static <K, V> Map<K, V> purgeElement(Map<K, V> aMap, V element) {
        if (aMap == null || element == null) {
            throw new IllegalArgumentException("Null elements");
        }

        Iterator < Map.Entry <K, V>> mapEntryIterator = aMap.entrySet().iterator(); //using "the view"

        while (mapEntryIterator.hasNext()){
            Map.Entry<K, V> entry = mapEntryIterator.next();
            if (entry.getValue().equals(element)){
                mapEntryIterator.remove();
            }
        }
        return aMap;
    }

//  Write a Java program to iterate over a map and remove entries one-by-one, printing each removed key.

    public static <K, V> boolean purgeAllElementsManual (Map<K, V> aMap){
        if (aMap == null) {
            throw new IllegalArgumentException("Null elements");
        }


        Set<K> keySet = new HashSet<>(aMap.keySet());
        keySet.forEach(k -> {
            System.out.println("Key:" + k);
            aMap.remove(k);
        });
        return true;
    }

//    Write a Java program to reinitialize a map by assigning a new instance and compare it with the original map.


    public static <K, V> Map <K, V> fancyNewMap (Map<K, V> aMap){
        if (aMap == null) {
            throw new IllegalArgumentException("Null elements");
        }
        var iterator = aMap.entrySet().iterator();
        Map<K,V> myMap = new HashMap<>();

        while (iterator.hasNext()){
            var current = iterator.next();
            myMap.put(current.getKey(), current.getValue());
        }
        System.out.println("Are they equal? boolean = " + aMap.equals(myMap));
        return myMap;
    }

//    Write a Java program to check whether a map contains key-value mappings (empty) or not.

    public static <K,V> boolean checkEmptyKV (Map <K, V> aMap){
        System.out.println("This method checks if there's any missing key/value");

        if (aMap == null) {
            System.out.println("The Map is null");
            return true;
        }

        if (aMap.isEmpty()){
            System.out.println("The map is empty");
            return true;
        }

        System.out.println("The map isn't null, checking for empty values");
        Iterator<Map.Entry<K, V>> iterator = aMap.entrySet().iterator();

        while (iterator.hasNext()){
            Map.Entry<K,V> current = iterator.next();
            if (current.getValue().equals("")){
                System.out.println("The key " + current.getKey() + " has an empty value assigned");
                return true;
            }
        }
        return false;
    }

//    Write a Java program to check if a HashMap is empty using the isEmpty() method and output an appropriate message.

    public static <K, V> boolean isMapEmpty (Map<K, V> aMap){
        if (aMap == null) {
            throw new IllegalArgumentException("Entry cannot be null");
        }
        return aMap.isEmpty();
    }

//    Write a Java program to use a conditional operator to print "Empty" or "Not Empty" based on the map’s size.

    public static <K, V> boolean isEmptyAndSize (Map<K, V> aMap){
        if (aMap == null) {
            throw new IllegalArgumentException("Entry cannot be null");
        }

        System.out.println("Checking map's size");
       boolean isEmpty = aMap.size() == 0 ? true : false;
        System.out.println("I map empty?" + isEmpty);
        return isEmpty;
    }

//    Write a Java program to get a shallow copy of a HashMap instance.


    public static <K, V> Map<K, V> mapCopy (Map<K, V> aMap){
        if (aMap == null) {
            throw new IllegalArgumentException("Entry cannot be null");
        }
        return new HashMap<>(aMap);
    }

//    Write a Java program to clone a HashMap using the clone() method
//    and then modify the clone without affecting the original.

    public static <K, V> Map<K, V> clone (Map<K, V> aMap) {
        if (aMap == null) {
            throw new IllegalArgumentException("Entry cannot be null");
        }
        var iterator = aMap.entrySet().iterator();
        Map<K,V> myMap = new HashMap<>();

        while (iterator.hasNext()){
            var current = iterator.next();
            myMap.put(current.getKey(), current.getValue());
        }
        return myMap;
    }

//    Write a Java program to test if a map contains a mapping for the specified key.

    public static <K, V> boolean containsKey (Map<K, V> aMap, K aKey){
        //returns true or false if aMap contains aKey;
        if (aMap == null || aKey == null) {
            throw new IllegalArgumentException("Entries cannot be null");
        }

        return aMap.containsKey(aKey);
    }

//    Write a Java program to check if a HashMap contains a specific key using containsKey() and print a custom message.

    public static <K, V> boolean containsKey2 (Map<K, V> map, K key){
        if (map == null || key == null) {
            throw new IllegalArgumentException("Entries cannot be null");
        }
        System.out.println("Checking for key");
        return map.containsKey(key);
    }

//    Write a Java program to implement a method that checks if
//    a key is present in a map and returns its associated value if found.

    public static <K, V> V getValue (Map<K, V> map, K key){
        if (map == null || key == null) {
            throw new IllegalArgumentException("Entries cannot be null");
        }
        return map.containsKey(key) ? map.get(key) : null;
    }

//    Write a Java program to create a set view of the mappings contained in a map.
public static <K, V> Set<K> theKeySet (Map<K, V> aMap){
    if (aMap == null) {
        throw new IllegalArgumentException("Entry cannot be null");
    }
    return aMap.keySet();
}

    public static <K, V> Set<Map.Entry<K, V>> theEntrySet (Map<K, V> aMap){
        if (aMap == null) {
            throw new IllegalArgumentException("Entry cannot be null");
        }
        return aMap.entrySet();
    }

    public static <K,V> Collection<V> theValuesSet (Map<K, V> aMap){
        if (aMap == null) {
            throw new IllegalArgumentException("Entry cannot be null");
        }
        return aMap.values();
    }

//    Write a Java program to create a set view of the entries in a HashMap using entrySet() and then print each mapping.

    public static <K, V> void mapEntryPrint (Map<K, V> map){
        if (map == null) {
            throw new IllegalArgumentException("Entry cannot be null");
        }
        Set<Map.Entry<K, V>> entrySet = map.entrySet();
        entrySet.forEach(System.out::println);

    }

//    Write a Java program to convert a map’s entrySet() into a List and then sort the list by keys.

    public static <K extends Comparable<K>, V> List<Map.Entry<K, V>> mapToList (Map<K, V> map){
        if (map == null) {
            throw new IllegalArgumentException("Entry cannot be null");
        }

        List<Map.Entry<K, V>> myEntry = new ArrayList<>(map.entrySet());

        myEntry.sort(Map.Entry.comparingByKey());

        return myEntry;

    }

//    Write a Java program to get the value of a specified key in a map.

    public static <K, V> V returnValue (Map <K, V> map, K key){
        if (map == null) {
            throw new IllegalArgumentException("Entry cannot be null");
        }
        return map.get(key);
    }

//    Write a Java program to retrieve a value from a map using get() and handle the case when the key is absent.

    public static <K, V> V returnValueIfExists (Map<K,V> map, K key){
        if (map == null) {
            throw new IllegalArgumentException("Entry cannot be null");
        }
        if (map.containsKey(key)) {
            System.out.println("Key found");
            return map.get(key);
        } else{
            System.out.println("Key not found");
            return null;
        }
    }
}
