package TerrainFeatures;


import transformation.builtinADT.MeshADT;

public class Aquifers {

    public MeshADT aquifersInitialization(){
        aquiferDistributor();
    }

    private void aquiferDistributor(){
        for (var p: this.meshADT.getPolygons()){
            p.setWaterContent((n*p.getId()+5)*17%49);
        }
    }
}