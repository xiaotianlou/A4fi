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
                Color c= new Color(250, 2, 37);
                m.drawCity(v,c,60);
            }
            if (v.getCityType().equals(CityType.cities)){
                Color c= new Color(24, 0, 245);
                m.drawCity(v,c,55);
            }
            if (v.getCityType().equals(CityType.towns)){
                Color c= new Color(0, 0, 0);
                m.drawCity(v,c,50);
            }
            if (v.getCityType().equals(CityType.hamlets)){
                Color c= new Color(255, 255, 255);
                m.drawCity(v,c,45);
            }
            if (v.getCityType().equals(CityType.villages)){
                Color c= new Color(83, 147, 222);
                m.drawCity(v,c,40);
            }

        }

            //        new Color(123, 183, 64);



        return m;
    }
}
