package featureRenderer.Shape;

import Reproducibility.Seed;
import featureRenderer.Generable;
import transformation.builtinADT.InfoSet;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

import java.awt.*;

public class BackGroundGenerator implements Generable {
    @Override
    public MeshADT Genering(MeshADT m, Seed seed) {
        for (PolygonADT p : m.getPolygons()) {
            Color c = new Color(13, 108, 185);
            InfoSet i =p.getInfoSet();
            i.setIsland(false);
            p.setColor(new int[]{c.getRed(),c.getGreen(),c.getBlue()});
        }

        return m;
    }
}
