package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.math.BigDecimal;
import java.util.*;

public class Mesh2 {
    private List<Point> vertices;
    private List<Segment> segments;
    private List<Polygon> polygons;
    private int scale = 4;

    public Mesh2() {
        vertices = new ArrayList<>();
        segments = new ArrayList<>();
        polygons = new ArrayList<>();
    }

    public Point addVertex(Point point) {
        for(Point p : this.vertices){
            if (p.getX() == point.getX() && p.getY()==point.getY()){
                point = p;
                return p;
            }
        }
        double x = point.getX();
        double y = point.getY();
        BigDecimal bdX = new BigDecimal(x);
        BigDecimal bdY = new BigDecimal(y);
        x = bdX.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        y = bdY.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        point.setX(x);
        point.setY(y);
        point.setId(vertices.size());
        Color.setColor(point);
        vertices.add(point);
        return point;
    }

    public Segment addSegment(Segment segment) {
        for (Segment s: this.segments){
            if ((segment.getStart().equals(s.getStart()) && segment.getEnd().equals(s.getEnd())) || (segment.getStart().equals(s.getEnd()) && segment.getEnd().equals(s.getStart()))) {
                for (int id : segment.getUsedBy()){
                    s.addUsedBy(id);
                }
                for (int id : s.getUsedBy()){
                    segment.addUsedBy(id);
                }
                segment.setId(s.getId());
                return s;
            }
        }
        segment.setId(segments.size());
        segments.add(segment);

        this.addVertex(segment.getStart());
        this.addVertex(segment.getEnd());
        return segment;
    }

    public Polygon addPolygon(Polygon polygon) {
        for (Polygon p:this.polygons){
            if (p.getVertices() == polygon.getVertices()){
                polygon.setId(p.getId());
                return p;
            }
        }
        polygon.setId(polygons.size());
        polygons.add(polygon);
        this.addVertex(polygon.getCentroid());
        for (Point p: polygon.getVertices()) {
            this.addVertex(p);
        }
        for (Segment s : polygon.getSegments()) {
            s.addUsedBy(polygon.getId());
            this.addSegment(s);
        }
        return polygon;

    }

    public List<Point> getVertices() {
        return vertices;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public List<Polygon> getPolygons() {
        return polygons;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mesh2:\n");
        sb.append("Vertices:\n");
        for (Point vertex : vertices) {
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
        for (Point p : this.vertices) {
            Structs.Vertex v = Structs.Vertex.newBuilder().setX(p.getX()).setY(p.getY()).build();
            Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(p.getColor().getColorCode()).build();
            Structs.Vertex colored = Structs.Vertex.newBuilder(v).addProperties(color).build();
            v_list.add(colored);
        }


        List<Structs.Segment> s_list = new ArrayList<>();
        for (Segment s : this.segments) {
            Structs.Segment seg = Structs.Segment.newBuilder().setV1Idx(s.getStart().getId()).setV2Idx(s.getEnd().getId()).build();
//            Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(s.getColor().getColorCode()).build();
//            Structs.Segment colored = Structs.Segment.newBuilder(seg).addProperties(color).build();
//            s_list.add(colored);
        }

        List<Structs.Polygon> p_list = new ArrayList<>();
        for (Polygon p : this.polygons) {
            Structs.Polygon poly = Structs.Polygon.newBuilder().setCentroidIdx(p.getCentroid().getId()).build();
            for (Segment s : p.getSegments()) {
                System.out.println(s.getId());
                poly.newBuilder().addSegmentIdxs(s.getId()).build();
                for (int neighbour : s.getUsedBy()){
                    if (neighbour != p.getId()){
                        p.addNeighbor(neighbour);
                    }
                }
                poly.newBuilder().addAllNeighborIdxs(p.getNeighbors());
            }
            System.out.println(p.getNeighbors());
            p_list.add(poly);
        }
        return Structs.Mesh.newBuilder().addAllVertices(v_list).addAllSegments(s_list).addAllPolygons(p_list).build();
    }
    public int findPolygonId(Polygon polygon){
        for (Polygon p : this.polygons){
            if (p.getVertices() == polygon.getVertices()){
                return p.getId();
            }
        }
        return -1;
    }
}