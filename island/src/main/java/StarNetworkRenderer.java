import Graph.Edges;
import Graph.Graph;
import Graph.Nodes;
import Reproducibility.Seed;
import featureRenderer.City.CityType;
import featureRenderer.Renderable;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;
import transformation.builtinADT.SegmentADT;
import transformation.builtinADT.VertexADT;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StarNetworkRenderer implements Renderable {

    private MeshADT mesh;
    private List<Nodes> nodeList=new ArrayList<>();
    private PathFinder pf;

    public void connectToPathFinder() {

        //Preparation parameter of mesh adt to pathfinder
        for (PolygonADT p : mesh.getPolygons()) {
            double cenx = p.getCentrVertex().getX();
            double ceny = p.getCentrVertex().getY();
            Nodes n = new Nodes(cenx, ceny, p.getElevation(), p.isIsland());
            //First put the nodes into the nodeslist, and then find the neighbors from the list xy
            nodeList.add(n);
        }
        System.out.println("adding Neighborhood");
        for (Nodes n : nodeList) {//Find n's neighbors from the list
            List<Nodes> adjList = new ArrayList<>();
            for (PolygonADT p : mesh.getPolygons()) {
                if (n.getX() == p.getCentrVertex().getX() && n.getY() == p.getCentrVertex().getY()) {//Find the poly corresponding to the node
                    for (PolygonADT adjp : p.getPolygons()) {//Find Poly Neighbors
                        for (Nodes node : nodeList) {//Find instance of neighbors in nodelist
                            if (node.getX() == adjp.getCentrVertex().getX() && node.getY() == adjp.getCentrVertex().getY()) {
                                adjList.add(node);
                            }
                        }
                    }
                }
            }
            n.setAdjacent(new ArrayList<>(adjList));
        }
        System.out.println("finished initialization ");

    }

    private void drawRoad(Nodes start, Nodes end) {
        List<Edges> ouPut = pf.find(start, end);
        for (Edges e : ouPut) {
            SegmentADT s = mesh.getSegment(mesh.getVertex(e.getStart().getX(), e.getStart().getY()), mesh.getVertex(e.getEnd().getX(), e.getEnd().getY()));
            s.setColor(new int[]{123, 183, 64});
            s.setThickness(10);
        }//draw result

    }

    @Override
    public MeshADT Rendering(MeshADT m, Seed seed) {
        this.mesh = m;
        connectToPathFinder();
        Nodes start=nodeList.get(0);
        LinkedList<Nodes> cityBag = new LinkedList<Nodes>();
        //finished pass
        Graph a = new Graph(nodeList);
        pf = new DijkstraShortestPath(a);

        for (VertexADT v : m.getVertices()) {

            if(v.getCityType()==null){continue;}

            if (v.getCityType() == CityType.Capital) {
                for (Nodes s : nodeList) {
                    if (s.getX() == v.getX() && s.getY() == v.getY()) {
                        start=s;
                    }
                }
            }else {
                for (Nodes s : nodeList) {
                    if (s.getX() == v.getX() && s.getY() == v.getY()) {
                        cityBag.add(s);
                    }
                }
            }


        }



        while (!cityBag.isEmpty()) {
            drawRoad(start, cityBag.pop());
        }
        return m;
    }
}
