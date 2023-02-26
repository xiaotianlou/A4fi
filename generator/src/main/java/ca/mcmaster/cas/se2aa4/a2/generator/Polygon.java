package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class Polygon {
    private List<Vertex_ADT> vertices;
    private List<Segment> segments;
    private List<Integer> neighbors;
    private int id;
    private Vertex_ADT centroid;
    private String thickness="1";

    public Polygon(List<Vertex_ADT> vertices) {
        this.vertices = new ArrayList<>(vertices);
        this.segments = new ArrayList<>();
        this.centroid = new Vertex_ADT(0,0);
        this.neighbors = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            Vertex_ADT start = vertices.get(i);
            Vertex_ADT end = vertices.get((i + 1) % vertices.size());
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

    public List<Vertex_ADT> getVertices() {
        return vertices;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public List<Integer> getNeighbors() {
        return neighbors;
    }

    public Vertex_ADT getCentroid() {
        double sumX = 0;
        double sumY = 0;

        for (int i = 0; i < vertices.size(); i++) {
            Vertex_ADT vertex = vertices.get(i);
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
