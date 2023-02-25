package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.locationtech.jts.geom.PrecisionModel;


import java.util.Random;

import org.locationtech.jts.geom.*;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;

import java.util.*;
import org.locationtech.jts.algorithm.*;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.triangulate.*;
import org.locationtech.jts.triangulate.quadedge.*;

import org.locationtech.jts.triangulate.DelaunayTriangulationBuilder;


import static org.locationtech.jts.algorithm.Centroid.getCentroid;


public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int square_size = 20;

    public Mesh generate() {
        final int MIN_COORDINATE = 0;
        final int MAX_COORDINATE = 500;
        final int NUM_POINTS = 100;
        int numRelaxations = 5;
        MeshADT mesh = new MeshADT();
        Random random = new Random();

        // create a list to hold the randomly generated points
        List<Coordinate> points = new ArrayList<>(NUM_POINTS);
        for (int i = 0; i < NUM_POINTS; i++) {
            // generate random x and y coordinates within the specified range
            double x = MIN_COORDINATE + random.nextDouble() * (MAX_COORDINATE - MIN_COORDINATE);
            double y = MIN_COORDINATE + random.nextDouble() * (MAX_COORDINATE - MIN_COORDINATE);
            mesh.getVertex(x, y);
            // add the new coordinate to the list
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

//        for (int i = 0; i < diagram.getNumGeometries(); i++) {
//            List<VertexADT> verticesPolygon = new LinkedList<>();
//            Geometry polygon = diagram.getGeometryN(i);
//            Coordinate centroid = getCentroid(diagram.getGeometryN(i));
//            System.out.println("Polygon " + i + ": " + polygon);
//            System.out.println("Centroid:"+centroid);
//        }

        Envelope envelope = new Envelope(0, width, 0, height);
        Geometry croppedDiagram = diagram.intersection(geometryFactory.toGeometry(envelope));

        for (int i = 0; i < croppedDiagram.getNumGeometries(); i++) {

            Geometry polygon = croppedDiagram.getGeometryN(i);
            ConvexHull convexHull = new ConvexHull(polygon);
            Geometry hull = convexHull.getConvexHull();
            Coordinate[] hullCoords = hull.getCoordinates();
            Geometry reorderedDiagram = geometryFactory.createPolygon(hullCoords);

//            System.out.println("------------------------------------");
//
////            Geometry centroid = polygon.getCentroid();
//            System.out.println("Polygon " + i + ": " + reorderedDiagram);
//            System.out.println("Centroid:"+centroid);

//            for (Coordinate c:reorderedDiagram.getCoordinates()){
////                mesh.getVertex(c.x,c.y);
//                System.out.println(c);
//            }

            for (int j=1;j<reorderedDiagram.getCoordinates().length;j++){
                Coordinate c_1 = reorderedDiagram.getCoordinates()[j-1];
                Coordinate c_2 = reorderedDiagram.getCoordinates()[j];
                Coordinate centroid = getCentroid(reorderedDiagram);
                VertexADT a = mesh.getVertex(c_1.x,c_1.y);
                VertexADT b = mesh.getVertex(c_2.x,c_2.y);
                VertexADT c = mesh.getVertex(centroid.x,centroid.y);

                SegmentADT ab = mesh.getSegment(a,b);

//                System.out.println(c_1);
//                System.out.println(c_2);
//                System.out.println(centroid);
//                System.out.println('!');
            }

        }




        DelaunayTriangulationBuilder delaunayTriangulationBuilder = new DelaunayTriangulationBuilder();


//        for (int i = 0; i < croppedDiagram.getNumGeometries(); i++) {
//            List<VertexADT> verticesPolygon = new LinkedList<>();
//            Geometry polygon = croppedDiagram.getGeometryN(i);
//            Geometry centroid = polygon.getCentroid();
//            System.out.println("Polygon " + i + ": " + polygon);
//            System.out.println("Centroid:"+centroid);
//        }

//        for (int i = 0; i < croppedDiagram.getNumGeometries(); i++) {
//            Coordinate polygon = croppedDiagram.getCoordinate();
//            System.out.println(polygon);
//
//        }



        List<Coordinate> centroidList = new ArrayList<>();
        for (int i = 0; i < centroids.length; i++) {
            centroidList.add(centroids[i]);
        }
        delaunayTriangulationBuilder.setSites(centroidList);

//        for (Coordinate c:centroidList){
//            System.out.println(c);
//        }

        GeometryCollection triangles = (GeometryCollection) delaunayTriangulationBuilder.getTriangles(geometryFactory);

        Map<Polygon, Set<Polygon>> neighbours = new HashMap<>();

        for (VertexADT v:mesh.getVertices()){
            System.out.println(v.getX() +"  "+v.getY());
            System.out.println("!");

        }




        return mesh.toMesh();
    }
}


//        for (int i = 0; i < points.size(); i++) {
//            List<Integer> adjacentPolygons = new ArrayList<>();
//            Collection quadEdge = quadEdgeSubdivision.getEdges();
//
//            do {
//                int polygonIndex = edge.;
//                if (polygonIndex != i && !adjacentPolygons.contains(polygonIndex)) {
//                    adjacentPolygons.add(polygonIndex);
//                }
//                edge = edge.oNext();
//            } while (edge != start);
//            neighbours.put(i, adjacentPolygons);
//        }

//
//        // iterate over the Voronoi polygons and print their coordinates
//        for (int i = 0; i < diagram.getNumGeometries(); i++) {
//            List<VertexADT> verticesPolygon = new LinkedList<>();
//            Geometry polygon = diagram.getGeometryN(i);
//            System.out.println("Polygon " + i + ": " + polygon);
//        }
//
//
//
//
//
//
//        return mesh.toMesh();
//    }
//
//}





//        Random r = new Random();
//        for (int x = 0; x <= width; x += 2*square_size) {
//            for (int y = 0; y <= height; y += 2*square_size) {
//                VertexADT v = mesh.getVertex(x+r.nextInt(square_size),y+r.nextInt(square_size));
//            }
//        }
//
//        int numRelaxations = 5;
//
//        Coordinate[] vertexCoordinate = new Coordinate[mesh.getVertices().size()];
//
//        for (int n = 0;n<vertexCoordinate.length;n++){
//            vertexCoordinate[n] = new Coordinate(mesh.getVertices().get(n).getX(),mesh.getVertices().get(n).getY());
//        }
//
//
//        PrecisionModel precisionModel = new PrecisionModel();
//        GeometryFactory geometryFactory = new GeometryFactory(precisionModel);
//
//
//        VoronoiDiagramBuilder voronoiDiagramBuilder = new  VoronoiDiagramBuilder();
//        voronoiDiagramBuilder.setSites(Arrays.asList(vertexCoordinate));
//        Geometry diagram = voronoiDiagramBuilder.getDiagram(geometryFactory);
//
//
//        for (int i = 0 ;i<numRelaxations;i++){
//            Coordinate[] centroids = new Coordinate[vertexCoordinate.length];
//            for (int j = 0; j< mesh.getVertices().size();j++){
//                centroids[j] = getCentroid(diagram.getGeometryN(j));
//            }
//
//
//            vertexCoordinate = centroids;
//
//
//            points = new Point[vertexCoordinate.length];
//            for (int j = 0; j < vertexCoordinate.length; j++) {
//                points[j] = geometryFactory.createPoint(vertexCoordinate[j]);
//            }
//            voronoiDiagramBuilder.setSites(Arrays.asList(points));
//            diagram = voronoiDiagramBuilder.getDiagram(geometryFactory);
//        }


//        Envelope envelope = new Envelope(0, width, 0, height);
//        Geometry croppedDiagram = diagram.intersection(geometryFactory.toGeometry(envelope));
//
//        DelaunayTriangulationBuilder delaunayBuilder = new DelaunayTriangulationBuilder();
//        delaunayBuilder.setSites(croppedDiagram);
//        QuadEdgeSubdivision subdivision = delaunayBuilder.getSubdivision();


//        Map<Integer, List<Integer>> neighbors = new HashMap<>();
//        for (int i = 0; i < vertexCoordinate.length; i++) {
//            List<Integer> adjacentPolygons = new ArrayList<>();
//            QuadEdge start = subdivision.locate(subdivision[i]);
//            QuadEdge edge = start;
//            do {
//                int polygonIndex = edge.dest().getVertexIndex();
//                if (polygonIndex != i && !adjacentPolygons.contains(polygonIndex)) {
//                    adjacentPolygons.add(polygonIndex);
//                }
//                edge = edge.oNext();
//            } while (edge != start);
//            neighbors.put(i, adjacentPolygons);






//    private static Coordinate getCentroid(Geometry polygon) {
//        Coordinate[] coordinates = polygon.getCoordinates();
//        double xSum = 0;
//        double ySum = 0;
//        for (int i = 0; i < coordinates.length - 1; i++) {
//            double xi = coordinates[i].x;
//            double yi = coordinates[i].y;
//            double xip1 = coordinates[i+1].x;
//            double yip1 = coordinates[i+1].y;
//            xSum += (xi + xip1) * (xi*yip1 - xip1*yi);
//            ySum += (yi + yip1) * (xi*yip1 - xip1*yi);
//        }
//        double area = polygon.getArea();
//        return new Coordinate(xSum / (6 * area), ySum / (6 * area));


//        MeshADT mesh = new MeshADT();
//        for(int x = 0; x < width; x += square_size) {
//            for(int y = 0; y < height; y += square_size) {
//                List<VertexADT> verticesPolygon = new LinkedList<>();
//                List<SegmentADT> segmentsPolygon = new LinkedList<>();
//                Random r = new Random();
//                int n = r.nextInt(100);
//                VertexADT a =  mesh.getVertex(x,y);
//                VertexADT b =  mesh.getVertex(x+square_size,y);
//                VertexADT c =  mesh.getVertex(x,y+square_size);
//                VertexADT d =  mesh.getVertex(x+square_size,y+square_size);
//                verticesPolygon.add(a);
//                verticesPolygon.add(b);
//                verticesPolygon.add(c);
//                verticesPolygon.add(d);
//                SegmentADT ab = mesh.getSegment(a,b);
//                SegmentADT bd = mesh.getSegment(b,d);
//                SegmentADT dc = mesh.getSegment(d,c);
//                SegmentADT ca = mesh.getSegment(c,a);
//                segmentsPolygon.add(ab);
//                segmentsPolygon.add(bd);
//                segmentsPolygon.add(dc);
//                segmentsPolygon.add(ca);
//                mesh.getPolygon(mesh,verticesPolygon,segmentsPolygon);
//            }
////        }
//    }
//
//}