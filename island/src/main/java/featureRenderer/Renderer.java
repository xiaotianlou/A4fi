package featureRenderer;

import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/3/23 15:57
 */
public abstract class Renderer implements Renderable{

    Mesh m;

    public Renderer(Mesh m){
        this.m=m;
    }


}
