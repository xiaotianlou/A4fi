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
        int i = 3;

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
//            System.out.println(n);


            VertexADT vertexADT = meshADT.getVertices().get(n*101%3000);
            int id = vertexADT.getId();
//            System.out.println("==");
//            System.out.println(id);

            boolean flag = true;
            while (meshADT.getVertices().get(id).isCentroid()||meshADT.getVertices().get(id).isAroundWater()){
//                System.out.println(")()");
//                System.out.println(id);
                if (id-1<10 && flag){
//                    System.out.println("*");
//                    System.out.println(id);
                    id += 2950;
//                    System.out.println(id);
//                    System.out.println("*");
                }else {
//                    System.out.println("==");
//                    System.out.println(id);
                    id -= 1;
//                    System.out.println(id);
//                    System.out.println("==");
                }
//                System.out.println(id);
//                System.out.println(")()");
            }
//            System.out.println(id);

            vertexADT = meshADT.getVertices().get(id);

            VertexADT next_vertexADT = vertexADT;

            boolean end = false;

            vertexADT.setColor(105+","+200+","+225);

            while (!vertexADT.isAroundWater()&&!end){
//                System.out.println(n);

                end = true;

                double min = vertexADT.getElevation();

                for (var v: vertexADT.getVertices()){

                    if (!v.isCentroid()){
                        if (min>v.getElevation()){
                            min = v.getElevation();
                            next_vertexADT = meshADT.getVertices().get(v.getId());
                            end = false;
//                            System.out.println(vertexADT.getId());
                        }
                    }
                }
                if (!end) {
                    SegmentADT segmentADT = meshADT.getSegment(vertexADT, next_vertexADT);
//                    System.out.println("---------");
//                    System.out.println( segmentADT.getId());
//                    System.out.println("------");
                    if(segmentADT.getThickness()==1){
                        segmentADT.setThickness(100);
                    }
                    segmentADT.setColor(0 + "," + 0 + "," + 0);
                    vertexADT.setColor(255 + "," + 100 + "," + 100);
                    int temp = segmentADT.getThickness();
                    segmentADT.setThickness(5+temp);
                    vertexADT = next_vertexADT;
//                    System.out.println("======");
//                    System.out.println(vertexADT.getId());
//                    System.out.println(segmentADT.getThickness());
//                    System.out.println(segmentADT.getColorCode());
                }

            }

        }
        return meshADT;
    }

}
