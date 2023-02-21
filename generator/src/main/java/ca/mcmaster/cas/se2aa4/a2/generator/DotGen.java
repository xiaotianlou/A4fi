package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.*;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

import static ca.mcmaster.cas.se2aa4.a2.generator.components.vertex.VertexInitializing.vertexInitialize;
import static ca.mcmaster.cas.se2aa4.a2.generator.components.segment.SegmentInitializing.segmentInitialize;


public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int square_size = 20;

    public Mesh generate() {

        List<Vertex> verticesWithColors = vertexInitialize(width,height,square_size);

        List<Segment> segmentsWithColors = segmentInitialize(width,height,square_size,verticesWithColors);

//        return Mesh.newBuilder().addAllVertices(verticesWithColors).build();

        return Mesh.newBuilder().addAllVertices(verticesWithColors).addAllSegments(segmentsWithColors).build();
    }
}