package Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/4/5 20:33
 */
public class Nodes {

   private int x;
   private int y;

   List<Nodes> adjacent;

   public Nodes(int x, int y, List<Nodes> adjacent) {
      this.x = x;
      this.y = y;
      this.adjacent = adjacent;
   }

   public int getX() {
      return x;

   }

   public int getY() {
      return y;
   }










}
