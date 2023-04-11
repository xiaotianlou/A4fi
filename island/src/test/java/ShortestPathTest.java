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
        //Preparation parameter of mesh adt to pathfinder
        List<Nodes> nodeList = new ArrayList<>();
        for (PolygonADT p : meshADT1.getPolygons()) {
            double cenx = p.getCentroid().getX();
            double ceny = p.getCentroid().getY();
            Nodes n = new Nodes(cenx, ceny, p.getElevation());
            //先把nodes放到nodeslist里，然后再从list里xy找邻居
            nodeList.add(n);
        }
        System.out.println("adding Neighborhood");
        for (Nodes n : nodeList) {//Find n's neighbors from the list
            List<Nodes> adjList = new ArrayList<>();
            for (PolygonADT p : meshADT1.getPolygons()) {
                if (n.getX() == p.getCentroid().getX() && n.getY() == p.getCentroid().getY()) {//Find the poly corresponding to the node
                    for (PolygonADT adjp : p.getPolygons()) {//Find Poly Neighbors
                        for (Nodes node : nodeList) {//Find instance of neighbors in nodelist
                            if (node.getX() == adjp.getCentroid().getX() && node.getY() == adjp.getCentroid().getY()) {
                                adjList.add(node);
                            }
                        }
                    }
                }
            }
            n.setAdjacent(new ArrayList<>(adjList));
        }
        System.out.println("finished initialization ");
        //finished pass

        Graph a = new Graph(nodeList);
        PathFinder pf = new DijkstraShortestPath(a);
       List<Edges> ouPut= pf.find(nodeList.get(3),nodeList.get(900));

        for (Edges e: ouPut){
            SegmentADT s =meshADT1.getSegment(meshADT1.getVertex(e.getStart().getX(),e.getStart().getY()),meshADT1.getVertex(e.getEnd().getX(),e.getEnd().getY()));
            s.setColor(new int[]{123, 183, 64});
            s.setThickness(10);
        }//draw result

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
