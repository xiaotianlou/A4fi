package Graph;

import java.util.List;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/4/5 20:33
 */
public class Nodes {

    List<Nodes> adjacent;
    private double x;
    private double y;
    private int elevation;
    private String name;

    public boolean isMarked() {
        return isMarked;
    }

    public void Mark() {
        isMarked = true;
    }

    private boolean isMarked =false;


   public Nodes(int x, int y, int elevation, List<Nodes> adjacent) {

      this.x = x;
      this.y = y;
      this.elevation = elevation;
      this.adjacent = adjacent;
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
