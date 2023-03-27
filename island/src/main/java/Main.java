import Reproducibility.Seed;
import TerrainFeatures.Aquifers;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import configuration.Configuration;
import featureRenderer.ElevationRenderer;
import featureRenderer.ShapeRenderer;
import transformation.builtinADT.MeshADT;
import transformation.importation.Importer;
import transformation.importation.polygonImporter;
import transformation.importation.segmentImporter;
import transformation.importation.vertexImporter;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static MeshADT readInputMesh(Structs.Mesh aMesh, MeshADT meshADT) {

        Importer polygonImporter = new polygonImporter();
        Importer segmentImporter = new segmentImporter();
        Importer vertexImporter = new vertexImporter();
        vertexImporter.read(aMesh, meshADT);
        segmentImporter.read(aMesh, meshADT);
        polygonImporter.read(aMesh, meshADT);


        return meshADT;
    }

    public static void main(String[] args) throws IOException {

        Configuration config = new Configuration(args);
        Map<String, String> options = config.export();
        MeshADT meshADT = new MeshADT();

        String input_c = "C:\\Users\\22091\\IdeaProjects\\a2---mesh-generator-team-28_newnewnewn\\IOArea\\inputoff.mesh"; //use absolute address only
        String outputadress = "C:\\Users\\22091\\IdeaProjects\\a2---mesh-generator-team-28_newnewnewn\\IOArea\\inputoff.mesh";
        if (!(options.get(Configuration.INPUT) == (null))) {
            input_c = options.get(Configuration.INPUT);
        }
        System.out.println(input_c);
        if (!(options.get(Configuration.OUTPUT) == (null))) {
            outputadress = options.get(Configuration.OUTPUT);
        }

        Seed seed = new Seed((int) (Math.random() * 10000 + 1000));
        System.out.println("seed is:   "+seed.getSeed());
        if (!(options.get(Configuration.seed) == (null))) {
            seed = new Seed(Integer.parseInt(options.get(Configuration.seed)));
        }
        int water = 1;
        if (!(options.get(Configuration.aquifers) == (null))) {
            water = Integer.parseInt(options.get(Configuration.seed));
        }
        Aquifers aquifers = new Aquifers(meshADT, water);

        Structs.Mesh aMesh = new MeshFactory().read(input_c);


        MeshADT m = readInputMesh(aMesh, meshADT);


        if (!(options.get(Configuration.shape) == (null))) {
            m = new ShapeRenderer().Rendering(m, new Seed(Integer.parseInt(options.get(Configuration.shape))));
        } else {
            m = new ShapeRenderer().Rendering(m, seed);

        }

        if (!(options.get(Configuration.altitude) == (null))) {
            m = new ElevationRenderer().Rendering(m, new Seed(Integer.parseInt(options.get(Configuration.altitude))));
        } else {
            m = new ElevationRenderer().Rendering(m, seed);
        }

        m = aquifers.aquifersInitialization();

        Structs.Mesh output = m.toMesh();
        new MeshFactory().write(output, outputadress);//


        //MVP
//        Configuration config = new Configuration("generator/generator.jar -k irregular -h 1920 -w 1920 -p 1000 -r 5 -o ireg.mesh".split(" "));
//        Buildable specification = SpecificationFactory.create(config);
//        Mesh theMesh = specification.build();
//        LagoonRenderer mvp = new LagoonRenderer(theMesh);
//        new MeshFactory().write(mvp.Rendering(), "IOArea/full_color.mesh");//


    }
}