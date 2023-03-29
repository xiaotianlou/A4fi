package featureRenderer;

import Reproducibility.Seed;

import featureRenderer.Shape.BackGroundGenerator;
import featureRenderer.Shape.LagoonGenerator;
import featureRenderer.Shape.RandomShapeGenerator;
import transformation.builtinADT.MeshADT;

public class ShapeRenderer implements Renderable{


    @Override
    public MeshADT Rendering(MeshADT m, Seed seed) {
//        int choose =seed.getSeedArray().get(seed.getSeedArray().size()/2)%2;

//        return new LagoonGenerator().Genering(m,seed);
        m=new BackGroundGenerator().Genering(m,seed);
        int number=seed.getSeedArray().get(seed.getSeedArray().size()/4);
        for (int i = 0; i < number+2; i++)
        {
            m=new RandomShapeGenerator().Genering(m,seed);
            seed = new Seed(seed.getSeed()*4);
        }
        m=new RandomShapeGenerator().Genering(m,seed);

        return  m;


    }
}
