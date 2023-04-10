package Graph;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/4/5 20:34
 */
public class Edges {

    private final Nodes start;
    private final Nodes end;
    private double lengthEdge;
    public Edges(Nodes start, Nodes end) {
        this.start = start;
        this.end = end;
        findLength();
    }

    public Nodes getStart() {
        return start;
    }

    public Nodes getEnd() {
        return end;
    }

    public double getLengthEdge() {
        return lengthEdge;
    }

    private void findLength() {
        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();

        this.lengthEdge = Math.sqrt(Math.pow(Math.abs(x1 - y1), 2) + Math.pow(Math.abs(x2 - y2), 2));
    }


}
