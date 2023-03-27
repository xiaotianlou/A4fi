package transformation.builtinADT;

import TerrainFeatures.Aquifers;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.List;

public class PolygonADT {
    final int id;
    //    private final MeshADT mesh;
    private int[] color = new int[]{125, 125, 125};
    private List<PolygonADT> polygons;
    private List<SegmentADT> segments;
    private List<VertexADT> vertices;
    private VertexADT centroid;
    private int humidity = 150;
    private final InfoSet infoSet = new InfoSet();

    public PolygonADT(List<SegmentADT> segments, List<VertexADT> vertices, VertexADT centroid, int id) {
        this.segments = segments;
        this.vertices = vertices;
        this.id = id;
        this.centroid = centroid;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public InfoSet getInfoSet() {
        return infoSet;
    }

    public Biome getBiome() {
        return infoSet.getBiome();
    }

    public void setBiome(Biome biome) {
        infoSet.setBiome(biome);
    }

    public int getId() {
        return id;
    }

    public boolean equals(List<VertexADT> vertices, List<SegmentADT> segments) {
        if (this.vertices.size() != vertices.size() || this.segments.size() != segments.size()) {
            return false;
        }
        for (int n = 0; n < vertices.size(); n++) {
            if (vertices.get(n) != this.vertices.get(n)) {
                return false;
            }
        }
        for (int n = 0; n < segments.size(); n++) {
            if (segments.get(n) != this.segments.get(n)) {
                return false;
            }
        }
        return true;
    }

    public int[] getColor() {
        return infoSet.getColor();
    }

    public void setColor(int[] color) {
        infoSet.setColor(color);
    }

    public void setColor(String c) {
        infoSet.setColor(c);
    }

    public String getColorCode() {

        int[] color = infoSet.getColor();
        return color[0] + "," + color[1] + "," + color[2];
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
        return infoSet.getElevation();
    }

    public void setElevation(int elevation) {
        infoSet.setElevation(elevation);
    }

    public boolean isIsland() {
        return infoSet.isIsland();
    }

    public void setIsland(boolean island) {
        infoSet.setIsland(island) ;
    }

    public int getWaterContent() {
        return infoSet.getWaterContent();
    }

    public void setWaterContent(int waterContent) {
        infoSet.setWaterContent(waterContent);
    }

    public int getTemperature() {
        return infoSet.getTemperature();
    }

    public void setTemperature(int temperature) {
        infoSet.setTemperature(temperature);
    }

    public List<PolygonADT> getPolygons() {
        return polygons;
    }

    public void setPolygons(List<PolygonADT> polygons) {
        this.polygons = polygons;
    }

    public List<SegmentADT> getSegments() {
        return segments;
    }

    public void setSegments(List<SegmentADT> segments) {
        this.segments = segments;
    }
    public static int calculateHumidity(int waterContent, int distance) {
        return (int) (100 * waterContent / (waterContent + 0.05 * distance));
    }

    public Structs.Polygon toPolygon() {
        Structs.Polygon.Builder builder = Structs.Polygon.newBuilder();
        List<Integer> neighbours = new ArrayList<>();
        for (SegmentADT s : segments) {
            builder.addSegmentIdxs(s.getId());
        }
        for (PolygonADT neighbourPolygon : polygons) {
            builder.addNeighborIdxs(neighbourPolygon.getId());
        }
        Structs.Property.Builder propertyBuilder = Structs.Property.newBuilder();

        builder.addProperties(propertyBuilder.setKey("rgb_color").setValue(getColorCode()));


        builder.addProperties(propertyBuilder.setKey("elevation").setValue(String.valueOf(getElevation())));

        builder.addProperties(propertyBuilder.setKey("temperature").setValue(getTemperature() + ""));

        builder.addProperties(propertyBuilder.setKey("waterContent").setValue(getWaterContent() + ""));

        builder.setCentroidIdx(centroid.id);

        builder.addAllNeighborIdxs(neighbours);

        return builder.build();
    }

}
