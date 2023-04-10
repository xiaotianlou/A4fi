package Graph;

import java.util.List;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/4/5 20:33
 */
public class Nodes {

    List<Nodes> adjacent;
    private int x;
    private int y;
    private int elevation;

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

    public int getX() {
        return x;

    }

    public int getY() {
        return y;
    }


}
