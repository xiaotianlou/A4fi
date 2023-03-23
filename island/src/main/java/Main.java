

import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Vertex;
import ca.mcmaster.cas.se2aa4.a2.generator.configuration.Configuration;
import ca.mcmaster.cas.se2aa4.a2.generator.export.Exporter;
import ca.mcmaster.cas.se2aa4.a2.generator.export.enricher.BackGroundEnricher;
import ca.mcmaster.cas.se2aa4.a2.generator.export.enricher.CircleIslandEnricher;
import ca.mcmaster.cas.se2aa4.a2.generator.export.enricher.RandomEnricher;
import ca.mcmaster.cas.se2aa4.a2.generator.specification.Buildable;
import ca.mcmaster.cas.se2aa4.a2.generator.specification.SpecificationFactory;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author loux8@mcmaster.ca
 * @date ${DATE} ${TIME}
 */
public class Main {
    public static void main(String[] args) throws IOException {

        Configuration config = new Configuration("generator/generator.jar -k irregular -h 1920 -w 1920 -p 1000 -r 5 -o ireg.mesh".split(" "));

        Buildable specification = SpecificationFactory.create(config);

        Mesh theMesh = specification.build();

        //get centreid of centre poly relax
        int cen_x=1920/2;
        int cen_y=1920/2;
        int circle_size =800;
        List<Vertex> centre_bag=new ArrayList<Vertex>();
        List<Vertex> centre_bag2=new ArrayList<Vertex>();

        for (Polygon p :theMesh.getPolygons()){
            float x =p.centroid().x();
            float y =p.centroid().y();
            //先找
            double distance = Math.sqrt(Math.pow(x-cen_x,2)+Math.pow(y-cen_y,2));
            if(distance<circle_size){
                centre_bag.add(p.centroid());
                if(distance<circle_size/2){
                    centre_bag2.add(p.centroid());
                }
            }
        }

        Structs.Mesh exported =new Exporter().run(theMesh);
        exported = new BackGroundEnricher(new Color(70,130,180)).process(exported);
        exported = new CircleIslandEnricher(new Color(153, 220, 211),centre_bag).process(exported);
        exported = new CircleIslandEnricher(new Color(253, 255, 255),centre_bag2).process(exported);


        new MeshFactory().write(exported, "og.mesh");
    }
}