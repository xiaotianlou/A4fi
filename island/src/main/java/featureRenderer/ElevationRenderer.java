package featureRenderer;

import Reproducibility.Seed;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import featureRenderer.Elevation.BasicElevationGenerator;
import featureRenderer.Elevation.VolcanoGenerator;
import transformation.builtinADT.InfoSet;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/3/23 16:14
 */
public class ElevationRenderer implements Renderable{


    @Override
    public MeshADT Rendering(MeshADT m, Seed seed) {
        new VolcanoGenerator().Genering(m,seed);
        new BasicElevationGenerator().Genering(m,seed);
        return m;
    }
}
