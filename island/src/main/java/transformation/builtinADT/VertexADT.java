package transformation.builtinADT;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.List;

public class VertexADT {
    final ArrayList<SegmentADT> segments = new ArrayList<>();

    final ArrayList<SegmentADT> polygon = new ArrayList<>();
    private int[] color=new int[]{0, 0, 0};
    private double elevation=0;
    private double x;
    private double y;
    final int id;

    public VertexADT(double x, double y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setColor(int[] color) {
        this.color = color;
    }

    public void setColor(String c){
        int n = 0;
        for (String s:c.split(",")){
            color[n] = Integer.parseInt(s);
            n++;
        }
    }

    public void setElevation(int elevation){
        this.elevation = elevation;
    }

    public double getElevation(){
        return elevation;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int[] getColor() {
        return color;
    }

    public String getColorCode() {
        return color[0]+"，"+color[1]+","+color[2];
    }

    public int getId() { return id; }

    public Structs.Vertex toVertex(){
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

}
