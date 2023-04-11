import Graph.Graph;
import Graph.Nodes;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShortestPathTest {
    MeshADT meshADT1;

    @Test
    void shortestPathTest() throws IOException {


        List<Nodes> nodeList = new ArrayList<>();
        for (PolygonADT p : meshADT1.getPolygons()) {
            double cenx = p.getCentroid().getX();
            double ceny = p.getCentroid().getY();
            Nodes n = new Nodes(cenx, ceny, p.getElevation());
            //先把nodes放到nodeslist里，然后再从list里xy找邻居
            nodeList.add(n);
        }

        for (Nodes n :nodeList){
            List<Nodes> adjList = new ArrayList<>();
            for (PolygonADT p:meshADT1.getPolygons()){
             if(n.getX()==p.getCentroid().getX()&&n.getY()==p.getCentroid().getY()){


             }


            }

            n.setAdjacent(adjList);
        }




        Graph a = new Graph();

        PathFinder pf = new DijkstraShortestPath();


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
