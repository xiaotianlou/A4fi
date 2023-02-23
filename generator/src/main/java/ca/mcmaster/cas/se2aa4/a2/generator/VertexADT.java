package ca.mcmaster.cas.se2aa4.a2.generator;

import java.awt.*;
import java.util.ArrayList;

public class VertexADT {
    public double x;
    public double y;
    public Color color;
    final ArrayList<SegmentADT> segments = new ArrayList<>();
    final int id;

    public VertexADT(double x, double y, Color color, int id) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.id = id;
    }
}
