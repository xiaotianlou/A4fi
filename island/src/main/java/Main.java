import Heatmaps.ElevationHeatMap;
import Heatmaps.HeatMap;
import Heatmaps.HumidityHeatMap;
import Reproducibility.Seed;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import featureRenderer.*;
import featureRenderer.Shape.BackGroundGenerator;
import featureRenderer.Shape.LagoonGenerator;
import transformation.builtinADT.MeshADT;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        ConfigurationIsland config = new ConfigurationIsland(args);
        Map<String, String> options = config.export();

        MeshADT meshADT = new MeshADT();
        int seedNumber = (int) (Math.random() * 100000);
        System.out.println("default seed :"+seedNumber);
        Seed defaultSeed = new Seed(seedNumber);



        String input_c = "IOArea/inputoff.mesh";
        String outputaddress="IOArea/outputoff.mesh";
//        java -jar island/island.jar -i IOArea/inputoff.mesh -o IOArea/lagoon.mesh -mode lagoon
        if (!(options.get(ConfigurationIsland.inputAddress) == (null))) {
            input_c = "".concat(options.get(ConfigurationIsland.inputAddress));}
        System.out.println(input_c);
        if (!(options.get(ConfigurationIsland.outputAddress) == (null))) {
            outputaddress = "".concat(options.get(ConfigurationIsland.outputAddress));}
        if (!(options.get(ConfigurationIsland.seed) == (null))) {
            defaultSeed = new Seed(Integer.parseInt(options.get(ConfigurationIsland.seed)));
        }
        if (!(options.get(ConfigurationIsland.mode)==null)&&options.get(ConfigurationIsland.mode).equals("lagoon")) {
            Structs.Mesh aMesh = new MeshFactory().read(input_c);
            meshADT.readInputMesh(aMesh);
            new BackGroundGenerator().Genering(meshADT, defaultSeed);
            new LagoonGenerator().Genering(meshADT, defaultSeed);
            Structs.Mesh out = meshADT.toMesh();
            new MeshFactory().write(out, options.get(ConfigurationIsland.outputAddress));//
            System.exit(0);
        }
        if (!(options.get(ConfigurationIsland.aquifersNumber) == (null))) {
            meshADT.setNumAquifers(Integer.parseInt(options.get(ConfigurationIsland.aquifersNumber)));
        }
        Structs.Mesh aMesh = new MeshFactory().read(input_c);
        meshADT.readInputMesh(aMesh);


        if (!(options.get(ConfigurationIsland.shapeSeed) == (null))) {
            new ShapeRenderer().Rendering(meshADT, new Seed(Integer.parseInt(options.get(ConfigurationIsland.shapeSeed))));
            System.out.println("1");
        }else {
            new ShapeRenderer().Rendering(meshADT,defaultSeed);
            System.out.println("2");
        }

        if (!(options.get(ConfigurationIsland.altitudeSeed) == (null))) {
            System.out.println("3");
            new ElevationRenderer().Rendering(meshADT, new Seed(Integer.parseInt(options.get(ConfigurationIsland.altitudeSeed))));

        }else {
            System.out.println("4");
            new ElevationRenderer().Rendering(meshADT,defaultSeed);

        }

        if (!(options.get(ConfigurationIsland.lakeMaxNumber) == (null))) {
            defaultSeed.setMaxlakeNumber(Integer.parseInt(options.get(ConfigurationIsland.lakeMaxNumber)));
        }
        new LakeRenderer().Rendering(meshADT, defaultSeed);


        if (!(options.get(ConfigurationIsland.riverNumber) == (null))) {
            defaultSeed.setRiverNumber(Integer.parseInt(options.get(ConfigurationIsland.riverNumber)));
        }
        new RiversRenderer().Rendering(meshADT, defaultSeed);
        new BiomeRenderer().Rendering(meshADT, defaultSeed);

        if (!(options.get(ConfigurationIsland.BiomeType) == (null))) {
            new WhittakerDiagramsRenderer().Rendering(meshADT,defaultSeed);
        }


        Structs.Mesh output = meshADT.toMesh();
        new MeshFactory().write(output, outputaddress);//

        HeatMap hE = new ElevationHeatMap(meshADT);
        HeatMap hH = new HumidityHeatMap(meshADT);
        hE.build();
        Structs.Mesh outputHE = meshADT.toMesh();
        new MeshFactory().write(outputHE,"IOArea\\ElevationHeatMap.mesh");
        hH.build();
        Structs.Mesh outputHH = meshADT.toMesh();
        new MeshFactory().write(outputHH, "IOArea\\HumidityHeatMap.mesh");
        //shape- seed
        //altitude --seed
        //lake max number
        //river -number of river
        //aquifers number of aquifers
        //soil seed
        //Biome string make island


    }

}