package featureRenderer.Shape;

import Reproducibility.Seed;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import transformation.builtinADT.MeshADT;

public interface Generable {

    MeshADT Rendering(MeshADT m, Seed seed);


}
