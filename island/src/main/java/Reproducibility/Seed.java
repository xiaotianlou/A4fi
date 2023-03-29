package Reproducibility;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Seed {

    public int getSeed() {
        return seed;
    }

    private int seed;

    public String getBiome() {
        return biome;
    }

    public void setBiome(String biome) {
        this.biome = biome;
    }

    private String biome;

    public int getRiverNumber() {
        return riverNumber;
    }

    public void setRiverNumber(int riverNumber) {
        this.riverNumber = riverNumber;
    }

    private int riverNumber=getSeedArray().get(getSeedArray().size() / 2) * 2 + 7;

    public int getMaxlakeNumber() {
        return MaxlakeNumber;
    }

    public void setMaxlakeNumber(int maxlakeNumber) {
        this.MaxlakeNumber = maxlakeNumber;
    }

    private int MaxlakeNumber = getSeedArray().get(getSeedArray().size()/3)+3;;


    public List<Integer> getSeedArray(){
        List<Integer> l = new ArrayList<Integer>();
        int n =seed;
        while (n>0){
        l.add(n%10);
        n=n/10;
        Collections.reverse(l);
        }
        if(l.size()==0){
            l.add(5);
            l.add(2);
            l.add(7);
        }

        return l;
    }




    public Seed(){
        seed = (int) (Math.random() * 10000 + 1000);
    }
    public Seed(int seed){
        this.seed = seed;
    }




}
