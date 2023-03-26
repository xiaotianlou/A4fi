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

import java.awt.*;
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
        seedint =148000;
        System.out.println("seed is:"+seedint );
        Seed s = new Seed(seedint);
//    s= new Seed(802517);
        meshADT = new ShapeRenderer().Rendering(meshADT,s);
        meshADT = new ElevationRenderer().Rendering(meshADT,s);
        Structs.Mesh output = meshADT.toMesh();
        new MeshFactory().write(output, "C:\\Users\\22091\\IdeaProjects\\a2---mesh-generator-team-28_new1\\IOArea\\lagtest.mesh");//
//java -jar visualizer/visualizer.jar -i C:\Users\22091\IdeaProjects\a2---mesh-generator-team-28_new1\IOArea\lagtest.mesh -o C:\Users\22091\IdeaProjects\a2---mesh-generator-team-28_new1\IOArea\lagtest1.svg -x

        new Color(22, 241, 140);
        for (PolygonADT temp : meshADT.getPolygons()){
            var info =temp.getInfoSet();
            if (info.isIsland()){
                int h=info.getElevation();
                if(h>90) {
                    info.setColor(new int[]{229, 5, 5});//red
                }
                else if(h>80){
                    info.setColor(new int[]{158, 232, 29});//yellow
                }
                else if(h>70){
                    info.setColor(new int[]{31, 34, 225});//blue
                }
                else if(h>60){
                    info.setColor(new int[]{22, 241, 140});//green
                    System.out.println(info.getElevation());
                }
                else if(h>50){
                    info.setColor(new int[]{22, 241, 140});//green
                    System.out.println(info.getElevation());
                }
                else if(h>40){
                    info.setColor(new int[]{22, 241, 140});//green
                    System.out.println(info.getElevation());
                }
                else if(h>30){
                    info.setColor(new int[]{22, 241, 140});//green
                    System.out.println(info.getElevation());
                }
                else if(h>20){
                    info.setColor(new int[]{22, 241, 140});//green
                    System.out.println(info.getElevation());
                }
                else if(h>=0){
                    info.setColor(new int[]{22, 241, 140});//green
                    System.out.println(info.getElevation());
                }
                else if(h==-1){
                    info.setColor(new int[]{0,0,0});//black
                    System.out.println(info.getElevation());
                }


            }
        }
        Structs.Mesh output2 = meshADT.toMesh();
        new MeshFactory().write(output2, "C:\\Users\\22091\\IdeaProjects\\a2---mesh-generator-team-28_new1\\IOArea\\lagtestH.mesh");//
//java -jar visualizer/visualizer.jar -i C:\Users\22091\IdeaProjects\a2---mesh-generator-team-28_new1\IOArea\lagtestH.mesh -o C:\Users\22091\IdeaProjects\a2---mesh-generator-team-28_new1\IOArea\lagtest1H.svg -x


    }

}