import TerrainFeatures.Aquifers;
import ca.mcmaster.cas.se2aa4.a2.generator.specification.Buildable;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import configuration.Configuration;
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
        String input_c="IOArea/inputoffC.mesh";

        Structs.Mesh aMesh = new MeshFactory().read(input_c);
        readInputMesh(aMesh,meshADT);





        Aquifers aquifers = new Aquifers(meshADT,1);

        MeshADT meshADT_A = aquifers.aquifersInitialization();





        Structs.Mesh output= meshADT.toMesh();




        new MeshFactory().write(output, "IOArea/outputC2333333333.mesh");//



        //MVP
//        Configuration config = new Configuration("generator/generator.jar -k irregular -h 1920 -w 1920 -p 1000 -r 5 -o ireg.mesh".split(" "));
//        Buildable specification = SpecificationFactory.create(config);
//        Mesh theMesh = specification.build();
//        LagoonRenderer mvp = new LagoonRenderer(theMesh);
//        new MeshFactory().write(mvp.Rendering(), "IOArea/full_color.mesh");//


    }
}