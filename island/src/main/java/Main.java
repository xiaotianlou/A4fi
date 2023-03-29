import Reproducibility.Seed;
import TerrainFeatures.Aquifers;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import configuration.Configuration;
import featureRenderer.ElevationRenderer;
import featureRenderer.Shape.BackGroundGenerator;
import featureRenderer.Shape.LagoonGenerator;
import featureRenderer.ShapeRenderer;
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



        if (!(options.get(Configuration.seed) == (null))) {
            defaultSeed = new Seed(Integer.parseInt(options.get(Configuration.seed)));
        }

        for (InputType temp : InputType.values()){
            seedBag.put(temp,defaultSeed);
        }

        String input_c;
        String outputaddress;

        if (options.get(Configuration.inputAddress) == (null)) {
            input_c = "..//IOArea\\inputoff.mesh";
        }else {input_c = options.get(Configuration.inputAddress);}

        if (options.get(Configuration.OUTPUT) == (null)) {
            outputaddress = "..//IOArea\\outputoff.mesh";
        }else {outputaddress = options.get(Configuration.OUTPUT);}

        if (options.get(Configuration.mode) == "lagoon") {
            Structs.Mesh aMesh = new MeshFactory().read(input_c);
            meshADT.readInputMesh(aMesh);
            new BackGroundGenerator().Genering(meshADT, defaultSeed);
            new LagoonGenerator().Genering(meshADT, defaultSeed);
            Structs.Mesh out = meshADT.toMesh();
            new MeshFactory().write(out, options.get(outputaddress));//
        }









        if (!(options.get(Configuration.altitudeSeed) == (null))) {
           seedBag.put(Configuration.altitudeSeed,options.get(Configuration.altitudeSeed))
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