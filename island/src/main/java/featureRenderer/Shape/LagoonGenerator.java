package featureRenderer.Shape;

import Reproducibility.Seed;

import featureRenderer.Generable;
import transformation.builtinADT.InfoSet;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

import java.awt.*;

public class LagoonGenerator implements Generable {



    @Override
    public MeshADT Genering(MeshADT m, Seed seed) {

        int cen_x = 1920 / 2;
        int cen_y = 1920 / 2;

        double circle_size = Simulink(seed.getSeedArray().get(1)-5)*500+400;
        System.out.println(seed.getSeedArray().get(1)-3+"   "+circle_size);


        double space = Math.sqrt(1920 * 1920 / 1000)+20;


        for (PolygonADT p : m.getPolygons()) {
            double x = p.getCentroid().getX();
            double y = p.getCentroid().getY();
            //先找
            double distance = Math.sqrt(Math.pow(x - cen_x, 2) + Math.pow(y - cen_y, 2));

            InfoSet i =p.getInfoSet();

            Color c;
            if (distance < circle_size) {
                i.setIsland(true);
                 c = new Color(153, 220, 211);
                i.setColor(new int[]{c.getRed(),c.getGreen(),c.getBlue()});
                if (distance < circle_size - space) {
                    c=new Color(255, 255, 255);
                    i.setColor(new int[]{c.getRed(),c.getGreen(),c.getBlue()});
                    if (distance < circle_size / (1+2*Simulink(seed.getSeedArray().get(2)))) {
                        c=new Color(153, 220, 211);
                        i.setColor(new int[]{c.getRed(),c.getGreen(),c.getBlue()});
                        if (distance < circle_size / (1+2*Simulink(seed.getSeedArray().get(2))) - space) {
                            c=new Color(146, 198, 250);
                            i.setColor(new int[]{c.getRed(),c.getGreen(),c.getBlue()});
                            i.setIsland(false);
                        }
                    }
                }
            }
        }

        return m;

    }
}
