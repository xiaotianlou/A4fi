package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class Polygon {
    private List<Point> vertices;
    private List<Segment> segments;
    private List<Integer> neighbors;
    private int id;
    private Point centroid;
    private String thickness="1";

    public Polygon(List<Point> vertices) {
        this.vertices = new ArrayList<>(vertices);
        this.segments = new ArrayList<>();
        this.centroid = new Point(0,0);
        this.neighbors = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            Point start = vertices.get(i);
            Point end = vertices.get((i + 1) % vertices.size());
            Segment segment = new Segment(start, end);
            segments.add(segment);
        }
    }
    public int getId() {
        return id;
    }

    public String getThickness() {
        return thickness;
    }

    public void setThickness(String thickness) {
        this.thickness = thickness;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addNeighbor(int id) {
        if (!neighbors.contains(id)) {
            neighbors.add(id);
        }
    }

    public List<Point> getVertices() {
        return vertices;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public List<Integer> getNeighbors() {
        return neighbors;
    }

    public Point getCentroid() {
        double sumX = 0;
        double sumY = 0;

        for (int i = 0; i < vertices.size(); i++) {
            Point vertex = vertices.get(i);
            sumX += vertex.getX();
            sumY += vertex.getY();
        }

        centroid.setX(sumX / vertices.size());
        centroid.setY(sumY / vertices.size());
        Color centroidc = new Color(255 + "," + 0 + "," + 0 + "," + 0);
        centroid.setColor(centroidc);
        return centroid;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Polygon) {
            Polygon other = (Polygon) obj;
            return new HashSet<>(segments).equals(new HashSet<>(other.segments));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashSet<>(segments).hashCode();
    }

}
