package featureRenderer.Shape;

import Reproducibility.Seed;
import transformation.builtinADT.InfoSet;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

import java.awt.*;

public class RandomShapeGenerator implements Generable {
    @Override
    public MeshADT Genering(MeshADT m, Seed seed) {

        int cen_x = 1920 / 2;
        int cen_y = 1920 / 2;

        double circle_size = Simulink(seed.getSeedArray().get(1)-5)*450+500;
        System.out.println(seed.getSeedArray().get(1)-3+"   "+circle_size);
        double space = Math.sqrt(1920 * 1920 / 1000)+20;

        for (PolygonADT p : m.getPolygons()) {
            double x = p.getCentroid().getX();
            double y = p.getCentroid().getY();
            //先找
            double distance = Math.sqrt(Math.pow(x - cen_x, 2) + Math.pow(y - cen_y, 2));
            Color c = new Color(13, 108, 185);
            InfoSet i =p.getInfoSet();
            i.setIsland(false);

            p.setColor(new int[]{c.getRed(),c.getGreen(),c.getBlue()});
            if (distance < circle_size) {
                i.setIsland(true);
                c = new Color(255, 255, 255);
                i.setColor(new int[]{c.getRed(),c.getGreen(),c.getBlue()});
            }



        }

    return  m;
    }

}
