package featureRenderer.City;

import Reproducibility.Seed;
import featureRenderer.Renderable;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;
import transformation.builtinADT.SegmentADT;
import transformation.builtinADT.VertexADT;

import java.awt.*;

public class CityRenderer implements Renderable {


    @Override
    public MeshADT Rendering(MeshADT m, Seed seed) {

        new CityGenerator().Genering(m, seed);

        for (PolygonADT p: m.getPolygons()){
            p.getCentrVertex().setCityType(p.getCityType());//mapping to vertex
        }
        //draw on vertex
        for (VertexADT v:m.getVertices()){
            if(v.getCityType()==null){
                continue;
            }

            if (v.getCityType().equals(CityType.Capital)){
                Color c= new Color(123, 183, 64);

            }
        }

            //        new Color(123, 183, 64);



        return null;
    }
}
