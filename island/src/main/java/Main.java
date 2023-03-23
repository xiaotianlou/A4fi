import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Polygon;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Vertex;
import ca.mcmaster.cas.se2aa4.a2.generator.configuration.Configuration;
import ca.mcmaster.cas.se2aa4.a2.generator.export.Exporter;
import ca.mcmaster.cas.se2aa4.a2.generator.export.enricher.BackGroundEnricher;
import ca.mcmaster.cas.se2aa4.a2.generator.export.enricher.CircleIslandEnricher;
import ca.mcmaster.cas.se2aa4.a2.generator.specification.Buildable;
import ca.mcmaster.cas.se2aa4.a2.generator.specification.SpecificationFactory;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author loux8@mcmaster.ca
 * @date ${DATE} ${TIME}
 */
public class Main {
    public static void main(String[] args) throws IOException {
        //MVP
        Configuration config = new Configuration("generator/generator.jar -k irregular -h 1920 -w 1920 -p 1000 -r 5 -o ireg.mesh".split(" "));
        Buildable specification = SpecificationFactory.create(config);
        Mesh theMesh = specification.build();
        Renderer mvp = new LagoonRenderer(theMesh);
        new MeshFactory().write(mvp.Rendering(), "og.mesh");
    }
}