package dev.lpa;

enum UsageType{ ENTERTAINMENT, GOVERNMENT, RESIDENTIAL, SPORTS}

public class Building implements Mappable {

    private String name;
    private UsageType type;

    public Building (String name, UsageType type){
        this.name = name;
        this.type = type;
    }

    public UsageType getType(){
        return type;
    }

    @Override
    public String getLabel() {
        return name;
    }

    @Override
    public Geometry getShape() {
        return Geometry.LINE;
    }

    @Override
    public String getMarker() {
        return switch (type){
            case SPORTS ->Color.GREEN + " " + PointMarker.CIRCLE;
            case GOVERNMENT -> Color.BLUE + " " + PointMarker.SQUARE;
            case RESIDENTIAL -> Color.ORANGE + " " + PointMarker.STAR;
            case ENTERTAINMENT -> Color.RED + " " + PointMarker.PUSH_PIN;
            default -> Color.BLACK + " " + PointMarker.STAR;
        };
    }

    @Override
    public String toJSON() {
        return Mappable.super.toJSON() + """
                "{name": %s, usage %s marker %s}""".formatted(getLabel(),getType(),getMarker());
    }


}
