package transformation.builtinADT;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;

public class SegmentADT {
    final ArrayList<PolygonADT> polygons = new ArrayList<>();
    final int id;
    private final VertexADT start;
    private final VertexADT end;
    private int[] color = new int[]{125, 125, 125};
    private float thickness = 1;

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

    public int[] getColor() {
        return color;
    }

    public void setColor(int[] color) {
        this.color = color;
    }

    public void setColor(String c) {
        int n = 0;
        for (String s : c.split(",")) {
            color[n] = Integer.parseInt(s);
            n++;
        }
    }

    public String getColorCode() {
        return color[0] + "," + color[1] + "," + color[2];
    }


    public Structs.Segment toSegment() {
        Structs.Segment.Builder builder = Structs.Segment.newBuilder();
        builder.setV1Idx(start.getId()).setV2Idx(end.getId());
        Structs.Property.Builder propertyBuilder = Structs.Property.newBuilder();
        builder.addProperties(propertyBuilder.setKey("rgb_color").setValue(getColorCode()));
        builder.addProperties(propertyBuilder.setKey("thickness").setValue(String.valueOf(getThickness())));
        return builder.build();
    }

    public float getThickness() {
        return thickness;
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
    }
}
