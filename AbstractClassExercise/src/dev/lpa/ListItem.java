package dev.lpa;

public abstract class ListItem {

    protected ListItem rightLink;
    protected ListItem leftLink;
    protected Object value;

    public ListItem (Object value){
        this.value = value;
    }

    //package-private methods == no explicit access modifier (public, protected, or private) is specified

    abstract ListItem next();
    abstract ListItem setNext(ListItem rightLink);
    abstract ListItem previous();
    abstract ListItem setPrevious(ListItem leftLink);
    abstract int compareTo(ListItem item);

   public Object getValue (){
        return value;
   }

   public void setValue (Object value){
        this.value = value;
   }
}
