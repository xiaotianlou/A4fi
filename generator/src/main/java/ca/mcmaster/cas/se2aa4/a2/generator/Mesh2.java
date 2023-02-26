package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import java.math.BigDecimal;
import java.util.*;

public class Mesh2 {
    private List<VertexADT> vertices;
    private List<Segment> segments;
    private List<PolygonADT> polygonADTS;
    private int scale = 2;

    public Mesh2() {
        vertices = new ArrayList<>();
        segments = new ArrayList<>();
        polygonADTS = new ArrayList<>();
    }

    public void addVertex(VertexADT vertexADT) {
        for(VertexADT p : this.vertices){
            if (p.getX()-vertexADT.getX()>=-0.1&&p.getX()-vertexADT.getX()<=0.1 && p.getY()-vertexADT.getY()<=0.1&&p.getY()-vertexADT.getY()>=-0.1){
                return ;
            }
        }
        double x = vertexADT.getX();
        double y = vertexADT.getY();
        BigDecimal bdX = new BigDecimal(x);
        BigDecimal bdY = new BigDecimal(y);
        x = bdX.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        y = bdY.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        vertexADT.setX(x);
        vertexADT.setY(y);
        vertexADT.setId(vertices.size());
        vertices.add(vertexADT);
    }

    public void addSegment(Segment segment) {
        for (Segment s: this.segments){
            if ((segment.getStart().equals(s.getStart()) && segment.getEnd().equals(s.getEnd())) || (segment.getStart().equals(s.getEnd()) && segment.getEnd().equals(s.getStart()))) {
                for (int id : segment.getUsedBy()){
                    s.addUsedBy(id);
                }
                return;
            }
        }
        segment.setId(segments.size());
        segments.add(segment);
        this.addVertex(segment.getStart());
        this.addVertex(segment.getEnd());
    }

    public void addPolygon(PolygonADT polygonADT) {
        for (PolygonADT p:this.polygonADTS){
            if (p.getVertices() == polygonADT.getVertices()){
                return ;
            }
        }
        polygonADT.setId(polygonADTS.size());
        polygonADTS.add(polygonADT);
        this.addVertex(polygonADT.getCentroid());
        for (VertexADT p: polygonADT.getVertices()) {
            this.addVertex(p);
        }
        for (Segment s : polygonADT.getSegments()) {
            s.addUsedBy(polygonADT.getId());
            this.addSegment(s);
        }

    }

    public List<VertexADT> getVertices() {
        return vertices;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public List<PolygonADT> getPolygons() {
        return polygonADTS;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mesh2:\n");
        sb.append("Vertices:\n");
        for (VertexADT vertex : vertices) {
            sb.append(vertex + "\n");
        }
        sb.append("Segments:\n");
        for (Segment segment : segments) {
            sb.append(segment + "\n");
        }
        return sb.toString();
    }

    public Structs.Mesh transform() {
        List<Structs.Vertex> v_list = new ArrayList<>();
        for (VertexADT p : this.vertices) {
            Structs.Vertex v = Structs.Vertex.newBuilder().setX(p.getX()).setY(p.getY()).build();

            Structs.Property thickness = Structs.Property.newBuilder().setKey("thickness").setValue(p.getThickness()).build();
            Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(p.getColor().getColorCode()).build();

            Structs.Vertex colored = Structs.Vertex.newBuilder(v).addProperties(color).addProperties(thickness).build();
            v_list.add(colored);
        }


        List<Structs.Segment> s_list = new ArrayList<>();
        for (Segment s : this.segments) {
            Structs.Segment seg = Structs.Segment.newBuilder().setV1Idx(getPoint(s.getStart()).getId()).setV2Idx(getPoint(s.getEnd()).getId()).build();
            Structs.Property thickness = Structs.Property.newBuilder().setKey("thickness").setValue(s.getThickness()).build();
            Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(getSegment(s).getColor().getColorCode()).build();
            Structs.Segment colored = Structs.Segment.newBuilder(seg).addProperties(color).addProperties(thickness).build();
            s_list.add(colored);
        }

        List<Structs.Polygon> p_list = new ArrayList<>();
        for (PolygonADT p : this.polygonADTS) {
            Structs.Polygon.Builder builder = Structs.Polygon.newBuilder();
            builder.setCentroidIdx(getPoint(p.getCentroid()).getId()).build();
            for (Segment s : p.getSegments()) {
                builder.addSegmentIdxs(getSegment(s).getId()).build();
                for (int neighbour : getSegment(s).getUsedBy()){
                    if (neighbour != getPolygon(p).getId()){
                        p.addNeighbor(neighbour);
                    }
                }
            }
            builder.addAllNeighborIdxs(p.getNeighbors());
            Structs.Polygon poly = builder.build();
            p_list.add(poly);
        }
        //neighbor infomation




        return Structs.Mesh.newBuilder().addAllVertices(v_list).addAllSegments(s_list).addAllPolygons(p_list).build();
    }

    public VertexADT getPoint(VertexADT vertexADT){
        for(VertexADT p : this.vertices){
            if (p.getX() == vertexADT.getX() && p.getY()== vertexADT.getY()){
                return p;
            }
        }
        return vertexADT;
    }
    public Segment getSegment(Segment segment){
        for (Segment s: this.segments){
            if ((segment.getStart().equals(s.getStart()) && segment.getEnd().equals(s.getEnd())) || (segment.getStart().equals(s.getEnd()) && segment.getEnd().equals(s.getStart()))) {
                return s;
            }
        }
        return segment;
    }
    public PolygonADT getPolygon(PolygonADT polygonADT){
        for (PolygonADT p:this.polygonADTS){
            if (p.getVertices() == polygonADT.getVertices()){
                return p;
            }
        }
        return polygonADT;
    }
}