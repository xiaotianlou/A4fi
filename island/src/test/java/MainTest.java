import Reproducibility.Seed;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import featureRenderer.ShapeRenderer;
import org.junit.jupiter.api.Test;
import transformation.builtinADT.MeshADT;
import transformation.importation.Importer;
import transformation.importation.polygonImporter;
import transformation.importation.segmentImporter;
import transformation.importation.vertexImporter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() throws IOException {
        MeshADT meshADT = new MeshADT();
        String input_c="C:\\Users\\22091\\IdeaProjects\\a2---mesh-generator-team-28_new1\\IOArea\\inputoff.mesh";
        Structs.Mesh aMesh = new MeshFactory().read(input_c);

        Importer polygonImporter = new polygonImporter();
        Importer segmentImporter = new segmentImporter();
        Importer vertexImporter = new vertexImporter();
        vertexImporter.read(aMesh,meshADT);
        segmentImporter.read(aMesh,meshADT);
        polygonImporter.read(aMesh,meshADT);

        meshADT = new ShapeRenderer().Rendering(meshADT,new Seed());
        Structs.Mesh output= meshADT.toMesh();

        new MeshFactory().write(output, "C:\\Users\\22091\\IdeaProjects\\a2---mesh-generator-team-28_new1\\IOArea\\lagtest.mesh");//
//        java -jar visualizer/visualizer.jar -i C:\Users\22091\IdeaProjects\a2---mesh-generator-team-28_new1\IOArea\lagtest.mesh -o C:\Users\22091\IdeaProjects\a2---mesh-generator-team-28_new1\IOArea\lagtest.svg -x


    }
}