package transformation.builtinADT;

import TerrainFeatures.Aquifers;

public class InfoSet {

    private boolean isIsland = false;
    private Biome biome = Biome.None;
    private int elevation = -1;
    private int waterContent;
    private int temperature = 25;


    public int[] getColor() {
        return color;
    }

    public static int calculateHumidity(int waterContent, int distance) {
        return (int) (100 * waterContent / (waterContent + 0.05 * distance));
    }

    public void setColor(int[] color) {
        this.color = color;
    }

    private int[] color = new int[]{255, 255, 255};

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
    public void setColor(String c) {
        int n = 0;
        for (String s : c.split(",")) {
            color[n] = Integer.parseInt(s);
            n++;
        }
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
        this.temperature = (int) (15-0.25*getElevation());
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
