package geometryP;
/*
 * ass 5
 * Tal Tikhonov
 * 215275512
 */
import java.util.ArrayList;
import java.util.List;
/**
 * Represents a rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    /**
     * Constructor.
     * @param upperLeft the upper left point
     * @param width the width
     * @param height the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }
    /**
     * Get the upper left point.
     * @return the upper left point
     */
    public Point getUpperLeft() {
        return upperLeft;
    }
    /**
     * Get the width.
     * @return the width
     */
    public double getWidth() {
        return width;
    }
    /**
     * Get the height.
     * @return the height
     */
    public double getHeight() {
        return height;
    }
    /**
     * Get the top line of the rectangle.
     * @return the top line
     */
    public Line getTopLine() {
        return new Line(upperLeft, new Point(upperLeft.getX() + width, upperLeft.getY()));
    }
    /**
     * Get the bottom line of the rectangle.
     * @return the bottom line
     */
    public Line getBottomLine() {
        return new Line(new Point(upperLeft.getX(), upperLeft.getY() + height),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
    }
    /**
     * Get the left line of the rectangle.
     * @return the left line
     */
    public Line getLeftLine() {
        return new Line(upperLeft, new Point(upperLeft.getX(), upperLeft.getY() + height));
    }
    /**
     * Get the right line of the rectangle.
     * @return the right line
     */
    public Line getRightLine() {
        return new Line(new Point(upperLeft.getX() + width, upperLeft.getY()),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
    }
    /**
     * Get the intersection points with a given line.
     * @param line the line
     * @return the list of intersection points
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();
        Line[] sides = {getTopLine(), getBottomLine(), getLeftLine(), getRightLine()};
        for (Line side : sides) {
            Point intersection = lineIntersection(line, side);
            if (intersection != null) {
                intersectionPoints.add(intersection);
            }
        }
        return intersectionPoints;
    }
    /**
     * Check for intersection between two lines and return the intersection point.
     * @param line1 the first line
     * @param line2 the second line
     * @return the intersection point, or null if no intersection
     */
    private Point lineIntersection(Line line1, Line line2) {
        double x1 = line1.start().getX();
        double y1 = line1.start().getY();
        double x2 = line1.end().getX();
        double y2 = line1.end().getY();
        //
        double x3 = line2.start().getX();
        double y3 = line2.start().getY();
        double x4 = line2.end().getX();
        double y4 = line2.end().getY();
        //
        double denominator = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
        if (denominator == 0) {
            return null;
        }
        //
        double ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / denominator;
        if (ua < 0 || ua > 1) {
            return null;
        }
        //
        double ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / denominator;
        if (ub < 0 || ub > 1) {
            return null;
        }
        //
        double intersectionX = x1 + ua * (x2 - x1);
        double intersectionY = y1 + ua * (y2 - y1);
        return new Point(intersectionX, intersectionY);
    }
}

