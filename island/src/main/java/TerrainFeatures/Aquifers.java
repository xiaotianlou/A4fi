package TerrainFeatures;


import transformation.builtinADT.MeshADT;

public class Aquifers {

    MeshADT meshADT;
    int n;

    public Aquifers(MeshADT meshADT, int n) {
        this.meshADT = meshADT;
        this.n = n;
    }

    public MeshADT aquifersInitialization(){
        aquiferDistributor();
        return this.meshADT;
    }

    private void aquiferDistributor(){
        for (var p: this.meshADT.getPolygons()){
            p.setWaterContent((n*p.getId()+5)*17%49);
        }
    }
}