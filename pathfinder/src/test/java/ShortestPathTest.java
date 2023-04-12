import Graph.Edges;
import Graph.Graph;
import Graph.Nodes;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/4/5 21:51
 */
class ShortestPathTest {

    @Test
    void completeTest() {
        //Note: In order to test the feasibility of finding the shortest path in a mesh environment while avoiding circular dependencies,
        //the complete test is available at island/src/test/java/shortestpathtest :)

    }

    @Test
    void basicTest() {

        List<Nodes> nodeList = new ArrayList<>();
        Nodes start = new Nodes(0, 0, 14,true);
        Nodes end = new Nodes(100, 0, 14,true);
        Nodes middle1 = new Nodes(50, 100, 14,true);
        Nodes middle2 = new Nodes(49, 50, 14,true);
        start.getAdjacent().add(middle1);
        start.getAdjacent().add(middle2);
        middle1.getAdjacent().add(end);
        middle2.getAdjacent().add(end);

        nodeList.add(start);
        nodeList.add(end);
        nodeList.add(middle1);
        nodeList.add(middle2);

        Graph a = new Graph(nodeList);
        PathFinder pf = new DijkstraShortestPath(a);
        List<Edges> result = pf.find(start, end);

        for (Edges temp : result) {
            System.out.print(temp.getEnd().getX()+"   ");
            System.out.println(temp.getEnd().getY());

            System.out.print(temp.getStart().getX()+"   ");
            System.out.println(temp.getStart().getY());
            System.out.println();
        }//test pass
    }


}