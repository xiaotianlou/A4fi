package transformation.importation;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import transformation.builtinADT.MeshADT;

public class segmentImporter implements Importer{
    @Override
    public void read(Structs.Mesh mesh, MeshADT meshADT) {
        for (var s:mesh.getSegmentsList()){
            meshADT.getSegment(meshADT.getVertices().get(s.getV1Idx()),meshADT.getVertices().get(s.getV2Idx()));
        }
    }
}
