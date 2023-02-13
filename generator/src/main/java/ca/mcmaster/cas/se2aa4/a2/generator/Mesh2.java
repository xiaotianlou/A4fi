package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.*;

class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 &&
                Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

class Segment {
    private Point start;
    private Point end;

    public Segment(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segment edge = (Segment) o;
        return Objects.equals(start, edge.start) &&
                Objects.equals(end, edge.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
    public String toString() {
        return "(" + start + " - " + end + ")";
    }
}

public class Mesh2 {
    private List<Point> vertices;
    private List<Segment> segments;

    public Mesh2() {
        vertices = new ArrayList<>();
        segments = new ArrayList<>();
    }

    public void addVertex(Point point) {
        vertices.add(point);
    }

    public void addSegment(Point start, Point end) {
        Segment segment = new Segment(start, end);
        segments.add(segment);
    }

    public List<Point> getVertices() {
        return vertices;
    }

    public List<Segment> getSegments() {
        return segments;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mesh2:\n");
        sb.append("Vertices:\n");
        for (Point vertex : vertices) {
            sb.append(vertex + "\n");
        }
        sb.append("Segments:\n");
        for (Segment segment : segments) {
            sb.append(segment + "\n");
        }
        return sb.toString();
    }
}

