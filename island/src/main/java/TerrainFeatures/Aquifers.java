package TerrainFeatures;


import transformation.builtinADT.MeshADT;

public class Aquifers {

    public void aquifersInitialization(MeshADT meshADT, int n){
        aquiferDistributor(meshADT,n);
    }

    private void aquiferDistributor(MeshADT meshADT,int n){
        for (var p: meshADT.getPolygons()){
            p.setWaterContent((n*p.getId()+5)*17%49);
        }
    }
}