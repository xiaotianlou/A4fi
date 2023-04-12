package featureRenderer.Shape;

import Reproducibility.Seed;
import featureRenderer.Generable;
import transformation.builtinADT.InfoSet;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

import java.awt.*;

public class RandomShapeGenerator implements Generable {
    @Override
    public MeshADT Genering(MeshADT m, Seed seed) {

        double para1=Simulink((seed.getSeedArray().get(seed.getSeedArray().size()/2))-5);
        double para2=Simulink((seed.getSeedArray().get(seed.getSeedArray().size()/6))-5);

        double baisx=100;//shift x
        double baisy=100;//shift y

        double cen_x = (1920 / 2)*para1+baisx;
        double cen_y = (1920 / 2)*para2+baisy;

        double sizeIsland=0;//default

        double circle_size = Simulink(seed.getSeedArray().get(1)-5)*450+500-50+sizeIsland;
        System.out.println(seed.getSeedArray().get(1)-3+"   "+circle_size);

        Color c;
        for (PolygonADT p : m.getPolygons()) {
            double x = p.getCentrVertex().getX();
            double y = p.getCentrVertex().getY();

            double seedpara1=Simulink(seed.getSeedArray().get(seed.getSeedArray().size()/2))*0.01;
            double seedpara2=Simulink(seed.getSeedArray().get(seed.getSeedArray().size()/3))*0.07;
            double distance = Math.sqrt(Math.pow(x - cen_x, 2+seedpara1) + Math.pow(y - cen_y, 2+seedpara2));
            InfoSet i =p.getInfoSet();
            if (distance < circle_size) {
                i.setIsland(true);
                c = new Color(255, 255, 255);
                i.setColor(new int[]{c.getRed(),c.getGreen(),c.getBlue()});
            }



        }

    return  m;
    }

}
