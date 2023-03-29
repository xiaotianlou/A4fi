package featureRenderer;

import Reproducibility.Seed;
import transformation.builtinADT.Biome;
import transformation.builtinADT.InfoSet;
import transformation.builtinADT.MeshADT;
import transformation.builtinADT.PolygonADT;

import java.awt.*;

public class BiomeRenderer implements Renderable {

    private static void setColor(PolygonADT p, Color c) {
        p.setColor(new int[]{c.getRed(), c.getGreen(), c.getBlue()});
    }

    @Override
    public MeshADT Rendering(MeshADT m, Seed seed) {
        InfoSet p;
        for (PolygonADT poly : m.getPolygons()) {
            p=poly.getInfoSet();
            int temperature = p.getTemperature();
            int humidity = poly.getHumidity();
            //sea break
            if (!p.isIsland()) {
                continue;
            }
            if (20 <= temperature) {
                if (humidity >= 250) {
                    p.setBiome(Biome.Tropical_Rain_Forest);
                    p.setColor(new Color(0, 255, 0));
                } else if (humidity >= 50) {
                    p.setBiome(Biome.Tropical_Seasonal_Forest);
                    p.setColor( new Color(154, 205, 50));
                }else {
                    p.setBiome(Biome.Subtropical_Desert);
                    p.setColor( new Color(238, 118, 33));
                }
            } else if (temperature >= 10) {
                if (humidity >= 225) {
                    p.setBiome(Biome.Temperate_Rain_Forest);
                    p.setColor( new Color(0, 250, 154));

                } else if (humidity >= 125) {
                    p.setBiome(Biome.Temperate_Deciduous_Forest);
                    p.setColor( new Color(46, 139, 87));

                } else if (humidity >= 25) {
                    p.setBiome(Biome.Temperate_Grassland_and_Desert);
                    p.setColor( new Color(255, 215, 0));
                }else {
                    p.setBiome(Biome.Subtropical_Desert);
                    p.setColor( new Color(238, 118, 33));
                }
            } else if (temperature >= 3) {
                if (humidity >= 200) {
                    p.setBiome(Biome.Temperate_Rain_Forest);
                    p.setColor( new Color(0, 250, 154));

                } else if (humidity >= 75) {
                    p.setBiome(Biome.Temperate_Deciduous_Forest);
                    p.setColor( new Color(46, 139, 87));

                } else if (humidity >= 15) {
                    p.setBiome(Biome.Temperate_Grassland_and_Desert);
                    p.setColor( new Color(255, 215, 0));
                }else {
                    p.setBiome(Biome.Subtropical_Desert);
                    p.setColor( new Color(238, 118, 33));
                }
            } else if (temperature >= -5) {
                if (humidity >= 30) {
                    p.setBiome(Biome.Taiga);
                    p.setColor( new Color(0, 100, 0));

                } else if (humidity >= 10) {
                    p.setBiome(Biome.Temperate_Grassland_and_Desert);
                    p.setColor( new Color(255, 215, 0));
                }else {
                    p.setBiome(Biome.Subtropical_Desert);
                    p.setColor(new Color(238, 118, 33));

                }
            } else if (temperature < -5) {
                p.setBiome(Biome.Tundra);
                p.setColor( new Color(0, 206, 209));

            }
        }
        return m;

    }
}
