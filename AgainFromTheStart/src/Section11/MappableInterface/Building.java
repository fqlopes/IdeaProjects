package Section11.MappableInterface;


enum usageType {BUSINESS, ENTERTAINMENT, HOME}


public class Building implements  Mappable{

    private String name;
    private usageType type;

    public Building (String name, usageType type){
        this.name = name;
        this.type = type;
    }


    @Override
    public String getLabel() {
        return name + "(" + type + ")";
    }

    @Override
    public String toString (){
        return this.name + " " + getIcon();
    }



    @Override
    public geometryType getGeometry() {
        return geometryType.POINT;
    }

    @Override
    public String getIcon() {
        return switch (this.type){
            case BUSINESS -> iconType.MARKED.toString();
            case HOME -> iconType.UNMARKED.toString();
            case ENTERTAINMENT -> iconType.IMPORTANT.toString();

        };
    }
}
