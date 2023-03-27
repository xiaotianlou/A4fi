package featureRenderer;

import Reproducibility.Seed;
import transformation.builtinADT.MeshADT;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/3/23 16:15
 */
public class LakesRenderer implements Renderable {


    @Override
    public MeshADT Rendering(MeshADT m, Seed seed) {
//        for (int n=2;n>0;n--){
//            VertexADT vertexADT =
//
//            VertexADT vertexADT = meshADT.getVertices().get(n*i*666%3000);
//            int id = vertexADT.getId();
//            while (meshADT.getVertices().get(id).isCentroid()||meshADT.getVertices().get(id).isAroundWater()){
//                id += 39;
//            }
//
//            vertexADT = meshADT.getVertices().get(id);
//
//            VertexADT next_vertexADT = vertexADT;
//
//            boolean end = false;
//
//            vertexADT.setColor(105+","+200+","+225);
//
//            while (!vertexADT.isAroundWater()&&!end){
//
//                end = true;
//
//                double min = vertexADT.getElevation();
//
//                for (var v: vertexADT.getVertices()){
//
//                    if (!v.isCentroid()){
//
//                        if (min>v.getElevation()){
//                            min = v.getElevation();
//                            next_vertexADT = meshADT.getVertices().get(id);
//                            end = false;
//                        }
//                    }
//                }
//                if (!end) {
//                    SegmentADT segmentADT = meshADT.getSegment(vertexADT, next_vertexADT);
//                    if(segmentADT.getThickness()!=1){
//                        segmentADT.setThickness();
//                    }
//                    segmentADT.setColor(105 + "," + 200 + "," + 225);
//                    vertexADT = next_vertexADT;
//                }
//
//            }
//
//        }


        return null;
    }
}

