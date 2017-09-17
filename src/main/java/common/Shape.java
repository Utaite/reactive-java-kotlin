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

    private static String getSuffix(String shape) {
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

    public static String getString(String color, String shape) {
        return color + getSuffix(shape);
    }

}
