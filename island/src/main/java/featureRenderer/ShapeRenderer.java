package featureRenderer;

import Reproducibility.Seed;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import featureRenderer.Shape.LagoonGenerator;
import featureRenderer.Shape.RandomShapeGenerator;
import transformation.builtinADT.MeshADT;

public class ShapeRenderer implements Renderable{


    @Override
    public MeshADT Rendering(MeshADT m, Seed seed) {
//        int choose =seed.getSeedArray().get(seed.getSeedArray().size()/2)%2;

//        return new LagoonGenerator().Genering(m,seed);

        return  new RandomShapeGenerator().Genering(m,seed);


    }
}
