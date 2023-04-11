import Graph.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/4/5 22:32
 */
public class DijkstraShortestPath extends ShortestPath{


    public DijkstraShortestPath(Graph g) {
        super(g);
    }

    public List<Edges> findUnmarkedEdges(Nodes start){
        List<Edges> unmarkedEdges = new ArrayList<Edges>();
        for (Edges e:edgeList){
            if(e.getStart()==start&&(!e.getEnd().isVisited())){
                unmarkedEdges.add(e);
            }
        }
        return unmarkedEdges;
    }
    public void updateShortestDistance(List<Edges> toBeSearched,Nodes preNode){
        for (Edges e: toBeSearched){
            Nodes target = e.getEnd();
            target.tryUpdateShortDistance(preNode.getShortDistance()+e.getLengthEdge(),preNode);

        }
    }
    final Nodes FarthestNodes=new Nodes(0,0,0);;
    {
        System.out.println("初始化最远节点");
        FarthestNodes.tryUpdateShortDistance(Double.MAX_VALUE,new Nodes(0,0,0));
    }


    public Nodes visitClosetOne(){
        Nodes shortestNode =FarthestNodes;
        for (Nodes n:nodesList){
        if(!n.isVisited()&&n.getShortDistance()<shortestNode.getShortDistance()){
            shortestNode=n;
        }
        }
        shortestNode.visit();
        return shortestNode;
    }


    @Override
    public void find(Nodes start, Nodes end) {

//        for(Nodes node:nodesList){
//            if (node.getX()==start.getX()){
//
//            }
//
//        }


        start.tryUpdateShortDistance(0,null);//Initialize source node
        start.visit();

        Nodes newVisitedNode=start;
        List<Edges> toBeSearched;
        while (!end.isVisited()){
            toBeSearched=findUnmarkedEdges(newVisitedNode);
            updateShortestDistance(toBeSearched,newVisitedNode);
            newVisitedNode=visitClosetOne();
        }

        //返回路径
        Nodes tail=end;
        while(tail!=null){
            for (Edges e:edgeList){
                if(e.getEnd()==tail&&e.getStart()==tail.getPrNodes()){
                    outPutEdge.add(e);
                }
            }
            tail=end.getPrNodes();
        }
    }
}
