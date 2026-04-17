package geometryP;

import java.util.List;
/**
 * Represents a line segment between two points.
 */
public class Line {
    private Point start;
    private Point end;
    /**
     * Constructor.
     * @param start the start point of the line
     * @param end the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     * Get the start point.
     * @return the start point
     */
    public Point start() {
        return start;
    }
    /**
     * Get the end point.
     * @return the end point
     */
    public Point end() {
        return end;
    }
    /**
     * Check if the given point is on the line segment.
     * @param point the point to check
     * @return true if the point is on the line, false otherwise
     */
    public boolean onLine(Point point) {
        double minX = Math.min(start.getX(), end.getX());
        double maxX = Math.max(start.getX(), end.getX());
        double minY = Math.min(start.getY(), end.getY());
        double maxY = Math.max(start.getY(), end.getY());
        //
        double crossProduct = (point.getY() - start.getY()) * (end.getX() - start.getX())
                - (point.getX() - start.getX()) * (end.getY() - start.getY());

        if (Math.abs(crossProduct) > 0.001) {
            return false;
        }
        //
        if (point.getX() < minX || point.getX() > maxX || point.getY() < minY || point.getY() > maxY) {
            return false;
        }
        //
        return true;
    }
    /**
     * Get the closest intersection point with a rectangle.
     * @param rect the rectangle
     * @return the closest intersection point, or null if no intersection
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        //
        Point closestPoint = intersectionPoints.get(0);
        double closestDistance = start.distance(closestPoint);
        //
        for (Point point : intersectionPoints) {
            double distance = start.distance(point);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestPoint = point;
            }
        }
        //
        return closestPoint;
    }
}

