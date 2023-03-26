package Reproducibility;


import java.util.ArrayList;
import java.util.List;

public class Seed {

    public int getSeed() {
        return seed;
    }

    private int seed;

    public List<Integer> getSeedArray(){
        ArrayList<Integer> t= new ArrayList();
        for(char a :(seed+"").toCharArray()){
            t.add(Integer.parseInt(a+""));
        }
        return t;
    }




    public Seed(){
        seed = 77123123;
    }
    public Seed(int seed){
        this.seed = seed;
    }




}
