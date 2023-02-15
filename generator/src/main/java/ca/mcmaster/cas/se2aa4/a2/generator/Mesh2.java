package ca.mcmaster.cas.se2aa4.a2.generator;

import java.math.BigDecimal;
import java.util.*;

public class Mesh2 {
    private List<Point> vertices;
    private List<Segment> segments;
    private List<Polygon> polygons;
    private int scale = 2;

    public Mesh2() {
        vertices = new ArrayList<>();
        segments = new ArrayList<>();
        polygons = new ArrayList<>();
    }

    public void addVertex(Point point) {
        double x = point.getX();
        double y = point.getY();
        BigDecimal bdX = new BigDecimal(x);
        BigDecimal bdY = new BigDecimal(y);
        x = bdX.setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
        y = bdY.setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
        point = new Point(x, y);
        vertices.add(point);
    }

    public void addSegment(Point start, Point end) {
        Segment segment = new Segment(start, end);
        segments.add(segment);
    }

    public void addPolygon(List<Point> vertices) {
        Polygon polygon = new Polygon(vertices);
        polygons.add(polygon);
    }

    public List<Point> getVertices() {
        return vertices;
    }

    public List<Segment> getSegments() {
        return segments;
    }
    public List<Polygon> getPolygons() {
        return polygons;
    }
    public void setScale(int scale) {
        this.scale = scale;
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

    public void removeDuplicates() {
        Set<Point> pointSet = new HashSet<>(vertices);
        Set<Segment> segmentSet = new HashSet<>(segments);
        Set<Polygon> polygonSet = new HashSet<>(polygons);
        vertices.clear();
        segments.clear();
        polygons.clear();
        vertices.addAll(pointSet);
        segments.addAll(segmentSet);
        polygons.addAll(polygonSet);
    }
}

