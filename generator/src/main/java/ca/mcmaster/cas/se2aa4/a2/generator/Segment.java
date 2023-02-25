package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.ArrayList;
import java.util.Objects;

public class Segment {
    private Point start;
    private Point end;

    final ArrayList<Integer> usedBy = new ArrayList<>();
    private Color color;
    private int id;
    private String thickness="1";

    public Segment(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public void setEnd(Point end) {
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

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
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