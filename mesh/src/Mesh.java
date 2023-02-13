import java.awt.*;
import java.util.*;
import java.util.List;

public class Mesh {
    private List<Point> vertices;
    private List<Segment> segments;
    private List<Polygon> polygons;

    public Mesh() {
        this.vertices = new ArrayList<>();
        this.segments = new ArrayList<>();
        this.polygons = new ArrayList<>();
    }

    public void addVertex(Point vertex) {
        this.vertices.add(vertex);
    }

    public void addSegment(Segment segment) {
        this.segments.add(segment);
    }

    public void addPolygon(Polygon polygon) {
        this.polygons.add(polygon);
    }

    public void removeDuplicates() {
        Set<Point> uniqueVertices = new HashSet<>(vertices);
        vertices.clear();
        vertices.addAll(uniqueVertices);

        Set<Segment> uniqueSegments = new HashSet<>(segments);
        segments.clear();
        segments.addAll(uniqueSegments);

        Set<Polygon> uniquePolygons = new HashSet<>(polygons);
        polygons.clear();
        polygons.addAll(uniquePolygons);
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

    static class Point {
        private double x;
        private double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return Double.compare(point.x, x) == 0 &&
                    Double.compare(point.y, y) == 0;
        }
    }

    static class Segment {
        private Point start;
        private Point end;

        public Segment(Point start, Point end) {
            this.start = start;
            this.end = end;
        }

        public Point getStart() {
            return start;
        }
    }
}