import Reproducibility.Seed;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import featureRenderer.*;
import featureRenderer.City.CityRenderer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.SegmentADT;

import java.io.IOException;

public class CityTest {
    MeshADT meshADT;
    Seed s;

    @Test
    public void cityRender(){

        new ShapeRenderer().Rendering(meshADT, s);
        new ElevationRenderer().Rendering(meshADT, s);
        new LakeRenderer().Rendering(meshADT, s);
        new RiversRenderer().Rendering(meshADT, s);
        new BiomeRenderer().Rendering(meshADT, s);
        s.setCityNumber(6);
        new CityRenderer().Rendering(meshADT,s);

    }


    @Test
    public void cityIdea() {

        //        new Color(123, 183, 64);
        SegmentADT s = meshADT.getSegment(meshADT.getVertex(500, 500), meshADT.getVertex(500, 500));
        s.setColor(new int[]{123, 183, 64});
        s.setThickness(30);

    }

    @Test
    public void cityinPoly() {
        int seedint = (int) (Math.random() * 1000000);
        Seed s = new Seed(seedint);
        //        new Color(123, 183, 64);
        new ShapeRenderer().Rendering(meshADT, s);
        new ElevationRenderer().Rendering(meshADT, s);
        new LakeRenderer().Rendering(meshADT, s);
        new RiversRenderer().Rendering(meshADT, s);
        new BiomeRenderer().Rendering(meshADT, s);

        for (var temp : meshADT.getPolygons()) {
            double x = temp.getCentrVertex().getX();
            double y = temp.getCentrVertex().getY();
            SegmentADT s1 = meshADT.getSegment(meshADT.getVertex(x, y), meshADT.getVertex(x, y));
            s1.setColor(new int[]{123, 183, 64});
            s1.setThickness(30);
        }


    }


    @BeforeEach
    void initial() throws IOException {
        meshADT = new MeshADT();
        String input_c = "..//IOArea\\inputoff.mesh";
        Structs.Mesh aMesh = new MeshFactory().read(input_c);
        meshADT.readInputMesh(aMesh);
        int seedint = (int) (Math.random() * 1000000);
        System.out.println("seed is:" + seedint);
        this.s = new Seed(seedint);
    }


    @AfterEach
    void TestOutput() throws IOException {

        Structs.Mesh output = meshADT.toMesh();
        new MeshFactory().write(output, "..//IOArea\\Test.mesh");//
//        java -jar visualizer/visualizer.jar -i IOArea//Test.mesh -o IOArea//Test.svg -x
    }

}
