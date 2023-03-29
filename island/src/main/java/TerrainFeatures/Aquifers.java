package TerrainFeatures;


import transformation.builtinADT.MeshADT;

public class Aquifers {



    public static void aquiferDistributor(MeshADT meshADT,int n){
        for (var p: meshADT.getPolygons()){
            p.setWaterContent((n*p.getId()+5)*17%49);
        }
    }
}