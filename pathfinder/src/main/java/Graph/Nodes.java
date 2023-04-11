package Graph;

import java.util.List;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/4/5 20:33
 */
public class Nodes {

    private final double x;
    private final double y;
    private final int elevation;
    private final List<Nodes> adjacent;
    private double shortDistance = 1000000;//shortDistance from start node
    private String name;
    private Nodes prNodes = null;
    private boolean isVisited = false;

    public Nodes(int x, int y, int elevation, List<Nodes> adjacent) {

        this.x = x;
        this.y = y;
        this.elevation = elevation;
        this.adjacent = adjacent;
    }

    public Nodes getPrNodes() {
        return prNodes;
    }

    public void setPrNodes(Nodes prNodes) {
        this.prNodes = prNodes;
    }

    public double getShortDistance() {
        return shortDistance;
    }

    public void tryUpdateShortDistance(double shortDistance,Nodes preNode) {
        if (shortDistance < this.shortDistance) {
            this.shortDistance = shortDistance;
            this.prNodes=preNode;
        }
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void visit() {
        isVisited = true;
    }

    public int getElevation() {
        return elevation;
    }

    public List<Nodes> getAdjacent() {
        return adjacent;
    }

    public double getX() {
        return x;

    }

    public double getY() {
        return y;
    }


}
