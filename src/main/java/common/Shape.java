package common;

public class Shape {

    public static final String HEXAGON = "HEXAGON";
    public static final String OCTAGON = "OCTAGON";
    public static final String RECTANGLE = "RECTANGLE";
    public static final String TRIANGLE = "TRIANGLE";
    public static final String DIAMOND = "DIAMOND";
    public static final String PENTAGON = "PENTAGON";
    public static final String STAR = "STAR";
    public static final String BALL = "BALL";
    public static final String NO_SHAPE = "NO_SHAPE";


    public static String getColor(String shape) {
        if(shape.endsWith("<>")) {
            return shape.replace("<>", "").trim();
        } else {
            int hyphen = shape.indexOf("-");
            if(hyphen > 0) {
                return shape.substring(0, hyphen);
            } else {
                return shape;
            }
        }
    }

    public static String getSuffix(String shape) {
        if (HEXAGON.equals(shape)) {
            return "-H";
        } else if (OCTAGON.equals(shape)) {
            return "-O";
        } else if (RECTANGLE.equals(shape)) {
            return "-R";
        } else if (TRIANGLE.equals(shape)) {
            return "-T";
        } else if (DIAMOND.equals(shape)) {
            return "<>";
        } else if (PENTAGON.equals(shape)) {
            return "-P";
        } else if (STAR.equals(shape)) {
            return "-S";
        } else {
            return "";
        }
    }

    public static String getShape(String obj) {
        if (obj.isEmpty()) {
            return NO_SHAPE;
        } else if (obj.endsWith("-H")) {
            return HEXAGON;
        } else if (obj.endsWith("-O")) {
            return OCTAGON;
        } else if (obj.endsWith("-R")) {
            return RECTANGLE;
        } else if (obj.endsWith("-T")) {
            return TRIANGLE;
        } else if (obj.endsWith("<>")) {
            return DIAMOND;
        } else if (obj.endsWith("-P")) {
            return PENTAGON;
        } else if (obj.endsWith("-S")) {
            return STAR;
        } else {
            return "BALL";
        }
    }

    public static String getString(String color, String shape) {
        return color + getSuffix(shape);
    }

}
