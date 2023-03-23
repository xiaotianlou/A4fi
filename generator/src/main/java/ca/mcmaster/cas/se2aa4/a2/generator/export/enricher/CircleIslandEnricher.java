package ca.mcmaster.cas.se2aa4.a2.generator.export.enricher;

import ca.mcmaster.cas.se2aa4.a2.generator.adt.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/3/23 14:18
 */
public class CircleIslandEnricher implements Enricher{
    Color c = new Color(187, 185, 48);

    List<Vertex> cen_bag = new ArrayList<Vertex>();

    public CircleIslandEnricher(Color newBackGround,List cen_bag){
        this.c=newBackGround;
        this.cen_bag=cen_bag;
    }


    @Override
    public Structs.Mesh process(Structs.Mesh aMesh) {
        Structs.Mesh.Builder clone = Structs.Mesh.newBuilder();
        clone.addAllVertices(aMesh.getVerticesList());
        clone.addAllSegments(aMesh.getSegmentsList());
        double tempx;
        double tempy;
        for(Structs.Polygon poly: aMesh.getPolygonsList()) {
            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(poly);

            tempx =aMesh.getVerticesList().get(poly.getCentroidIdx()).getX();
            tempy =aMesh.getVerticesList().get(poly.getCentroidIdx()).getY();
            for (Vertex v :cen_bag){
                if(v.x()==tempx&&v.y()==tempy){
                    String color = c.getRed()+","+c.getGreen()+","+c.getBlue();
                    Structs.Property p = Structs.Property.newBuilder()
                            .setKey("rgb_color")
                            .setValue(color)
                            .build();
                    pc.addProperties(p);
                }
            }

            clone.addPolygons(pc);
        }
        return clone.build();
    }
}
