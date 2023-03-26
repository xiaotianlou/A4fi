package transformation.builtinADT;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;

public class SegmentADT {
    final ArrayList<PolygonADT> polygons = new ArrayList<>();
    private VertexADT start;
    private VertexADT end;
    private int[] color = new int[]{0,0,0};

    private int thickness;
    final int id;

    public SegmentADT(VertexADT start, VertexADT end, int id) {
        this.start = start;
        this.end = end;
        this.id = id;
    }

    public VertexADT getStart() {
        return start;
    }

    public VertexADT getEnd() {
        return end;
    }

    public int getId() {
        return id;
    }

    public void setColor(int[] color) {
        this.color = color;
    }

    public void setColor(String c) {
        int n = 0;
        for (String s:c.split(",")){
            color[n] = Integer.parseInt(s);
            n++;
        }
    }


    public int[] getColor() {
        return color;
    }

    private String getColorCode() {
        return color[0]+"，"+color[1]+","+color[2];
    }


    public Structs.Segment toSegment(){
        Structs.Segment s = Structs.Segment.newBuilder().setV1Idx(start.getId()).setV2Idx(end.getId()).build();
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(getColorCode()).build();
        Structs.Segment colored_s = Structs.Segment.newBuilder(s).addProperties(color).build();
        return colored_s;
    }
}
