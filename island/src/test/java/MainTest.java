import Reproducibility.Seed;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import featureRenderer.Shape.Generable;
import featureRenderer.ShapeRenderer;
import org.junit.jupiter.api.Test;
import transformation.builtinADT.MeshADT;
import transformation.importation.Importer;
import transformation.importation.polygonImporter;
import transformation.importation.segmentImporter;
import transformation.importation.vertexImporter;

import java.io.IOException;

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
        meshADT = new ShapeRenderer().Rendering(meshADT, s);
        Structs.Mesh output = meshADT.toMesh();
        new MeshFactory().write(output, "C:\\Users\\22091\\IdeaProjects\\a2---mesh-generator-team-28_new1\\IOArea\\lagtest.mesh");//
//        java -jar visualizer/visualizer.jar -i C:\Users\22091\IdeaProjects\a2---mesh-generator-team-28_new1\IOArea\lagtest.mesh -o C:\Users\22091\IdeaProjects\a2---mesh-generator-team-28_new1\IOArea\lagtest.svg -x


    }
}