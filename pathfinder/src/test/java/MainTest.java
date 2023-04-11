import Graph.Nodes;
import org.junit.jupiter.api.Test;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/4/5 12:03
 */
public class MainTest {
    @Test
    void main() {
        Nodes tail=new Nodes(1,1,1,null);
        tail=null;
        if (tail==null){
            System.out.println("1");
        }
        else {

            System.out.println("2");
        }
    }

}
