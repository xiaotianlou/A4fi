package ca.mcmaster.cas.se2aa4.a2.generator;

import java.awt.*;

public class SegmentADT {
    private VertexADT start;
    private VertexADT end;
    private Color color;
//    private Thickness thickness;
    final int id;

    public SegmentADT(VertexADT start, VertexADT end, Color color, int id) {
        this.start = start;
        this.end = end;
        this.color = color;
        this.id = id;
    }
}

