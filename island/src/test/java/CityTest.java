import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.SegmentADT;

import java.io.IOException;

public class CityTest {
    MeshADT meshADT1;

    @Test
    public void city() {

        //        new Color(123, 183, 64);
       SegmentADT s = meshADT1.getSegment(meshADT1.getVertex(500,500),meshADT1.getVertex(500,500));
       s.setColor(new int[]{123, 183, 64});
       s.setThickness(100);


    }
    @BeforeEach
    void initial() throws IOException {
        meshADT1 = new MeshADT();
        String input_c = "..//IOArea\\inputoff.mesh";
        Structs.Mesh aMesh = new MeshFactory().read(input_c);
        meshADT1.readInputMesh(aMesh);
    }


    @AfterEach
    void TestOutput() throws IOException {

        Structs.Mesh output = meshADT1.toMesh();
        new MeshFactory().write(output, "..//IOArea\\Test.mesh");//
//        java -jar visualizer/visualizer.jar -i IOArea//Test.mesh -o IOArea//Test.svg -x
    }

}
