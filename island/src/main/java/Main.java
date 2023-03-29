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
            new ElevationRenderer().Rendering(meshADT, defaultSeed;
        }else {
            new ShapeRenderer().Rendering(meshADT,defaultSeed);
        }



        new LakeRenderer().Rendering(meshADT, s);
        new RiversRenderer().Rendering(meshADT, s);
        new BiomeRenderer().Rendering(meshADT, s);






        if (!(options.get(Configuration.altitudeSeed) == (null))) {
//           seedBag.put(Configuration.altitudeSeed,options.get(Configuration.altitudeSeed));
        } else {
            new ElevationRenderer().Rendering(meshADT, seed);
        }






        int water = 1;
        if (!(options.get(Configuration.aquifers) == (null))) {
            water = Integer.parseInt(options.get(Configuration.seed));
        }
        new Aquifers().aquifersInitialization(meshADT,water);

        Structs.Mesh aMesh = new MeshFactory().read(input_c);


        meshADT.readInputMesh(aMesh);


        if (!(options.get(Configuration.shapeSeed) == (null))) {
            new ShapeRenderer().Rendering(meshADT, new Seed(Integer.parseInt(options.get(Configuration.shapeSeed))));
        } else {
            new ShapeRenderer().Rendering(meshADT, seed);

        }



        new Aquifers().aquifersInitialization(meshADT,water);

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

    private void exeCommands(String command) throws IOException {
        Process p = Runtime.getRuntime().exec(command);
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        String text = command + "\n";
        System.out.println(text);
        while ((line = input.readLine()) != null) {
            text += line;
            System.out.println("Line: " + line);
        }
    }
}