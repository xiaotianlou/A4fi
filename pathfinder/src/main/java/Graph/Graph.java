package Graph;

import java.util.ArrayList;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/4/5 20:33
 */
public class Graph {

    private ArrayList<Nodes> nodesList;
    private ArrayList<Edges> edgesList;

    public Graph(ArrayList<Nodes> nodesList) {
        this.nodesList = nodesList;
//        Set<Edges> edgeSet = new HashSet<Edges>();

        for (Nodes nod : nodesList) {
            for (Nodes adj : nod.getAdjacent()) {
                edgesList.add(new Edges(nod, adj));
            }
        }


//找x开始或者结尾的边 然后拿出
    }

    public ArrayList<Nodes> getNodesList() {
        return nodesList;
    }

    public ArrayList<Edges> getEdgesList() {
        return edgesList;
    }


}
