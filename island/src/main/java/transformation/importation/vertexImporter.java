package transformation.importation;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.VertexADT;

public class vertexImporter implements Importer{
    @Override
    public void read(Structs.Mesh mesh, MeshADT meshADT) {
        for (var v:mesh.getVerticesList()){
//            VertexADT vertexADT = new VertexADT(v);
            meshADT.getVertex(v.getX(), v.getY());
        }


    }
}
