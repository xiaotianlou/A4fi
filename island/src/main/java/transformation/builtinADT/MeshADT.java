package transformation.builtinADT;

import TerrainFeatures.Humidity;
import TerrainFeatures.Soil;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import transformation.importation.Importer;
import transformation.importation.polygonImporter;
import transformation.importation.segmentImporter;
import transformation.importation.vertexImporter;

import java.util.ArrayList;
import java.util.List;

public class MeshADT {
    final ArrayList<PolygonADT> polygons = new ArrayList<>();
    final private ArrayList<VertexADT> vertices = new ArrayList<>();
    final private ArrayList<SegmentADT> segments = new ArrayList<>();

    public VertexADT getVertex(double x, double y) {
        for (VertexADT v : vertices) {
            if (v.getX() == x && v.getY() == y) {
                return v;
            }
        }
        VertexADT v = new VertexADT(x, y, vertices.size());
        vertices.add(v);
        return v;
    }

    public SegmentADT getSegment(VertexADT start, VertexADT end) {
        for (SegmentADT s : segments) {
            if (
                    ((start == s.getStart() && end == s.getEnd()) ||
                            (start == s.getEnd() && end == s.getStart()))
            ) {
                return s;
            }
        }
        SegmentADT s = new SegmentADT(start, end, segments.size());
        segments.add(s);
        return s;
    }

    public ArrayList<VertexADT> getVertices() {
        return vertices;
    }

    public ArrayList<SegmentADT> getSegments() {
        return segments;
    }

    public ArrayList<PolygonADT> getPolygons() {
        return polygons;
    }

    public PolygonADT getPolygon(List<VertexADT> vertices, List<SegmentADT> segments, VertexADT centroid) {
        for (PolygonADT p : polygons) {
            if (p.equals(vertices, segments)) {
                return p;
            }
        }
        PolygonADT p = new PolygonADT(segments, vertices, centroid, polygons.size());
        polygons.add(p);
        return p;
    }

    public Structs.Mesh toMesh() {
        Structs.Mesh.Builder builder = Structs.Mesh.newBuilder();
        for (VertexADT v : vertices) {
            builder.addVertices(v.toVertex());
        }
        for (SegmentADT s : segments) {
            builder.addSegments(s.toSegment());
        }
        for (PolygonADT p : polygons) {
            builder.addPolygons(p.toPolygon());
        }
        return builder.build();
    }
    public MeshADT readInputMesh(Structs.Mesh aMesh) {

        Importer polygonImporter = new polygonImporter();
        Importer segmentImporter = new segmentImporter();
        Importer vertexImporter = new vertexImporter();
        vertexImporter.read(aMesh, this);
        segmentImporter.read(aMesh, this);
        polygonImporter.read(aMesh, this);


        return this;
    }
    public void humidityInitialization(){
        for (var p:this.getPolygons()){
            if (p.isIsland()){
                Humidity.humiditySum(p);
            }
        }
    }
    public void soilInitialization(){
        for(var p:this.getPolygons()){
            if (!p.isIsland()){
                Soil.waterAbsorbing(p);
            }
        }
        for (var p:this.getPolygons()){
            Soil.humidityInfluence(p);
        }
    }

    public void calInfo(){
        humidityInitialization();
        soilInitialization();

    }


}
