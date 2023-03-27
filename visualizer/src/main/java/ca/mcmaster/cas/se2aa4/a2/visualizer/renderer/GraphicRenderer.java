package ca.mcmaster.cas.se2aa4.a2.visualizer.renderer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties.ColorProperty;
import org.apache.batik.bridge.svg12.SVGSolidColorElementBridge;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.Iterator;
import java.util.Optional;

public class GraphicRenderer implements Renderer {

    private static final int THICKNESS = 3;
    public void render(Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.2f);
        canvas.setStroke(stroke);
        drawPolygons(aMesh,canvas);
        drawSegments(aMesh,canvas);
    }

    private void drawSegments(Mesh aMesh,Graphics2D canvas){
        for (Structs.Segment segment:aMesh.getSegmentsList()){

            Optional<Color> fill = new ColorProperty().extract(segment.getPropertiesList());
            canvas.setColor(fill.get());

            float temp = 0.2f;
            for (var p:segment.getPropertiesList()){
                if (p.getKey().equals("thickness")){
                    temp = Float.parseFloat(p.getValue());
                }
            }
            System.out.println(temp);
            Stroke stroke = new BasicStroke(temp);
            canvas.setStroke(stroke);
            canvas.draw(new Line2D.Double(aMesh.getVerticesList().get(segment.getV1Idx()).getX(),aMesh.getVerticesList().get(segment.getV1Idx()).getY(),aMesh.getVerticesList().get(segment.getV2Idx()).getX(), aMesh.getVerticesList().get(segment.getV2Idx()).getY()));

        }
    }

    private void drawPolygons(Mesh aMesh, Graphics2D canvas) {
        for(Structs.Polygon p: aMesh.getPolygonsList()){
            drawAPolygon(p, aMesh, canvas);
        }
    }

    private void drawAPolygon(Structs.Polygon p, Mesh aMesh, Graphics2D canvas) {
        Hull hull = new Hull();
        for(Integer segmentIdx: p.getSegmentIdxsList()) {
            hull.add(aMesh.getSegments(segmentIdx), aMesh);
        }
        Path2D path = new Path2D.Float();
        Iterator<Vertex> vertices = hull.iterator();
        Vertex current = vertices.next();
        path.moveTo(current.getX(), current.getY());
        while (vertices.hasNext()) {
            current = vertices.next();
            path.lineTo(current.getX(), current.getY());
        }
        path.closePath();
        canvas.draw(path);
        Optional<Color> fill = new ColorProperty().extract(p.getPropertiesList());

        if(fill.isPresent()) {
            Color old = canvas.getColor();
            canvas.setColor(fill.get());
            canvas.fill(path);
            canvas.setColor(old);
        }

    }

}
