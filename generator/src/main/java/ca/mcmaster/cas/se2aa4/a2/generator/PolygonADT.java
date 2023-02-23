package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.List;

public class PolygonADT {
    private final MeshADT mesh;
    private List<SegmentADT> segments;
    private List<VertexADT> vertices;
    final int id;

    public PolygonADT(MeshADT mesh, List<SegmentADT> segments, List<VertexADT> vertices, int id) {
        this.mesh = mesh;
        this.segments = segments;
        this.vertices = vertices;
        this.id = id;
    }
}
