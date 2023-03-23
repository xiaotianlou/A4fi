

import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.generator.configuration.Configuration;
import ca.mcmaster.cas.se2aa4.a2.generator.specification.Buildable;
import ca.mcmaster.cas.se2aa4.a2.generator.specification.SpecificationFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.io.File;

/**
 * @author loux8@mcmaster.ca
 * @date ${DATE} ${TIME}
 */
public class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration(args);
        Buildable specification = SpecificationFactory.create(config);
        Mesh theMesh = specification.build();


    }
}