package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.*;
import ca.mcmaster.cas.se2aa4.a2.generator.Mesh2;


public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int square_size = 20;

    public Mesh2 generate() {
        Mesh2 mesh = new Mesh2();
        // Create all the vertices
        for(int x = 0; x < width; x += square_size) {
            for(int y = 0; y < height; y += square_size) {
                Point start = new Point(x,y);
                Point end1 = new Point(x,y+square_size);
                Point end2 = new Point(x+square_size,y);
                mesh.addVertex(start);
                if (y+square_size <= 500){
                    mesh.addSegment(start,end1);
                }
                if (x+square_size <= 500){
                    mesh.addSegment(start,end2);
                }
            }
        }
        return mesh;
    }

//        // Distribute colors randomly. Vertices are immutable, need to enrich them
//        Set<Vertex> verticesWithColors = new HashSet<>();
//        Random bag = new Random();
//        for(Vertex v: vertices){
//            int red = bag.nextInt(255);
//            int green = bag.nextInt(255);
//            int blue = bag.nextInt(255);
//            String colorCode = red + "," + green + "," + blue;
//            Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
//            Vertex colored = Vertex.newBuilder(v).addProperties(color).build();
//            verticesWithColors.add(colored);
//        }
//        for(int x = 0; x < width; x += square_size) {
//            for(int y = 0; y < height; y += square_size) {
//                segments.add(Segment.newBuilder().setV1Idx(findVertex(v_list,x,y)).setV2Idx(findVertex(v_list,x+square_size,y)).build());
//                segments.add(Segment.newBuilder().setV1Idx(findVertex(v_list,x,y)).setV2Idx(findVertex(v_list,x,y+square_size)).build());
//                segments.add(Segment.newBuilder().setV1Idx(findVertex(v_list,x+square_size,y)).setV2Idx(findVertex(v_list,x+square_size,y+square_size)).build());
//                segments.add(Segment.newBuilder().setV1Idx(findVertex(v_list,x,y+square_size)).setV2Idx(findVertex(v_list,x+square_size,y+square_size)).build());
//            }
//        }
//
//
//        return Mesh.newBuilder().addAllVertices(v_list).addAllSegments(segments).build();
//    }
//    private int findVertex(List<Vertex> vertexList,double x,double y){
//        int i=0;
//        for (Vertex v:vertexList){
//            if(v.getX()==x&&v.getY()==y){
//                return i;
//            }
//            i++;
//        }
//        return -1;
//    }



}
