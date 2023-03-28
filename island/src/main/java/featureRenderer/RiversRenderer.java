package featureRenderer;

import Reproducibility.Seed;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.SegmentADT;
import transformation.builtinADT.VertexADT;

public class RiversRenderer implements Renderable {

    @Override
    public MeshADT Rendering(MeshADT meshADT, Seed seed) {
        int i = seed.getSeedArray().get(seed.getSeedArray().size()/2)*2+7;

        for (var p: meshADT.getPolygons()){
            if (p.isIsland()){
                for (var v:p.getVertices()){
                    v.setAroundWater(false);
                }
            }
        }
        for (var p: meshADT.getPolygons()){
            if (!p.isIsland()){
                for (var v:p.getVertices()){
                    v.setAroundWater(true);
                }
            }
        }

        for (int n=10;n>0;n--){


            VertexADT vertexADT = meshADT.getVertices().get(n*101%3000);
            int id = vertexADT.getId();

            boolean flag = true;
            while (meshADT.getVertices().get(id).isCentroid()||meshADT.getVertices().get(id).isAroundWater()){
                if (id-1<10 && flag){
                    id += 2950;
                }else {
                    id -= 1;
                }
            }

            vertexADT = meshADT.getVertices().get(id);

            VertexADT next_vertexADT = vertexADT;

            boolean end = false;

            vertexADT.setColor(105+","+200+","+225);

            while (!vertexADT.isAroundWater()&&!end){

                end = true;

                double min = vertexADT.getElevation();

                for (var v: vertexADT.getVertices()){

                    if (!v.isCentroid()){
                        if (min>v.getElevation()){
                            min = v.getElevation();
                            next_vertexADT = meshADT.getVertices().get(v.getId());
                            end = false;
                        }
                    }
                }
                if (!end) {
                    SegmentADT segmentADT = meshADT.getSegment(vertexADT, next_vertexADT);
                    if(segmentADT.getThickness()==1){
                        segmentADT.setThickness(5);
                    }
                    segmentADT.setColor(100+ "," + 155 + "," + 255);
                    vertexADT.setColor(255 + "," + 100 + "," + 100);
                    float temp = segmentADT.getThickness();
                    segmentADT.setThickness(5+temp);
                    vertexADT = next_vertexADT;
                }

            }

        }
        return meshADT;
    }

}
