import Heatmaps.ElevationHeatMap;
import Heatmaps.HeatMap;
import Heatmaps.HumidityHeatMap;
import Reproducibility.Seed;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import featureRenderer.*;
import featureRenderer.Shape.BackGroundGenerator;
import featureRenderer.Shape.LagoonGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;
import transformation.importation.Importer;
import transformation.importation.polygonImporter;
import transformation.importation.segmentImporter;
import transformation.importation.vertexImporter;

import java.awt.*;

import java.io.IOException;


class MainTest {


    MeshADT meshADT;
    Seed s;

    @BeforeEach
    void initial() throws IOException {
        this.meshADT = new MeshADT();
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


    @Test
    void main() throws IOException {
        new ShapeRenderer().Rendering(meshADT, s);
        new ElevationRenderer().Rendering(meshADT, s);
        new LakeRenderer().Rendering(meshADT, s);
        new RiversRenderer().Rendering(meshADT, s);
        new BiomeRenderer().Rendering(meshADT, s);
//        s.setBiome("Tropical_Rain_Forest");
        new WhittakerDiagramsRenderer().Rendering(meshADT,s);
    }

    @Test
    void testElevationHeatMap() {
        new ShapeRenderer().Rendering(meshADT, s);
        new ElevationRenderer().Rendering(meshADT, s);
        new LakeRenderer().Rendering(meshADT, s);
        new RiversRenderer().Rendering(meshADT, s);
        new BiomeRenderer().Rendering(meshADT, s);

        HeatMap h = new ElevationHeatMap(meshADT);
        h.build();

    }
    @Test
    void testHumidityHeatMap() {
        new ShapeRenderer().Rendering(meshADT, s);
        new ElevationRenderer().Rendering(meshADT, s);
        new LakeRenderer().Rendering(meshADT, s);
        new RiversRenderer().Rendering(meshADT, s);
        new BiomeRenderer().Rendering(meshADT, s);

        HeatMap h = new HumidityHeatMap(meshADT);
        h.build();

    }


    @Test
    void testRiversRender() throws{
        new ShapeRenderer().Rendering(meshADT, s);
        new ElevationRenderer().Rendering(meshADT, s);
        new LakeRenderer().Rendering(meshADT, s);
        new RiversRenderer().Rendering(meshADT, s);
    }

    @Test
    void testDW() throws {
        new ShapeRenderer().Rendering(meshADT, s);
        new ElevationRenderer().Rendering(meshADT, s);
        new BiomeRenderer().Rendering(meshADT, s);
        String type = "Tropical_Seasonal_Forest";
        new WhittakerDiagramsRenderer().Rendering(meshADT, s);


    }

    @Test
    void testLakeRender() {
        new ShapeRenderer().Rendering(meshADT, s);
        new ElevationRenderer().Rendering(meshADT, s);
        new LakeRenderer().Rendering(meshADT, s);
    }


    @Test
    void testBiomeRender() {
        new ShapeRenderer().Rendering(meshADT, s);
        new ElevationRenderer().Rendering(meshADT, s);
        new BiomeRenderer().Rendering(meshADT, s);

    }


    @Test
    void MVP() throws IOException {
        MeshADT meshADT = new MeshADT();
        String input_c = "inputoff.mesh";
        Structs.Mesh aMesh = new MeshFactory().read(input_c);

        Importer polygonImporter = new polygonImporter();
        Importer segmentImporter = new segmentImporter();
        Importer vertexImporter = new vertexImporter();
        vertexImporter.read(aMesh, meshADT);
        segmentImporter.read(aMesh, meshADT);
        polygonImporter.read(aMesh, meshADT);
        int seedint = (int) (Math.random() * 100000);
        System.out.println("seed is:" + seedint);
        Seed s = new Seed(seedint);
//    s= new Seed(802517);
        new BackGroundGenerator().Genering(meshADT, s);
        new LagoonGenerator().Genering(meshADT, s);
        Structs.Mesh out = meshADT.toMesh();
        new MeshFactory().write(out, "MVP.mesh");//
//        java -jar visualizer/visualizer.jar -i C:\Users\22091\IdeaProjects\a2---mesh-generator-team-28_new1\IOArea\lagtest.mesh -o C:\Users\22091\IdeaProjects\a2---mesh-generator-team-28_new1\IOArea\lagtest.svg -x


    }

    @Test
    void testSimulink() {

        for (int i = 0; i < 100; i++) {
            double t = new Generable() {
                @Override
                public MeshADT Genering(MeshADT m, Seed seed) {
                    return null;
                }
            }.Simulink(i) * 700;
            System.out.println(t);
            Assertions.assertEquals(t, 700, 700 / 2);
        }
    }

    @Test
    void testShape() throws IOException {
        new ShapeRenderer().Rendering(meshADT, s);
        new ElevationRenderer().Rendering(meshADT, s);

    }

    @Test
    void testE2() throws IOException {

        int seedint = (int) (Math.random() * 1000000);
        seedint = 148000;
        System.out.println("seed is:" + seedint);
        Seed s = new Seed(seedint);
//    s= new Seed(802517);
        meshADT = new ShapeRenderer().Rendering(meshADT, s);
        meshADT = new ElevationRenderer().Rendering(meshADT, s);
        Structs.Mesh output = meshADT.toMesh();
        new MeshFactory().write(output, "..//IOArea\\Test.mesh");//
//java -jar visualizer/visualizer.jar -i C:\Users\22091\IdeaProjects\a2---mesh-generator-team-28_new1\IOArea\lagtest.mesh -o C:\Users\22091\IdeaProjects\a2---mesh-generator-team-28_new1\IOArea\lagtest1.svg -x

        new Color(22, 241, 140);
        for (PolygonADT temp : meshADT.getPolygons()) {
            var info = temp.getInfoSet();
            if (info.isIsland()) {
                int h = info.getElevation();
                if (h > 90) {
                    info.setColor(new int[]{229, 5, 5});//red
                } else if (h > 80) {
                    info.setColor(new int[]{158, 232, 29});//yellow
                } else if (h > 70) {
                    info.setColor(new int[]{31, 34, 225});//blue
                } else if (h > 60) {
                    info.setColor(new int[]{22, 241, 140});//green
                    System.out.println(info.getElevation());
                } else if (h > 50) {
                    info.setColor(new int[]{22, 241, 140});//green
                    System.out.println(info.getElevation());
                } else if (h > 40) {
                    info.setColor(new int[]{22, 241, 140});//green
                    System.out.println(info.getElevation());
                } else if (h > 30) {
                    info.setColor(new int[]{22, 241, 140});//green
                    System.out.println(info.getElevation());
                } else if (h > 20) {
                    info.setColor(new int[]{22, 241, 140});//green
                    System.out.println(info.getElevation());
                } else if (h >= 0) {
                    info.setColor(new int[]{22, 241, 140});//green
                    System.out.println(info.getElevation());
                } else if (h == -1) {
                    info.setColor(new int[]{0, 0, 0});//black
                    System.out.println(info.getElevation());
                }


            }
        }
    }

}