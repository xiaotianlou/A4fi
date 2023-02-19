package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.*;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int square_size = 20;

    public Mesh generate() {
        List<Vertex> vertices = new LinkedList<>();
        // Create all the vertices
        for(int x = 0; x < width; x += square_size) {
            for(int y = 0; y < height; y += square_size) {
                vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y).build());
                vertices.add(Vertex.newBuilder().setX((double) x+square_size).setY((double) y).build());
                vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y+square_size).build());
                vertices.add(Vertex.newBuilder().setX((double) x+square_size).setY((double) y+square_size).build());
            }
        }

        // Distribute colors randomly. Vertices are immutable, need to enrich them
        List<Vertex> verticesWithColors = new LinkedList<>();
        Random bag = new Random();
        for(Vertex v: vertices){
            int red = bag.nextInt(255);
            int green = bag.nextInt(255);
            int blue = bag.nextInt(255);
            String colorCode = red + "," + green + "," + blue;
            Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
            Vertex colored = Vertex.newBuilder(v).addProperties(color).build();
            verticesWithColors.add(colored);
        }
        System.out.println("==================================================");
        System.out.println(verticesWithColors.get(0).getX()+" "+verticesWithColors.get(0).getY());
        System.out.println(verticesWithColors.get(0).getProperties(0));
        System.out.println(verticesWithColors.get(1).getX()+" "+verticesWithColors.get(1).getY());
        System.out.println(verticesWithColors.get(2).getX()+" "+verticesWithColors.get(2).getY());
        System.out.println("==================================================");
//        List<Vertex> v_list = new LinkedList<>(verticesWithColors);
        LinkedList<Segment> segments = new LinkedList<>();
        for(int x = 0; x < width; x += square_size) {
            for(int y = 0; y < height; y += square_size) {
                segments.add(Segment.newBuilder().setV1Idx(findVertex(verticesWithColors,x,y)).setV2Idx(findVertex(verticesWithColors,x+square_size,y)).build());
                segments.add(Segment.newBuilder().setV1Idx(findVertex(verticesWithColors,x,y)).setV2Idx(findVertex(verticesWithColors,x,y+square_size)).build());
                segments.add(Segment.newBuilder().setV1Idx(findVertex(verticesWithColors,x+square_size,y)).setV2Idx(findVertex(verticesWithColors,x+square_size,y+square_size)).build());
                segments.add(Segment.newBuilder().setV1Idx(findVertex(verticesWithColors,x,y+square_size)).setV2Idx(findVertex(verticesWithColors,x+square_size,y+square_size)).build());
            }
        }

        List<Segment> segmentsWithColors = new LinkedList<>();
        for (Segment s:segments){
            int[]tempA = extractColor_Number(verticesWithColors.
                    get(s.getV1Idx()).getProperties(0).getValue());
            int[]tempB = extractColor_Number(verticesWithColors
                    .get(s.getV2Idx()).getProperties(0).getValue());
            int[]tempC = {(tempA[0]+tempB[0])/2,(tempA[1]+tempB[1])/2,(tempA[2]+tempB[2])/2};
            String colorCode = tempC[0] + "," + tempC[1] + "," + tempC[2];
            Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
            Segment colored = Segment.newBuilder(s).addProperties(color).build();
            segmentsWithColors.add(colored);
        }

        System.out.println("===================================================");
        System.out.println(verticesWithColors.get(segments.get(0).getV1Idx()).getProperties(0).getValue()+"+"+segments.get(0).getV2Idx());
        System.out.println(segments.get(0));

        System.out.println("---------------------------------------------------");
        System.out.println(segments.get(1).getV1Idx()+"+"+segments.get(1).getV2Idx());
        System.out.println(segments.get(1));
        System.out.println("---------------------------------------------------");
        System.out.println(segments.get(2));
        System.out.println(segments.get(2).getV1Idx()+"+"+segments.get(2).getV2Idx());
        System.out.println("===================================================");

        return Mesh.newBuilder().addAllVertices(verticesWithColors).addAllSegments(segmentsWithColors).build();
    }

    private int[] extractColor_Number(String val) {
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0]);
        int green = Integer.parseInt(raw[1]);
        int blue = Integer.parseInt(raw[2]);
        return new int[]{red,green,blue};
    }


    private int findVertex(List<Vertex> vertexList,double x,double y){
        int i=0;
        for (Vertex v:vertexList){
            if(v.getX()==x&&v.getY()==y){
                return i;
            }
            i++;
        }
        return -1;
    }

}