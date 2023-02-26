package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.Objects;

public class Vertex_ADT {
    private double x;
    private double y;
    private Color color= new Color();
    private int id;
    private String thickness="1";


    public Vertex_ADT(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
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

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex_ADT vertexADT = (Vertex_ADT) o;
        return Double.compare(vertexADT.x, x) == 0 &&
                Double.compare(vertexADT.y, y) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
