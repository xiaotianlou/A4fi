package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.*;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;


public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int square_size = 20;

    public Structs.Mesh generate() {
        Mesh2 mesh = new Mesh2();
        List<Point>vertices = new ArrayList<>();
        for (int x = 0; x < width; x += square_size) {
            for (int y = 0; y < height; y += square_size) {
                Point p1 = new Point(x, y);
                Point p2 = new Point(x, y + square_size);
                Point p3 = new Point(x + square_size, y + square_size);
                Point p4 = new Point(x + square_size, y);
                vertices.add(p1);
                vertices.add(p2);
                vertices.add(p3);
                vertices.add(p4);
                Polygon polygon = new Polygon(vertices);
                for (Point p : polygon.getVertices()) {
                    Random bag = new Random();
                    int red = bag.nextInt(255);
                    int green = bag.nextInt(255);
                    int blue = bag.nextInt(255);
                    int alpha = bag.nextInt(255);
                    String colorCode = red + "," + green + "," + blue + "," + alpha;
                    Color.setColor(p,colorCode);
                }
                for (Segment s : polygon.getSegments()) {
                    Color.setColor(s);
                }
                mesh.addPolygon(polygon);
                vertices.clear();
            }
        }

        return mesh.transform();
    }
}