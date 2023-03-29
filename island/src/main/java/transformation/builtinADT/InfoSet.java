package transformation.builtinADT;

import java.awt.*;

public class InfoSet {

    private boolean isIsland = false;
    private Biome biome = Biome.None;
//    private int humidity = 150;
    private int elevation = -1;
    private int waterContent;
    private int temperature = 25;

    private int humidity = 0;

    private boolean isLake=false;

    public boolean isLake() {
        return isLake;
    }

    public void setLake(boolean lake) {
        isLake = lake;
    }

    public int getHumidity() {

        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    private int[] color = new int[]{255, 255, 255};

    public static int calculateHumidity(int waterContent, int distance) {
        return (int) (100 * waterContent / (waterContent + 0.05 * distance));
    }

    public int[] getColor() {
        return color;
    }

    public void setColor(int[] color) {
        this.color = color;
    }

    public void setColor(Color c) {
        this.setColor(new int[]{c.getRed(), c.getGreen(), c.getBlue()});
    }

    public void setColor(String c) {
        int n = 0;
        for (String s : c.split(",")) {
            color[n] = Integer.parseInt(s);
            n++;
        }
    }

    public boolean isIsland() {
        return isIsland;
    }

    public void setIsland(boolean island) {
        this.isIsland = island;
    }

    public Biome getBiome() {
        return biome;
    }

    public void setBiome(Biome biome) {
        this.biome = biome;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public int getWaterContent() {
        return waterContent;
    }

    public void setWaterContent(int waterContent) {
        this.waterContent = waterContent;
    }

    public int getTemperature() {
        //temp related with elevation
        this.temperature = (int) (15 - 0.25 * getElevation());
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
