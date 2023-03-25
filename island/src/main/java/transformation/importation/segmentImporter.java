package transformation.importation;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.SegmentADT;

public class segmentImporter implements Importer{
    @Override
    public void read(Structs.Mesh mesh, MeshADT meshADT) {
        for (var s:mesh.getSegmentsList()){
            SegmentADT segmentADT = meshADT.getSegment(meshADT.getVertices().get(s.getV1Idx()),meshADT.getVertices().get(s.getV2Idx()));
            for (int n = mesh.getPropertiesCount();n>0;n--){
                if (n == 1){
                   segmentADT .setColor(s.getProperties(0).getValue());
                }
            }
        }
    }
}
