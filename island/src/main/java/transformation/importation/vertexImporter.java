package transformation.importation;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.VertexADT;

public class vertexImporter implements Importer{
    @Override
    public void read(Structs.Mesh mesh, MeshADT meshADT) {
        for (var v:mesh.getVerticesList()){
//            VertexADT vertexADT = new VertexADT(v);
            VertexADT vertexADT = meshADT.getVertex(v.getX(), v.getY());
            for (int n = mesh.getPropertiesCount();n>0;n--){
                if (n == 1){
                    vertexADT.setColor(v.getProperties(0).getValue());
                }
            }
        }


    }
}
