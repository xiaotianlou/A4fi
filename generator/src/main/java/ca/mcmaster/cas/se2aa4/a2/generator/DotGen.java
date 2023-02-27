package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.*;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.apache.commons.cli.*;
import org.locationtech.jts.algorithm.ConvexHull;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.triangulate.DelaunayTriangulationBuilder;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;

import static org.locationtech.jts.algorithm.Centroid.getCentroid;

public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int square_size = 20;

    private int findVertex(List<Structs.Vertex> vertexList, double x, double y){
        int i=0;
        for (Structs.Vertex v:vertexList){
            if(v.getX()==x&&v.getY()==y){
                return i;
            }
            i++;
        }
        return -1;
    }
    private Mesh step3_irregular(int num_poly,int levelRelax){
        final int MIN_COORDINATE = 0;
        final int MAX_COORDINATE = 500;
        final int NUM_POINTS = num_poly;
        int numRelaxations = levelRelax;
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

            List<VertexADT> vertices = new ArrayList<>();
            Coordinate centroid = null;
            for (int j = 1; j < croppedDiagram.getCoordinates().length; j++) {
                Coordinate c_1 = croppedDiagram.getCoordinates()[j - 1];
                Coordinate c_2 = croppedDiagram.getCoordinates()[j];
                centroid = getCentroid(croppedDiagram);


                VertexADT a = new VertexADT(c_1.x, c_1.y);
                VertexADT b = new VertexADT(c_2.x, c_2.y);
                vertices.add(a);
                vertices.add(b);
                mesh.addVertex(a);
                mesh.addVertex(b);
                mesh.addVertex(new VertexADT(centroid.x, centroid.y));
                mesh.addSegment(new Segment(a, b));

            }
            PolygonADT a = new PolygonADT(vertices);
            VertexADT c = new VertexADT(centroid.x, centroid.y);
            a.setCentroid(c);
            mesh.addPolygon(a);

        }

        DelaunayTriangulationBuilder delaunayTriangulationBuilder = new DelaunayTriangulationBuilder();

        List<Coordinate> centroidList = new ArrayList<>();
        for (int i = 0; i < centroids.length; i++) {
            centroidList.add(centroids[i]);
        }
        delaunayTriangulationBuilder.setSites(centroidList);

        GeometryCollection triangles = (GeometryCollection) delaunayTriangulationBuilder.getTriangles(geometryFactory);

        Map<PolygonADT, Set<PolygonADT>> neighbours = new HashMap<>();



        return mesh.transform();

    }
    private Mesh step2_grid(){Mesh2 step2 = new Mesh2();
        List<VertexADT>vertices = new ArrayList<>();
        for (int x = 0; x < width; x += square_size) {
            for (int y = 0; y < height; y += square_size) {
                VertexADT p1 = new VertexADT(x, y);
                VertexADT p2 = new  VertexADT(x, y + square_size);
                VertexADT p3 = new  VertexADT(x + square_size, y + square_size);
                VertexADT p4 = new  VertexADT(x + square_size, y);
                vertices.add(p1);
                vertices.add(p2);
                vertices.add(p3);
                vertices.add(p4);
                VertexADT cen= new VertexADT(x + square_size/2,y + square_size/2);
                step2.addVertex(cen);//centre
                PolygonADT polygon = new PolygonADT(vertices);
                polygon.setCentroid(cen);
                for (VertexADT p : polygon.getVertices()) {
                    Random bag = new Random();
                    int red = bag.nextInt(255);
                    int green = bag.nextInt(255);
                    int blue = bag.nextInt(255);
                    float alpha = bag.nextFloat(1);
                    String colorCode = red + "," + green + "," + blue + "," + alpha;
                    Color.setColor(p,colorCode);
                }
                for (Segment s : polygon.getSegments()) {
                    Color.setColor(s);
                }
                step2.addPolygon(polygon);

                vertices.clear();
            }
        }

        return step2.transform();}

public Mesh generate(MeshKind mk,int num_poly,int levelRelax) {
    Mesh m;
    if(mk.equals(MeshKind.irregular)) {
        return step3_irregular(num_poly,levelRelax);
    }else {
        return step2_grid();
    }
}
    public Mesh generate() {
        return this.generate(MeshKind.irregular,50,200);
    }
}
