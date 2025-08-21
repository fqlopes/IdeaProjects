package dev.lpa;

public class Node extends ListItem{

    public Node (Object value){
        super (value);
    }

    @Override
    ListItem next(){
        return rightLink;
    }

    @Override
    ListItem setNext(ListItem rightLink){
       return this.rightLink = rightLink;
    }

    @Override
    ListItem previous(){
        return leftLink;
    }

    @Override
    ListItem setPrevious(ListItem leftLink){
        return this.leftLink = leftLink;
    }


    @Override
    int compareTo (ListItem item){
        //If this value is greater than the value that was passed in,
        // then it should return a number greater than zero. If vice versa
        // , then it should return a number less than zero, and zero if equal.
        int x = Integer.parseInt(String.valueOf(value));
        int y = Integer.parseInt(String.valueOf(item.value));

        if (x == 0){
            return 0;
        }

        return (x > y ? 1 : -1);
    }




}
