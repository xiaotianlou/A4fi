import Reproducibility.Seed;
import TerrainFeatures.Aquifers;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import configuration.Configuration;
import featureRenderer.*;
import featureRenderer.Shape.BackGroundGenerator;
import featureRenderer.Shape.LagoonGenerator;
import transformation.builtinADT.MeshADT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        Configuration config = new Configuration(args);
        Map<String, String> options = config.export();

        MeshADT meshADT = new MeshADT();
        int seedNumber = (int) (Math.random() * 100000);
        System.out.println("default seed :"+seedNumber);
        Seed defaultSeed = new Seed(seedNumber);

        String input_c = "..//IOArea\\inputoff.mesh";
        String outputaddress="..//IOArea\\outputoff.mesh";

        if (!(options.get(Configuration.inputAddress) == (null))) {
            input_c = options.get(Configuration.inputAddress);}
        if (!(options.get(Configuration.outputAddress) == (null))) {
            outputaddress = options.get(Configuration.outputAddress);}
        if (!(options.get(Configuration.seed) == (null))) {
            defaultSeed = new Seed(Integer.parseInt(options.get(Configuration.seed)));
        }
        if (options.get(Configuration.mode) == "lagoon") {
            Structs.Mesh aMesh = new MeshFactory().read(input_c);
            meshADT.readInputMesh(aMesh);
            new BackGroundGenerator().Genering(meshADT, defaultSeed);
            new LagoonGenerator().Genering(meshADT, defaultSeed);
            Structs.Mesh out = meshADT.toMesh();
            new MeshFactory().write(out, options.get(outputaddress));//
            System.exit(0);
        }


        if (!(options.get(Configuration.aquifersNumber) == (null))) {
            meshADT.setNumAquifers(Integer.parseInt(options.get(Configuration.aquifersNumber)));
        }


        if (!(options.get(Configuration.shapeSeed) == (null))) {
            new ShapeRenderer().Rendering(meshADT, new Seed(Integer.parseInt(options.get(Configuration.shapeSeed))));
        }else {
            new ShapeRenderer().Rendering(meshADT,defaultSeed);
        }

        if (!(options.get(Configuration.altitudeSeed) == (null))) {
            new ElevationRenderer().Rendering(meshADT, new Seed(Integer.parseInt(options.get(Configuration.altitudeSeed))));
        }else {
            new ShapeRenderer().Rendering(meshADT,defaultSeed);
        }

        if (!(options.get(Configuration.lakeMaxNumber) == (null))) {
            defaultSeed.setMaxlakeNumber(Integer.parseInt(Configuration.lakeMaxNumber));
        }
        new LakeRenderer().Rendering(meshADT, defaultSeed);


        if (!(options.get(Configuration.riverNumber) == (null))) {
            defaultSeed.setRiverNumber(Integer.parseInt(Configuration.riverNumber));
        }
        new RiversRenderer().Rendering(meshADT, defaultSeed);
        new BiomeRenderer().Rendering(meshADT, defaultSeed);

        if (!(options.get(Configuration.BiomeType) == (null))) {
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