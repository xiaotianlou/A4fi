package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Polygon {
    private List<Point> vertices;
    private List<Segment> segments;

    public Polygon(List<Point> vertices) {
        this.vertices = new ArrayList<>(vertices);
        this.segments = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            Point start = vertices.get(i);
            Point end = vertices.get((i + 1) % vertices.size());
            Segment segment = new Segment(start, end);
            segments.add(segment);
        }
    }

    public List<Point> getVertices() {
        return vertices;
    }

    public List<Segment> getSegments() {
        return segments;
    }

//    public List<Segment> makeSegmentsConsecutive(Polygon polygon) {
//        List<Segment> segments = polygon.getSegments();
//        if (segments.size() < 2) {
//            return segments;
//        }
//        List<Segment> result = new ArrayList<>(segments.size());
//        Set<Point> vertices = new HashSet<>();
//        for (int i = 0; i < segments.size(); i++) {
//            if (i == 0) {
//                result.add(segments.get(i));
//                vertices.add(segments.get(i).getStart());
//                vertices.add(segments.get(i).getEnd());
//            } else {
//                boolean found = false;
//                for (Segment segment : segments) {
//                    if (vertices.contains(segment.getStart()) && !vertices.contains(segment.getEnd())) {
//                        result.add(segment);
//                        vertices.add(segment.getEnd());
//                        found = true;
//                        break;
//                    } else if (vertices.contains(segment.getEnd()) && !vertices.contains(segment.getStart())) {
//                        result.add(new Segment(segment.getEnd(), segment.getStart()));
//                        vertices.add(segment.getStart());
//                        found = true;
//                        break;
//                    }
//                }
//                if (!found) {
//                    throw new IllegalArgumentException("Segments are not consecutive");
//                }
//            }
//        }
//        return result;
//    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Polygon) {
            Polygon other = (Polygon) obj;
            return new HashSet<>(segments).equals(new HashSet<>(other.segments));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashSet<>(segments).hashCode();
    }

}
