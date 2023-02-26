package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.*;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.locationtech.jts.algorithm.ConvexHull;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.triangulate.DelaunayTriangulationBuilder;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;

import static org.locationtech.jts.algorithm.Centroid.getCentroid;

public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int square_size = 20;

//    public Structs.Mesh generate() {
//        Mesh2 step2 = new Mesh2();
//        List<Point>vertices = new ArrayList<>();
//        for (int x = 0; x < width; x += square_size) {
//            for (int y = 0; y < height; y += square_size) {
//                Point p1 = new Point(x, y);
//                Point p2 = new Point(x, y + square_size);
//                Point p3 = new Point(x + square_size, y + square_size);
//                Point p4 = new Point(x + square_size, y);
//                vertices.add(p1);
//                vertices.add(p2);
//                vertices.add(p3);
//                vertices.add(p4);
//                PolygonADT polygon = new PolygonADT(vertices);
//                for (Point p : polygon.getVertices()) {
//                    Random bag = new Random();
//                    int red = bag.nextInt(255);
//                    int green = bag.nextInt(255);
//                    int blue = bag.nextInt(255);
//                    float alpha = bag.nextFloat(1);
//                    String colorCode = red + "," + green + "," + blue + "," + alpha;
//                    Color.setColor(p,colorCode);
//                }
//                for (Segment s : polygon.getSegments()) {
//                    Color.setColor(s);
//                }
//                step2.addPolygon(polygon);
//                vertices.clear();
//            }
//        }
//
//
//        return Mesh.newBuilder().addAllVertices(v_list).addAllSegments(segments).build();
//    }
//    private int findVertex(List<Vertex> vertexList,double x,double y){
//        int i=0;
//        for (Vertex v:vertexList){
//            if(v.getX()==x&&v.getY()==y){
//                return i;
//            }
//            i++;
//        }
//        return -1;
//    }
public Mesh generate() {
    final int MIN_COORDINATE = 0;
    final int MAX_COORDINATE = 500;
    final int NUM_POINTS = 100;
    int numRelaxations = 200;
    Mesh2 mesh = new Mesh2();
    Random random = new Random();

    List<Coordinate> points = new ArrayList<>(NUM_POINTS);
    for (int i = 0; i < NUM_POINTS; i++) {
        double x = MIN_COORDINATE + random.nextDouble() * (MAX_COORDINATE - MIN_COORDINATE);
        double y = MIN_COORDINATE + random.nextDouble() * (MAX_COORDINATE - MIN_COORDINATE);
        points.add(new Coordinate(x, y));
    }

    VoronoiDiagramBuilder builder = new VoronoiDiagramBuilder();
    builder.setSites(points);
    PrecisionModel precisionModel = new PrecisionModel(0.01);

    GeometryFactory geometryFactory = new GeometryFactory(precisionModel);
    Geometry diagram = builder.getDiagram(geometryFactory);
    Coordinate[] centroids = new Coordinate[points.size()];
    for (int i = 0; i < numRelaxations; i++) {
        for (int j = 0; j < points.size(); j++) {
            centroids[j] = getCentroid(diagram.getGeometryN(j));
        }

        builder.setSites(Arrays.asList(centroids));
        diagram = builder.getDiagram(geometryFactory);
    }


    for (int i = 0; i < diagram.getNumGeometries(); i++) {

        Geometry polygon = diagram.getGeometryN(i);
        ConvexHull convexHull = new ConvexHull(polygon);
        Geometry hull = convexHull.getConvexHull();
        Coordinate[] hullCoords = hull.getCoordinates();
        Geometry reorderedDiagram = geometryFactory.createPolygon(hullCoords);
        Envelope envelope = new Envelope(0, width, 0, height);

        Geometry croppedDiagram = reorderedDiagram.intersection(geometryFactory.toGeometry(envelope));

        List<VertexADT> vertices = null;
        for (int j=1;j<croppedDiagram.getCoordinates().length;j++){
            Coordinate c_1 = croppedDiagram.getCoordinates()[j-1];
            Coordinate c_2 = croppedDiagram.getCoordinates()[j];
            Coordinate centroid = getCentroid(croppedDiagram);


            VertexADT a=new VertexADT(c_1.x,c_1.y);
            VertexADT b =new VertexADT(c_2.x,c_2.y);
            vertices.add(a);
            vertices.add(b);
            mesh.addVertex(a);
            mesh.addVertex(b);
            mesh.addVertex(new VertexADT(centroid.x,centroid.y));
//            mesh.addSegment(new mesh.getSegment(a,b););


//                    public PolygonADT(List<VertexADT> vertices) {
//                this.vertices = new ArrayList<>(vertices);
//                this.segments = new ArrayList<>();
//                this.centroid = new VertexADT(0,0);
//                this.neighbors = new ArrayList<>();
//                for (int i = 0; i < vertices.size(); i++) {
//                    VertexADT start = vertices.get(i);
//                    VertexADT end = vertices.get((i + 1) % vertices.size());
//                    Segment segment = new Segment(start, end);
//                    segments.add(segment);
//
//            }


            mesh.addSegment(new Segment(a,b));

        }
        PolygonADT a = new PolygonADT(vertices);
        mesh.addPolygon(a);

    }
    //https://code-with-me.global.jetbrains.com/7QReUBUN_KcRKu6LbcuUtA#p=IU&fp=AD3A4FF761491603226549EAB97CF974C646E8200F4028D29293D6628C9A54AF


    DelaunayTriangulationBuilder delaunayTriangulationBuilder = new DelaunayTriangulationBuilder();



    List<Coordinate> centroidList = new ArrayList<>();
    for (int i = 0; i < centroids.length; i++) {
        centroidList.add(centroids[i]);
    }
    delaunayTriangulationBuilder.setSites(centroidList);

    GeometryCollection triangles = (GeometryCollection) delaunayTriangulationBuilder.getTriangles(geometryFactory);

    Map<PolygonADT, Set<PolygonADT>> neighbours = new HashMap<>();

    for (VertexADT v:mesh.getVertices()){
        System.out.println(v.getX() +"  "+v.getY());
        System.out.println("!");

    }

    return mesh.transform();
}


}
