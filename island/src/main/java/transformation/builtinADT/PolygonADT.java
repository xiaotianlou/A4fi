package transformation.builtinADT;

import TerrainFeatures.Aquifer;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.HashSet;
import java.util.List;

public class PolygonADT {
//    private final MeshADT mesh;

    private List<PolygonADT> polygons;
    private List<SegmentADT> segments;
    private List<VertexADT> vertices;

    private VertexADT centroid;
    private int elevation =0;

    private boolean isIsland=false;

    private Aquifer waterContent;
    private int[] color;

    private int temperature=25;
    final int id;

    public PolygonADT(List<SegmentADT> segments, List<VertexADT> vertices, int id) {
//        this.mesh = mesh;
        this.segments = segments;
        this.vertices = vertices;
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public boolean equals(List<VertexADT> vertices, List<SegmentADT> segments) {
        if(this.vertices.size()!= vertices.size() ||this.segments.size()!=segments.size()){
            return false;
        }
        for (int n = 0; n< vertices.size(); n++){
            if (vertices.get(n) != this.vertices.get(n)){
                return false;
            }
        }
        for (int n=0;n<segments.size();n++){
            if (segments.get(n) !=this.segments.get(n)){
                return false;
            }
        }
        return true;
    }

    public void setPolygons(List<PolygonADT> polygons) {
        this.polygons = polygons;
    }

    public void setColor(int[] color) {
        this.color = color;
    }

    public int[] getColor() {
        return color;
    }

    public String getColorCode() {
        return "red"+color[0]+"，"+color[1]+","+color[2];
    }

    public List<VertexADT> getVertices() {
        return vertices;
    }

    public void setVertices(List<VertexADT> vertices) {
        this.vertices = vertices;
    }

    public VertexADT getCentroid() {
        return centroid;
    }

    public void setCentroid(VertexADT centroid) {
        this.centroid = centroid;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public boolean isIsland() {
        return isIsland;
    }

    public void setIsland(boolean island) {
        isIsland = island;
    }

    public Aquifer getWaterContent() {
        return waterContent;
    }

    public void setWaterContent(Aquifer waterContent) {
        this.waterContent = waterContent;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Structs.Polygon toPolygon(){
        Structs.Polygon.Builder builder = Structs.Polygon.newBuilder();
        HashSet<Integer> neighbours = new HashSet<>();
        for (SegmentADT s:segments){
            builder.addSegmentIdxs(s.getId());
            for (PolygonADT neighbourPolygon:s.polygons){
                if (neighbourPolygon != this) neighbours.add(neighbourPolygon.getId());
            }
        }
        Structs.Property.Builder propertyBuilder = Structs.Property.newBuilder();

//        builder.addProperties(propertyBuilder.setValue("rgb_color").setValue(getColorCode()));

        builder.addProperties(propertyBuilder.setValue("elevation").setValue(String.valueOf(elevation)));

        builder.addProperties(propertyBuilder.setValue("temperature").setValue(String.valueOf("0")));

        builder.addProperties(propertyBuilder.setValue("waterContent").setValue("0"));

//        builder.setCentroidIdx(centroid.id);

        builder.addAllNeighborIdxs(neighbours);

        return builder.build();
    }

}
