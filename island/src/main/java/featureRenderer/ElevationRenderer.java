package featureRenderer;

import Reproducibility.Seed;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

import java.util.ArrayList;
import java.util.List;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/3/23 16:14
 */
public class ElevationRenderer implements Renderable{


    @Override
    public MeshADT Rendering(MeshADT m, Seed seed) {
        List island = new ArrayList();


        for (PolygonADT p:m.getPolygons()){
            if(p.isIsland()){
                island.add(p);
            }
        }







        return null;
    }
}
