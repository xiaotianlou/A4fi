import Reproducibility.Seed;
import TerrainFeatures.Aquifers;
import ca.mcmaster.cas.se2aa4.a2.generator.specification.Buildable;
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
    public static MeshADT readInputMesh(Structs.Mesh aMesh,MeshADT meshADT){

        Importer polygonImporter = new polygonImporter();
        Importer segmentImporter = new segmentImporter();
        Importer vertexImporter = new vertexImporter();
        vertexImporter.read(aMesh,meshADT);
        segmentImporter.read(aMesh,meshADT);
        polygonImporter.read(aMesh,meshADT);



        return meshADT;
    }

    public static void main(String[] args) throws IOException {
        Configuration config = new Configuration(args);
        Map<String, String> options = config.export();
        MeshADT meshADT = new MeshADT();

        String input_c="../IOArea/inputoffC.mesh";
        String outputadress="../IOArea/inputoffC.mesh";
        if(!options.get(Configuration.INPUT).isEmpty())
        input_c=options.get(Configuration.INPUT).toString();
        if(!options.get(Configuration.OUTPUT).isEmpty())
        outputadress=options.get(Configuration.OUTPUT).toString();

        Seed seed = new Seed((int)Math.random() * 100000);
        if(!options.get(Configuration.seed).isEmpty())
            seed=new Seed(Integer.parseInt(options.get(Configuration.seed).toString()));
        int water=1;
        if(!options.get(Configuration.seed).isEmpty())
            water=Integer.parseInt(options.get(Configuration.seed).toString());
        Aquifers aquifers = new Aquifers(meshADT,water);
        Structs.Mesh aMesh = new MeshFactory().read(input_c);
        MeshADT m =readInputMesh(aMesh,meshADT);


        if(!options.get(Configuration.shape).isEmpty())
        m= new ShapeRenderer().Rendering(m,new Seed(Integer.parseInt(options.get(Configuration.shape).toString())));

        if(!options.get(Configuration.altitude).isEmpty())
        m= new ElevationRenderer().Rendering(m,new Integer.parseInt(options.get(Configuration.altitude).toString()));

        m=aquifers.aquifersInitialization();




        Structs.Mesh output= m.toMesh();
        new MeshFactory().write(output, outputadress);//



        //MVP
//        Configuration config = new Configuration("generator/generator.jar -k irregular -h 1920 -w 1920 -p 1000 -r 5 -o ireg.mesh".split(" "));
//        Buildable specification = SpecificationFactory.create(config);
//        Mesh theMesh = specification.build();
//        LagoonRenderer mvp = new LagoonRenderer(theMesh);
//        new MeshFactory().write(mvp.Rendering(), "IOArea/full_color.mesh");//


    }
}