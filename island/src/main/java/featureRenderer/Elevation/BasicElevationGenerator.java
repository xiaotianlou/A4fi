package featureRenderer.Elevation;

import Reproducibility.Seed;
import featureRenderer.Generable;
import transformation.builtinADT.InfoSet;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

import java.util.ArrayList;
import java.util.List;

public class BasicElevationGenerator implements Generable {
    @Override
    public MeshADT Genering(MeshADT m, Seed seed) {

        List<PolygonADT> land = new ArrayList();


        for (PolygonADT p : m.getPolygons()) {
            if (p.getInfoSet().isIsland()) {
                land.add(p);
            }
        }
        for (PolygonADT p : land) {
            InfoSet ti = p.getInfoSet();
            if (ti.getElevation() == -1) {
                ti.setElevation(seed.getSeedArray().get(p.getId()*3 % seed.getSeedArray().size())+seed.getSeedArray().get(p.getId() % seed.getSeedArray().size()) * 70 / 10);
            }
        }
        return m;
    }
}
