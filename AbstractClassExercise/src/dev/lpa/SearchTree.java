package dev.lpa;

public class SearchTree implements NodeList{

    private ListItem root;

    public SearchTree (ListItem root){
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return null;

    }

    @Override
    public boolean addItem(ListItem item) {
        return false;
    }

    @Override
    public boolean removeItem(ListItem item) {
        return false;
    }

    @Override
    public void traverse(ListItem root) {

    }
}
