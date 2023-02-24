package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.*;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.*;

import static ca.mcmaster.cas.se2aa4.a2.generator.components.polygon.PolygonInitializing.polygonInitialize;
import static ca.mcmaster.cas.se2aa4.a2.generator.components.vertex.VertexInitializing.vertexInitialize;
import static ca.mcmaster.cas.se2aa4.a2.generator.components.segment.SegmentInitializing.segmentInitialize;


public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int square_size = 20;

    public Mesh generate() {
        MeshADT mesh = new MeshADT();
        for(int x = 0; x < width; x += square_size) {
            for(int y = 0; y < height; y += square_size) {
                List<VertexADT> verticesPolygon = new LinkedList<>();
                List<SegmentADT> segmentsPolygon = new LinkedList<>();
                VertexADT a =  mesh.getVertex(x,y);
                VertexADT b =  mesh.getVertex(x+square_size,y);
                VertexADT c =  mesh.getVertex(x,y+square_size);
                VertexADT d =  mesh.getVertex(x+square_size,y+square_size);
                verticesPolygon.add(a);
                verticesPolygon.add(b);
                verticesPolygon.add(c);
                verticesPolygon.add(d);
                SegmentADT ab = mesh.getSegment(a,b);
                SegmentADT bd = mesh.getSegment(b,d);
                SegmentADT dc = mesh.getSegment(d,c);
                SegmentADT ca = mesh.getSegment(c,a);
                segmentsPolygon.add(ab);
                segmentsPolygon.add(bd);
                segmentsPolygon.add(dc);
                segmentsPolygon.add(ca);
                mesh.getPolygon(mesh,verticesPolygon,segmentsPolygon);
//                        (Vertex.newBuilder().setX((double) x).setY((double) y).build());
//                vertices.add(Vertex.newBuilder().setX((double) x+square_size).setY((double) y).build());
//                vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y+square_size).build());
//                vertices.add(Vertex.newBuilder().setX((double) x+square_size).setY((double) y+square_size).build());
            }
        }

//        List<Vertex> verticesWithColors = vertexInitialize(width,height,square_size);
//
//        List<Segment> segmentsWithColors = segmentInitialize(width,height,square_size,verticesWithColors);
//        List<Polygon> polygons = polygonInitialize(verticesWithColors,segmentsWithColors);

//        return Mesh.newBuilder().addAllVertices(verticesWithColors).build();


        return mesh.toMesh();
    }
}