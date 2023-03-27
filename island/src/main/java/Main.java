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
        //MVP
//        Configuration config = new Configuration("generator/generator.jar -k irregular -h 1920 -w 1920 -p 1000 -r 5 -o ireg.mesh".split(" "));
//        Buildable specification = SpecificationFactory.create(config);
//        Mesh theMesh = specification.build();
//        LagoonRenderer mvp = new LagoonRenderer(theMesh);
//        new MeshFactory().write(mvp.Rendering(), "IOArea/full_color.mesh");//

        Configuration config = new Configuration(args);
        Map<String, String> options = config.export();
        MeshADT meshADT = new MeshADT();

        String input_c = "C:\\Users\\22091\\IdeaProjects\\a2---mesh-generator-team-28_newnewnewn\\IOArea\\inputoff.mesh"; //use absolute address only
        String outputadress = "C:\\Users\\22091\\IdeaProjects\\a2---mesh-generator-team-28_newnewnewn\\IOArea\\inputoff.mesh";
        if (options.get(Configuration.mode) == "lagoon") {
            Structs.Mesh aMesh = new MeshFactory().read(options.get(Configuration.INPUT));
            meshADT.readInputMesh(aMesh);
            int seedint = (int) (Math.random() * 100000);
            System.out.println("seed is:" + seedint);
            Seed s = new Seed(seedint);
            meshADT = new BackGroundGenerator().Genering(meshADT, s);
            meshADT = new LagoonGenerator().Genering(meshADT, s);
            Structs.Mesh out = meshADT.toMesh();
            new MeshFactory().write(out, options.get(Configuration.OUTPUT));//

        }

        if (!(options.get(Configuration.INPUT) == (null))) {
            input_c = options.get(Configuration.INPUT);
        }
        System.out.println(input_c);
        if (!(options.get(Configuration.OUTPUT) == (null))) {
            outputadress = options.get(Configuration.OUTPUT);
        }

        Seed seed = new Seed((int) (Math.random() * 1000000));
        System.out.println("seed is:   " + seed.getSeed());
        if (!(options.get(Configuration.seed) == (null))) {
            seed = new Seed(Integer.parseInt(options.get(Configuration.seed)));
        }
        int water = 1;
        if (!(options.get(Configuration.aquifers) == (null))) {
            water = Integer.parseInt(options.get(Configuration.seed));
        }
        Aquifers aquifers = new Aquifers(meshADT, water);

        Structs.Mesh aMesh = new MeshFactory().read(input_c);


        meshADT.readInputMesh(aMesh);


        if (!(options.get(Configuration.shape) == (null))) {
            new ShapeRenderer().Rendering(meshADT, new Seed(Integer.parseInt(options.get(Configuration.shape))));
        } else {
            new ShapeRenderer().Rendering(meshADT, seed);

        }

        if (!(options.get(Configuration.altitude) == (null))) {
            new ElevationRenderer().Rendering(meshADT, new Seed(Integer.parseInt(options.get(Configuration.altitude))));
        } else {
            new ElevationRenderer().Rendering(meshADT, seed);
        }

        meshADT = aquifers.aquifersInitialization();

        Structs.Mesh output = meshADT.toMesh();
        new MeshFactory().write(output, outputadress);//


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