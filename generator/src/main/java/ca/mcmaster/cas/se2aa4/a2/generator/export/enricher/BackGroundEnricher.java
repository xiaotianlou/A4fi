package ca.mcmaster.cas.se2aa4.a2.generator.export.enricher;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.awt.*;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/3/23 11:30
 */
public class BackGroundEnricher implements Enricher{

    Color backgound = new Color(5, 100, 208);

    public void changeBackGound(Color newBackGround){
        this.backgound=newBackGround;
    }
    public BackGroundEnricher(Color c){
        this.backgound=c;

    }

    @Override
    public Structs.Mesh process(Structs.Mesh aMesh) {

        Structs.Mesh.Builder clone = Structs.Mesh.newBuilder();
        clone.addAllVertices(aMesh.getVerticesList());
        clone.addAllSegments(aMesh.getSegmentsList());

        for(Structs.Polygon poly: aMesh.getPolygonsList()) {
            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(poly);

                String color = backgound.getRed()+","+backgound.getGreen()+","+backgound.getBlue();
                Structs.Property p = Structs.Property.newBuilder()
                        .setKey("rgb_color")
                        .setValue(color)
                        .build();
                pc.addProperties(p);
            clone.addPolygons(pc);
        }
        return clone.build();
    }
}
