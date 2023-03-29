import Reproducibility.Seed;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import configuration.ConfigurationIsland;
import featureRenderer.*;
import featureRenderer.Shape.BackGroundGenerator;
import featureRenderer.Shape.LagoonGenerator;
import transformation.builtinADT.MeshADT;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        ConfigurationIsland config = new ConfigurationIsland(args);
        Map<String, String> options = config.export();

        MeshADT meshADT = new MeshADT();
        int seedNumber = (int) (Math.random() * 100000);
        System.out.println("default seed :"+seedNumber);
        Seed defaultSeed = new Seed(seedNumber);

        String input_c = "..//IOArea\\inputoff.mesh";
        String outputaddress="..//IOArea\\outputoff.mesh";
//        java -jar island/island.jar -i IOArea/inputoff.mesh -o IOArea/lagoon.mesh -mode lagoon
        if (!(options.get(ConfigurationIsland.inputAddress) == (null))) {
            input_c = options.get(ConfigurationIsland.inputAddress);}
        if (!(options.get(ConfigurationIsland.outputAddress) == (null))) {
            outputaddress = options.get(ConfigurationIsland.outputAddress);}
        if (!(options.get(ConfigurationIsland.seed) == (null))) {
            defaultSeed = new Seed(Integer.parseInt(options.get(ConfigurationIsland.seed)));
        }
        if (options.get(ConfigurationIsland.mode).equals( "lagoon")) {
            Structs.Mesh aMesh = new MeshFactory().read(input_c);
            meshADT.readInputMesh(aMesh);
            new BackGroundGenerator().Genering(meshADT, defaultSeed);
            new LagoonGenerator().Genering(meshADT, defaultSeed);
            Structs.Mesh out = meshADT.toMesh();
            new MeshFactory().write(out, options.get(ConfigurationIsland.outputAddress));//
            System.exit(0);
        }


        if (!(options.get(ConfigurationIsland.aquifersNumber) == (null))) {
            meshADT.setNumAquifers(Integer.parseInt(options.get(ConfigurationIsland.aquifersNumber)));
        }


        if (!(options.get(ConfigurationIsland.shapeSeed) == (null))) {
            new ShapeRenderer().Rendering(meshADT, new Seed(Integer.parseInt(options.get(ConfigurationIsland.shapeSeed))));
        }else {
            new ShapeRenderer().Rendering(meshADT,defaultSeed);
        }

        if (!(options.get(ConfigurationIsland.altitudeSeed) == (null))) {
            new ElevationRenderer().Rendering(meshADT, new Seed(Integer.parseInt(options.get(ConfigurationIsland.altitudeSeed))));
        }else {
            new ShapeRenderer().Rendering(meshADT,defaultSeed);
        }

        if (!(options.get(ConfigurationIsland.lakeMaxNumber) == (null))) {
            defaultSeed.setMaxlakeNumber(Integer.parseInt(ConfigurationIsland.lakeMaxNumber));
        }
        new LakeRenderer().Rendering(meshADT, defaultSeed);


        if (!(options.get(ConfigurationIsland.riverNumber) == (null))) {
            defaultSeed.setRiverNumber(Integer.parseInt(ConfigurationIsland.riverNumber));
        }
        new RiversRenderer().Rendering(meshADT, defaultSeed);
        new BiomeRenderer().Rendering(meshADT, defaultSeed);

        if (!(options.get(ConfigurationIsland.BiomeType) == (null))) {
            new WhittakerDiagramsRenderer().Rendering(meshADT,defaultSeed);
        }

        Structs.Mesh output = meshADT.toMesh();
        new MeshFactory().write(output, outputaddress);//

        //shape- seed
        //altitude --seed
        //lake max number
        //river -number of river
        //aquifers number of aquifers
        //soil seed
        //Biome string make island


    }

}