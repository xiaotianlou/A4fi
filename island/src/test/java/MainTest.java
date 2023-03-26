import Reproducibility.Seed;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import featureRenderer.ElevationRenderer;
import featureRenderer.Generable;
import featureRenderer.ShapeRenderer;
import org.junit.jupiter.api.Test;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;
import transformation.importation.Importer;
import transformation.importation.polygonImporter;
import transformation.importation.segmentImporter;
import transformation.importation.vertexImporter;

import java.io.IOException;
import java.util.ArrayList;

class MainTest {

    @Test
    void main() throws IOException {

    }

    @Test
    void testSimulink() {
        System.out.println(new Generable() {
            @Override
            public MeshADT Genering(MeshADT m, Seed seed) {
                return null;
            }
        }.Simulink(1) * 700);
    }

    @Test
    void testShape() throws IOException {
        MeshADT meshADT = new MeshADT();
        String input_c = "C:\\Users\\22091\\IdeaProjects\\a2---mesh-generator-team-28_new1\\IOArea\\inputoff.mesh";
        Structs.Mesh aMesh = new MeshFactory().read(input_c);

        Importer polygonImporter = new polygonImporter();
        Importer segmentImporter = new segmentImporter();
        Importer vertexImporter = new vertexImporter();
        vertexImporter.read(aMesh, meshADT);
        segmentImporter.read(aMesh, meshADT);
        polygonImporter.read(aMesh, meshADT);
        int seedint = (int) (Math.random() * 1000000);
        System.out.println("seed is:"+seedint );
        Seed s = new Seed(seedint);
//    s= new Seed(802517);
        meshADT = new ShapeRenderer().Rendering(meshADT,s);
        Structs.Mesh output = meshADT.toMesh();
        new MeshFactory().write(output, "C:\\Users\\22091\\IdeaProjects\\a2---mesh-generator-team-28_new1\\IOArea\\lagtest.mesh");//
//        java -jar visualizer/visualizer.jar -i C:\Users\22091\IdeaProjects\a2---mesh-generator-team-28_new1\IOArea\lagtest.mesh -o C:\Users\22091\IdeaProjects\a2---mesh-generator-team-28_new1\IOArea\lagtest.svg -x


    }
    @Test
    void  testE2() throws IOException {
        MeshADT meshADT = new MeshADT();
        String input_c = "C:\\Users\\22091\\IdeaProjects\\a2---mesh-generator-team-28_new1\\IOArea\\inputoff.mesh";
        Structs.Mesh aMesh = new MeshFactory().read(input_c);

        Importer polygonImporter = new polygonImporter();
        Importer segmentImporter = new segmentImporter();
        Importer vertexImporter = new vertexImporter();
        vertexImporter.read(aMesh, meshADT);
        segmentImporter.read(aMesh, meshADT);
        polygonImporter.read(aMesh, meshADT);
        int seedint = (int) (Math.random() * 1000000);
        System.out.println("seed is:"+seedint );
        Seed s = new Seed(seedint);
//    s= new Seed(802517);
        meshADT = new ShapeRenderer().Rendering(meshADT,s);
        meshADT = new ElevationRenderer().Rendering(meshADT,s);
        Structs.Mesh output = meshADT.toMesh();
        new MeshFactory().write(output, "C:\\Users\\22091\\IdeaProjects\\a2---mesh-generator-team-28_new1\\IOArea\\lagtest.mesh");//
//java -jar visualizer/visualizer.jar -i C:\Users\22091\IdeaProjects\a2---mesh-generator-team-28_new1\IOArea\lagtest.mesh -o C:\Users\22091\IdeaProjects\a2---mesh-generator-team-28_new1\IOArea\lagtest1.svg -x

        ArrayList<PolygonADT> p =meshADT.getPolygons();

        for (PolygonADT temp : p){
            temp.getInfoSet().setColor(new int[]{255,255,255-temp.getInfoSet().getElevation()});
        }
        Structs.Mesh output2 = meshADT.toMesh();
        new MeshFactory().write(output, "C:\\Users\\22091\\IdeaProjects\\a2---mesh-generator-team-28_new1\\IOArea\\lagtestH.mesh");//
//        java -jar visualizer/visualizer.jar -i C:\Users\22091\IdeaProjects\a2---mesh-generator-team-28_new1\IOArea\lagtestH.mesh -o C:\Users\22091\IdeaProjects\a2---mesh-generator-team-28_new1\IOArea\lagtest1H.svg -x


    }

}