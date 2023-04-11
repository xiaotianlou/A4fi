import Reproducibility.Seed;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import transformation.builtinADT.MeshADT;

import java.io.IOException;

public class ShortestPathTest {
    MeshADT meshADT1;

    @Test
    void shortestPathTest() throws IOException {
        meshADT1 = new MeshADT();
        String input_c = "..//IOArea\\inputoff.mesh";
        Structs.Mesh aMesh = new MeshFactory().read(input_c);


        meshADT1.readInputMesh(aMesh);
        Structs.Mesh output = meshADT1.toMesh();


        new MeshFactory().write(output, "..//IOArea\\Testblank.mesh");//
//        java -jar visualizer/visualizer.jar -i IOArea//Test.mesh -o IOArea//Test.svg -x
        //java -jar visualizer/visualizer.jar -i IOArea//Testblank.mesh -o IOArea//Testblank.svg -x
    }


    @BeforeEach
    void initial() throws IOException {

    }


    @AfterEach
    void TestOutput() throws IOException {

    }
}
