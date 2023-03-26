import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.junit.jupiter.api.Test;
import transformation.builtinADT.MeshADT;
import transformation.importation.Importer;
import transformation.importation.polygonImporter;
import transformation.importation.segmentImporter;
import transformation.importation.vertexImporter;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() {
        MeshADT meshADT = new MeshADT();
//        String input = "IOArea/inputoff.mesh";
        String input_c="IOArea/inputoffCE.mesh";
//        Structs.Mesh aMesh = new MeshFactory().read(input_c);

        Importer polygonImporter = new polygonImporter();
        Importer segmentImporter = new segmentImporter();
        Importer vertexImporter = new vertexImporter();
        vertexImporter.read(aMesh,meshADT);
        segmentImporter.read(aMesh,meshADT);
        polygonImporter.read(aMesh,meshADT);
        Structs.Mesh output= meshADT.toMesh();


//        new MeshFactory().write(output, "IOArea/outputC2333333333.mesh");//

    }
}