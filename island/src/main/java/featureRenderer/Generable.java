package featureRenderer;

import Reproducibility.Seed;
import transformation.builtinADT.MeshADT;

public interface Generable {

    MeshADT Genering(MeshADT m, Seed seed);
    default double Simulink(double input){
        return 1/(1+Math.exp(-input));
    }

}
