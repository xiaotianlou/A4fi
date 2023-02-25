package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.List;

public class GraphicRenderer {

    private static final int THICKNESS = 3;

    public void render(Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);
        for (Vertex v : aMesh.getVerticesList()) {
            double centre_x = v.getX() - (THICKNESS / 2.0d);
            double centre_y = v.getY() - (THICKNESS / 2.0d);
            Color old = canvas.getColor();
            canvas.setColor(extractColor(v.getPropertiesList()));
            Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, THICKNESS, THICKNESS);
            canvas.fill(point);
            canvas.setColor(old);
        }

        for (Segment line : aMesh.getSegmentsList()) {
            canvas.setColor(Color.BLACK);
            canvas.setStroke(stroke);

            canvas.setColor(extractColor(line.getPropertiesList()));

            canvas.draw(new Line2D.Double(aMesh.getVerticesList().get(line.getV1Idx()).getX(),aMesh.getVerticesList().get(line.getV1Idx()).getY(),aMesh.getVerticesList().get(line.getV2Idx()).getX(), aMesh.getVerticesList().get(line.getV2Idx()).getY()));
        }
        
    }
    public void renderDebug(Mesh aMesh, Graphics2D canvas) {
        System.out.println("debug mode");
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);
        for (Vertex v : aMesh.getVerticesList()) {
            double centre_x = v.getX() - (THICKNESS / 2.0d);
            double centre_y = v.getY() - (THICKNESS / 2.0d);
            Color old = canvas.getColor();

            canvas.setColor(new Color(0, 0, 0));
            for(var a:aMesh.getPolygonsList()){
                if(v==aMesh.getVertices(a.getCentroidIdx())){
                 canvas.setColor(new Color(255,0,0));
                }//red color for centre
            }
            //all centre red
            Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, THICKNESS, THICKNESS);
            canvas.fill(point);
            canvas.setColor(old);
        }

        for (Segment line : aMesh.getSegmentsList()) {
            canvas.setColor(Color.BLACK);
            //all association in black
            canvas.setStroke(stroke);
            canvas.draw(new Line2D.Double(aMesh.getVerticesList().get(line.getV1Idx()).getX(),aMesh.getVerticesList().get(line.getV1Idx()).getY(),aMesh.getVerticesList().get(line.getV2Idx()).getX(), aMesh.getVerticesList().get(line.getV2Idx()).getY()));
        }
        //draw neighbour realtion










    }






    private Color extractColor(List<Property> properties) {
        String val = null;
        for (Property p : properties) {
            if (p.getKey().equals("rgb_color")) {
                System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        if (val == null)
            return Color.BLACK;
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0]);
        int green = Integer.parseInt(raw[1]);
        int blue = Integer.parseInt(raw[2]);
        return new Color(red, green, blue);
    }

}
