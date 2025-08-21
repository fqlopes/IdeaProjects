package dev.lpa;

import dev.lpa.model.LPAStudent;
import dev.lpa.model.Student;
import dev.lpa.util.QueryItem;
import dev.lpa.util.QueryList;

import java.util.ArrayList;
import java.util.List;

record Employee (String name) implements QueryItem{

    @Override
    public boolean matchFieldValue(String fieldName, String value) {
        return false;
    }
}

public class Main {

    public static void main(String[] args) {

        QueryList<LPAStudent> queryList = new QueryList<>();



    }

    public static void printMoreLists (List<? extends Student> students){

        for (var student : students){
            System.out.println(student);
        }
    }

    public static void testList (List<?> list){
        for (var element : list){
            if (element instanceof String s){
                System.out.println("String : " + s.toUpperCase());
            }
            if (element instanceof Integer i){
                System.out.println("Integer : " + i.floatValue());
            }

        }
    }
}
