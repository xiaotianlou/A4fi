package Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/4/5 20:33
 */
public class Graph {

    private List<Nodes> nodesList;
    private List<Edges> edgesList;

    public Graph(List<Nodes> nodesList) {
        this.nodesList = nodesList;
//        Set<Edges> edgeSet = new HashSet<Edges>();

        for (Nodes nod : nodesList) {
            for (Nodes adj : nod.getAdjacent()) {
                edgesList.add(new Edges(nod, adj));
            }
        }


//找x开始或者结尾的边 然后拿出
    }

    public List<Nodes> getNodesList() {
        return nodesList;
    }

    public List<Edges> getEdgesList() {
        return edgesList;
    }


}
