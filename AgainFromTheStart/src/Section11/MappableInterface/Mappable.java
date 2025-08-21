package Section11.MappableInterface;

public interface Mappable {

    enum labelType {A, B, C }
    enum geometryType {SQUARE, TRIANGLE, CIRCLE, POINT}
    enum iconType {MARKED, UNMARKED, IMPORTANT}

    String getLabel();
    geometryType getGeometry();
    String getIcon();

    String JSON_PROPERTY = "properties{%s}";

    default String toJSON(){

        return getLabel().toString() + " " + getGeometry().toString() + " " + getIcon();
    }

    static void mapIt (Mappable mappable){
        System.out.println(JSON_PROPERTY.formatted(mappable.toJSON()));
    }

}
