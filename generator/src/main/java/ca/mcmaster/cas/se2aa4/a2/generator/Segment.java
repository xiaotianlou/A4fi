package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.ArrayList;
import java.util.Objects;

public class Segment {
    private Vertex_ADT start;
    private Vertex_ADT end;

    final ArrayList<Integer> usedBy = new ArrayList<>();
    private Color color =new Color();
    private int id;
    private String thickness="1";

    public Segment(Vertex_ADT start, Vertex_ADT end) {
        this.start = start;
        this.end = end;
    }

    public void setStart(Vertex_ADT start) {
        this.start = start;
    }

    public void setEnd(Vertex_ADT end) {
        this.end = end;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getThickness() {
        return thickness;
    }

    public void setThickness(String thickness) {
        this.thickness = thickness;
    }

    public Vertex_ADT getStart() {
        return start;
    }

    public Vertex_ADT getEnd() {
        return end;
    }
    public int getId() {
        return id;
    }
    public Color getColor() {
        return color;
    }

    public ArrayList<Integer> getUsedBy() {
        return usedBy;
    }

    public void addUsedBy(int polygonId) {
        for (int id : this.usedBy){
            if(polygonId == id){
                return;
            }
        }
        this.usedBy.add(polygonId);
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