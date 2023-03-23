

import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.generator.configuration.Configuration;
import ca.mcmaster.cas.se2aa4.a2.generator.export.Exporter;
import ca.mcmaster.cas.se2aa4.a2.generator.export.enricher.BackGroundEnricher;
import ca.mcmaster.cas.se2aa4.a2.generator.export.enricher.RandomEnricher;
import ca.mcmaster.cas.se2aa4.a2.generator.specification.Buildable;
import ca.mcmaster.cas.se2aa4.a2.generator.specification.SpecificationFactory;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.io.File;
import java.io.IOException;

/**
 * @author loux8@mcmaster.ca
 * @date ${DATE} ${TIME}
 */
public class Main {
    public static void main(String[] args) throws IOException {

        Configuration config = new Configuration("generator/generator.jar -k irregular -h 1920 -w 1920 -p 1000 -r 5 -o ireg.mesh".split(" "));

        Buildable specification = SpecificationFactory.create(config);

        Mesh theMesh = specification.build();

        Structs.Mesh exported =new Exporter().run(theMesh);


        exported = new BackGroundEnricher().process(exported);
        new MeshFactory().write(exported, "og.mesh");
    }
}