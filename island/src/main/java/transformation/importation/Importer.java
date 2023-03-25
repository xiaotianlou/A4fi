package transformation.importation;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import transformation.builtinADT.MeshADT;

public interface Importer {
    void read(Structs.Mesh mesh, MeshADT meshADT);
}
