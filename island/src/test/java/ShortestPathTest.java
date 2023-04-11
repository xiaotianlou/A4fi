import Graph.*;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;
import transformation.builtinADT.SegmentADT;
import transformation.builtinADT.VertexADT;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShortestPathTest {
    MeshADT meshADT1;

    @Test
    void shortestPathTest() throws IOException {

        System.out.println("开始测试");
        List<Nodes> nodeList = new ArrayList<>();


        for (PolygonADT p : meshADT1.getPolygons()) {
            double cenx = p.getCentroid().getX();
            double ceny = p.getCentroid().getY();
            Nodes n = new Nodes(cenx, ceny, p.getElevation());
            //先把nodes放到nodeslist里，然后再从list里xy找邻居
            nodeList.add(n);
        }
        System.out.println("开始加邻居");
        for (Nodes n : nodeList) {//从list里找n的邻居

            List<Nodes> adjList = new ArrayList<>();
            for (PolygonADT p : meshADT1.getPolygons()) {
                if (n.getX() == p.getCentroid().getX() && n.getY() == p.getCentroid().getY()) {//找node对应的poly
                    for (PolygonADT adjp : p.getPolygons()) {//找poly邻居
                        for (Nodes node : nodeList) {//找邻居在nodelist里的实例
                            if (node.getX() == adjp.getCentroid().getX() && node.getY() == adjp.getCentroid().getY()) {
                                adjList.add(node);
//                                System.out.println("1");
                            }
                        }
                    }
                }
            }
            n.setAdjacent(new ArrayList<>(adjList));
        }
        System.out.println("finished initial ");


        Graph a = new Graph(nodeList);
        PathFinder pf = new DijkstraShortestPath(a);



       List<Edges> ouput= pf.find(nodeList.get(3),nodeList.get(900));
        System.out.println("结束测试");
        //把线段加进graph


//        new Color(123, 183, 64);
//       SegmentADT s = meshADT1.getSegment(meshADT1.getVertex(0,0),meshADT1.getVertex(100,100));
//       s.setColor(new int[]{123, 183, 64});
//       s.setThickness(10);
        for (Edges e: ouput){
            SegmentADT s =meshADT1.getSegment(meshADT1.getVertex(e.getStart().getX(),e.getStart().getY()),meshADT1.getVertex(e.getEnd().getX(),e.getEnd().getY()));
            s.setColor(new int[]{123, 183, 64});
            s.setThickness(10);
        }




//        for (var s:mesh.getSegmentsList()){
//            SegmentADT segmentADT = meshADT.getSegment(meshADT.getVertices().get(s.getV1Idx()),meshADT.getVertices().get(s.getV2Idx()));
//            segmentADT.getStart().addSegments(segmentADT);
//            segmentADT.getEnd().addSegments(segmentADT);
//            for (int n = mesh.getPropertiesCount();n>0;n--){
//                if (n == 1){
//                    segmentADT .setColor(s.getProperties(0).getValue());
//                }
//            }
//        }


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
