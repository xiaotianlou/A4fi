package Reproducibility;

import featureRenderer.Shape.Shape;

public class SeedParser {

    public static Shape getShapeType(Seed seed){
        return Shape.values()[seed.getSeed()%3];
    }
}
