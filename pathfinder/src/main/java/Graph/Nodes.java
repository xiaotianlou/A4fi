package Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/4/5 20:33
 */
public class Nodes {//重写equal

    private final double x;
    private final double y;
    private final int elevation;

    public void setIsland(boolean island) {
        isIsland = island;
    }

    public Nodes(double x, double y, int elevation, boolean isIsland) {
        this.x = x;
        this.y = y;
        this.elevation = elevation;
        this.isIsland = isIsland;
    }

    public boolean isIsland() {
        return isIsland;
    }

    private boolean isIsland=true;

    private List<Nodes> adjacent = new ArrayList<>();
    private double shortDistance = 1000000;//shortDistance from start node
    private String name;
    private Nodes prNodes = null;
    private boolean isVisited = false;

    public Nodes getPrNodes() {
        return prNodes;
    }

    public void setPrNodes(Nodes prNodes) {
        this.prNodes = prNodes;
    }

    public double getShortDistance() {
        return shortDistance;
    }

    public void tryUpdateShortDistance(double shortDistance, Nodes preNode) {
        if (shortDistance < this.shortDistance) {
            this.shortDistance = shortDistance;
            this.prNodes = preNode;
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

    public void setAdjacent(List<Nodes> adjacent) {
        this.adjacent = adjacent;
    }

    public double getX() {
        return x;

    }

    public double getY() {
        return y;
    }


}
