package featureRenderer;

import Reproducibility.Seed;
import featureRenderer.Elevation.BasicElevationGenerator;
import featureRenderer.Elevation.SnowMountainGenerator;
import transformation.builtinADT.MeshADT;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/3/23 16:14
 */
public class ElevationRenderer implements Renderable{


    @Override
    public MeshADT Rendering(MeshADT m, Seed seed) {

        new SnowMountainGenerator().Genering(m,seed);


        new BasicElevationGenerator().Genering(m,seed);
        return m;
    }
}
