package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.*;

//import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
//import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
//import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
//import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;


public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int square_size = 20;




    public Structs.Mesh generate() {
        Mesh2 mesh = new Mesh2();
        // Create all the vertices
        for (int x = 0; x < width; x += square_size) {
            for (int y = 0; y < height; y += square_size) {
                Point start = new Point(x, y);
                Point end1 = new Point(x, y + square_size);
                Point end2 = new Point(x + square_size, y);
                mesh.addVertex(start);
                if (y + square_size < 500) {
                    mesh.addSegment(start, end1);
                }
                if (x + square_size < 500) {
                    mesh.addSegment(start, end2);
                }
            }
        }
        Random bag = new Random();
        for (Point p : mesh.getVertices()) {
            int red = bag.nextInt(255);
            int green = bag.nextInt(255);
            int blue = bag.nextInt(255);
            int alpha = bag.nextInt(255);
            String colorCode = red + "," + green + "," + blue + "," + alpha;
            p.setColor(colorCode);
        }

        for (Segment s : mesh.getSegments()) {
            int red = bag.nextInt(255);
            int green = bag.nextInt(255);
            int blue = bag.nextInt(255);
            int alpha = bag.nextInt(255);
            String colorCode = red + "," + green + "," + blue + "," + alpha;
            s.setColor(colorCode);
        }
        return Mesh2.transform(mesh);
    }

    //    Set<Vertex> vertices = new HashSet<>();
//    // Create all the vertices
//        for(int x = 0; x < width; x += square_size) {
//        for(int y = 0 ; y < height; y += square_size) {
//            vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y).build());
//            vertices.add(Vertex.newBuilder().setX((double) x+square_size).setY((double) y).build());
//            vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y+square_size).build());
//            vertices.add(Vertex.newBuilder().setX((double) x+square_size).setY((double) y+square_size).build());
//        }
//    }
//
    // Distribute colors randomly. Vertices are immutable, need to enrich them
//    Set<Vertex> verticesWithColors = new HashSet<>();
//    Random bag = new Random();
//        for(Vertex v: vertices){
//        int red = bag.nextInt(255);
//        int green = bag.nextInt(255);
//        int blue = bag.nextInt(255);
//        String colorCode = red + "," + green + "," + blue;
//        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
//        Vertex colored = Vertex.newBuilder(v).addProperties(color).build();
//        verticesWithColors.add(colored);
//    }
//    List<Vertex> v_list = new LinkedList<>(verticesWithColors);
//    HashSet<Segment> segments = new HashSet<>();
//        for(int x = 0; x < width; x += square_size) {
//            for(int y = 0; y < height; y += square_size) {
//            segments.add(Segment.newBuilder().setV1Idx(findVertex(v_list,x,y)).setV2Idx(findVertex(v_list,x+square_size,y)).build());
//            segments.add(Segment.newBuilder().setV1Idx(findVertex(v_list,x,y)).setV2Idx(findVertex(v_list,x,y+square_size)).build());
//            segments.add(Segment.newBuilder().setV1Idx(findVertex(v_list,x+square_size,y)).setV2Idx(findVertex(v_list,x+square_size,y+square_size)).build());
//            segments.add(Segment.newBuilder().setV1Idx(findVertex(v_list,x,y+square_size)).setV2Idx(findVertex(v_list,x+square_size,y+square_size)).build());
//        }
//    }
//
//
//        return Mesh.newBuilder().addAllVertices(v_list).addAllSegments(segments).build();
//}

}


