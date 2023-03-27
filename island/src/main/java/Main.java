import Reproducibility.Seed;
import TerrainFeatures.Aquifers;
import TerrainFeatures.Lakes;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import featureRenderer.ElevationRenderer;
import featureRenderer.RiversRenderer;
import featureRenderer.ShapeRenderer;
import transformation.builtinADT.MeshADT;
import transformation.importation.Importer;
import transformation.importation.polygonImporter;
import transformation.importation.segmentImporter;
import transformation.importation.vertexImporter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
//        Configuration config = new Configuration("generator/generator.jar -k irregular -h 1920 -w 1920 -p 1000 -r 5 -o ireg.mesh".split(" "));
//        Mesh theMesh = specification.build();
//        new MeshFactory().write(mvp.Rendering(), "og.mesh");



        MeshADT meshADT = new MeshADT();
//        String input = "IOArea/inputoff.mesh";
        String input_c="IOArea/inputoff.mesh";
        Structs.Mesh aMesh = new MeshFactory().read(input_c);

        Importer polygonImporter = new polygonImporter();
        Importer segmentImporter = new segmentImporter();
        Importer vertexImporter = new vertexImporter();
        vertexImporter.read(aMesh,meshADT);
        segmentImporter.read(aMesh,meshADT);
        polygonImporter.read(aMesh,meshADT);
        Aquifers aquifers = new Aquifers(meshADT,10);
        meshADT = aquifers.aquifersInitialization();



        int seedint = (int) (Math.random() * 1000000);
        seedint =6082;
        System.out.println("seed is:"+seedint );
        Seed s = new Seed(seedint);
//    s= new Seed(802517);
        meshADT = new ShapeRenderer().Rendering(meshADT,s);
        meshADT = new ElevationRenderer().Rendering(meshADT,s);

        Lakes lakes = new Lakes();
        meshADT = lakes.lakeBuilder(meshADT,20);

        RiversRenderer riversRenderer = new RiversRenderer();

       riversRenderer.Rendering(meshADT,s);







        for (var p: meshADT.getPolygons()){
            System.out.println(p.isLake());
        }




        Structs.Mesh output= meshADT.toMesh();




        System.out.println(output.getPolygonsCount());



        new MeshFactory().write(output, "IOArea/outputRiver.mesh");//



        //MVP
//        Configuration config = new Configuration("generator/generator.jar -k irregular -h 1920 -w 1920 -p 1000 -r 5 -o ireg.mesh".split(" "));
//        Buildable specification = SpecificationFactory.create(config);
//        Mesh theMesh = specification.build();
//        LagoonRenderer mvp = new LagoonRenderer(theMesh);
//        new MeshFactory().write(mvp.Rendering(), "IOArea/full_color.mesh");//


    }
}