package featureRenderer.City;

import Reproducibility.Seed;
import featureRenderer.Generable;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

import java.util.ArrayList;
import java.util.List;

public class CityGenerator implements Generable {
    @Override
    public MeshADT Genering(MeshADT m, Seed seed) {

        int cityNumber = seed.getCityNumber();

        ArrayList<PolygonADT> temp = new ArrayList<>();

        for (PolygonADT p:m.getPolygons()){
            if(p.isIsland()){
                temp.add(p);
            }
        }

        for (int i = 0; i < cityNumber; i++) {
            temp.get(i+(seed.getSeedArray().get(i%seed.getSeedArray().size())%temp.size())).setCityType(CityType.values()[((i+seed.getSeedArray().get(0)%4))%4]);
        }
        return m;
    }
}
