package featureRenderer.Shape;

import Reproducibility.Seed;

import transformation.builtinADT.InfoSet;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

import java.awt.*;

public class LagoonGenerator implements Generable{



    @Override
    public MeshADT Genering(MeshADT m, Seed seed) {

        int cen_x = 1920 / 2;
        int cen_y = 1920 / 2;

        int circle_size = 800;


        double space = Math.sqrt(1920 * 1920 / 1000);


        for (PolygonADT p : m.getPolygons()) {
            double x = p.getCentroid().getX();
            double y = p.getCentroid().getY();
            //先找
            double distance = Math.sqrt(Math.pow(x - cen_x, 2) + Math.pow(y - cen_y, 2));
            Color c = new Color(13, 108, 185);
            InfoSet i =p.getInfoSet();

            p.setColor(new int[]{c.getRed(),c.getGreen(),c.getBlue()});
            if (distance < circle_size) {
                 c = new Color(153, 220, 211);
                i.setColor(new int[]{c.getRed(),c.getGreen(),c.getBlue()});
                if (distance < circle_size - space) {
                    c=new Color(255, 255, 255);
                    i.setColor(new int[]{c.getRed(),c.getGreen(),c.getBlue()});
                    if (distance < circle_size / 2) {
                        c=new Color(153, 220, 211);
                        i.setColor(new int[]{c.getRed(),c.getGreen(),c.getBlue()});
                        if (distance < circle_size / 2 - space) {
                            c=new Color(146, 198, 250);
                            i.setColor(new int[]{c.getRed(),c.getGreen(),c.getBlue()});
                        }
                    }
                }
            }
        }

        return m;

    }
}
