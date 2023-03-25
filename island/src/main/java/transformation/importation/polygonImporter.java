package transformation.importation;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.SegmentADT;
import transformation.builtinADT.VertexADT;

import java.util.ArrayList;
import java.util.List;

public class polygonImporter implements Importer{
    @Override
    public void read(Structs.Mesh mesh, MeshADT meshADT) {
        for (var p:mesh.getPolygonsList()){
            ArrayList <VertexADT> vertices = new ArrayList<>();
            VertexADT centroid = meshADT.getVertices().get(p.getCentroidIdx());
            for (var s:p.getSegmentIdxsList()){
                SegmentADT segment = meshADT.getSegments().get(s);
                if (!vertices.contains(segment.getStart())){
                    vertices.add(segment.getStart());
                }
                if (!vertices.contains(segment.getEnd())){
                    vertices.add(segment.getEnd());
                }
            }
            meshADT.getPolygon(mesh,p.getCentroidIdx(),);
        }
    }
}
