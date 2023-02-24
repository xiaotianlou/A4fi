package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.math.BigDecimal;
import java.util.*;

public class Mesh2 {
    private List<Point> vertices;
    private List<Segment> segments;
    private List<Polygon> polygons;
    private int scale = 2;

    public Mesh2() {
        vertices = new ArrayList<>();
        segments = new ArrayList<>();
        polygons = new ArrayList<>();
    }

    public void addVertex(Point point) {
        double x = point.getX();
        double y = point.getY();
        BigDecimal bdX = new BigDecimal(x);
        BigDecimal bdY = new BigDecimal(y);
        x = bdX.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        y = bdY.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        point = new Point(x, y);
        point.setId(vertices.size());
        vertices.add(point);
    }

    public void addSegment(Segment segment) {
        Point start = segment.getStart();
        Point end = segment.getEnd();
        segment = new Segment(start,end);
        segment.setId(segments.size());
        segments.add(segment);
    }

    public void addPolygon(Polygon polygon) {
        List<Point> vertices = polygon.getVertices();
        polygon = new Polygon(vertices);
        polygon.setId(segments.size());
        polygons.add(polygon);
    }

    public void addCentroid() {
        Polygon polygon = new Polygon(vertices);
        polygons.add(polygon);
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

    public void removeDuplicates() {
        Set<Point> pointSet = new HashSet<>(vertices);
        Set<Segment> segmentSet = new HashSet<>(segments);
        Set<Polygon> polygonSet = new HashSet<>(polygons);
        vertices.clear();
        segments.clear();
        polygons.clear();
        vertices.addAll(pointSet);
        segments.addAll(segmentSet);
        polygons.addAll(polygonSet);
    }

    public Structs.Mesh transform() {
        List<Structs.Vertex> v_list = new ArrayList<>();
        for (Point p : this.getVertices()) {
            Structs.Vertex v = Structs.Vertex.newBuilder().setX(p.getX()).setY(p.getY()).build();
            Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(p.getColor()).build();
            Structs.Vertex colored = Structs.Vertex.newBuilder(v).addProperties(color).build();
            v_list.add(colored);
        }

        List<Structs.Segment> s_list = new ArrayList<>();
        for (Segment s : this.getSegments()) {
            Structs.Segment seg = Structs.Segment.newBuilder().setV1Idx(s.getStart().getId()).setV2Idx(s.getEnd().getId()).build();
            Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(s.getColor()).build();
            Structs.Segment colored = Structs.Segment.newBuilder(seg).addProperties(color).build();
            s_list.add(colored);
        }

        List<Structs.Polygon> p_list = new ArrayList<>();
        for (Polygon p : this.getPolygons()) {
            Structs.Polygon poly = Structs.Polygon.newBuilder().setCentroidIdx(p.getCentroid().getId()).build();
            for (Segment s : p.getSegments()) {
                poly.newBuilder().addSegmentIdxs(s.getId()).build();
            }
            for (int i : p.getNeighbors()){
                poly.newBuilder().addNeighborIdxs(i);
            }
            p_list.add(poly);
        }

        return Structs.Mesh.newBuilder().addAllVertices(v_list).addAllSegments(s_list).addAllPolygons(p_list).build();
        }
        private int findVertex (List < Structs.Vertex > vertexList,double x, double y){
            int i = 0;
            for (Structs.Vertex v : vertexList) {
                if (v.getX() == x && v.getY() == y) {
                    return i;
                }
                i++;
            }
            return -1;
        }
        private int findSegment (List < Structs.Segment > segmentlist,int x, int y){
            int i = 0;
            for (Structs.Segment s : segmentlist) {
                if (s.getV1Idx() == x && s.getV2Idx() == y) {
                    return i;
                }
                i++;
            }
            return -1;
        }

        private int findPolygon (List < Structs.Polygon > polygonlist,int x){
            int i = 0;
            for (Structs.Polygon p : polygonlist) {
                if (p.getCentroidIdx() == x) {
                    return i;
                }
                i++;
            }
            return -1;
        }
    }


