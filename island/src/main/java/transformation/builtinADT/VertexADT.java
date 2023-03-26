package transformation.builtinADT;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;

public class VertexADT {
    private ArrayList<VertexADT> vertices = new ArrayList<>();
    private ArrayList<SegmentADT> segments = new ArrayList<>();
    private ArrayList<PolygonADT> polygons = new ArrayList<>();
    final int id;
    private int[] color = new int[]{0, 0, 0};
    private double elevation = 0;
    private double x;
    private double y;

    private boolean isCentroid = true;

    private boolean aroundWater = true;

    public VertexADT(double x, double y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public double getElevation() {
        for (var p: this.polygons){
            this.elevation += p.getElevation();
        }
        return elevation;
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
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
        return color[0] + "，" + color[1] + "," + color[2];
    }

    public int getId() {
        return id;
    }

    public Structs.Vertex toVertex() {
        Structs.Vertex.Builder builder = Structs.Vertex.newBuilder();
        builder.setX(x).setY(y);
//        Structs.Property p = Structs.Property.newBuilder()
//                .setKey("rgb_color")
//                .setValue(getColorCode())
//                .build();
//        builder.addProperties(p);
        Structs.Property.Builder propertyBuilder = Structs.Property.newBuilder();
        builder.addProperties(propertyBuilder.setKey("rgb_color").setValue(getColorCode()));
        builder.addProperties(propertyBuilder.setKey("elevation").setValue(String.valueOf(elevation)));
        return builder.build();
    }

    public ArrayList<SegmentADT> getSegments() {
        return segments;
    }

    public void addSegments(SegmentADT segments) {
        this.segments.add(segments);
        if (segments.getStart()!=this){
            this.vertices.add(segments.getStart());
        }
        if (segments.getEnd()!=this){
            this.vertices.add(segments.getEnd());
        }
    }

    public ArrayList<PolygonADT> getPolygons() {
        return polygons;
    }

    public void addPolygons(PolygonADT polygonADT) {
        this.polygons.add(polygonADT);
    }


    public boolean isCentroid() {
        return isCentroid;
    }

    public void setCentroid(boolean centroid) {
        isCentroid = centroid;
    }

    public ArrayList<VertexADT> getVertices() {
        return vertices;
    }

    public boolean isAroundWater() {
        return aroundWater;
    }

    public void setAroundWater(boolean aroundWater) {
        this.aroundWater = aroundWater;
    }
}
