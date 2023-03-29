package configuration;

import org.apache.commons.cli.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/3/23 15:55
 */
public class Configuration {



    public static final String mode = "mode";
    public static final String inputAddress = "i";
    public static final String outputAddress = "o";
    public static final String shapeSeed = "shape";
    public static final String altitudeSeed = "al";
    public static final String lakeMaxNumber = "l";
    public static final String riverNumber = "r";
    public static final String HELP = "help";
    public static final String aquifersNumber = "aq";
    public static final String soilSeed = "s";
    public static final String BiomeType = "b";
    public static final String seed = "seed";

    private CommandLine cli;

    public Configuration(String[] args) {
        try {
            this.cli = parser().parse(options(), args);
            if (cli.hasOption(HELP)) {
                help();
            }
        } catch (ParseException pe) {
            throw new IllegalArgumentException(pe);
        }
    }

    private void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar island.jar", options());
        System.exit(0);
    }

    public Map<String, String> export() {
        Map<String, String> result = new HashMap<>();
        for(Option o: cli.getOptions()){
            result.put(o.getOpt(), o.getValue(""));
        }
        return result;
    }

    public String export(String key) {
        return cli.getOptionValue(key);
    }

    private CommandLineParser parser() {
        return new DefaultParser();
    }


    // shapeSeed,altitudeSeed,lakeMaxNumber,riverNumber,aquifersNumber,soilSeed,BiomeType,inputAddress,outputAddress
    private Options options() {
        Options options = new Options();
        options.addOption(new Option(outputAddress, true, "adress of output mesh"));
        options.addOption(new Option(shapeSeed, true, "Seed for shape"));
        options.addOption(new Option(altitudeSeed, true, "Seed for generator altitude"));
        options.addOption(new Option(lakeMaxNumber, true, "max number of lake lake"));
        options.addOption(new Option(riverNumber, true, "number of river"));
        options.addOption(new Option(aquifersNumber, true, "number of aquifers"));
        options.addOption(new Option(soilSeed, true, "seed for soil"));
        options.addOption(new Option(BiomeType, true, "biomes name"));
        options.addOption(new Option(seed, true, "global seed"));
        options.addOption(new Option(HELP, false, "print help message"));
        options.addOption(new Option(mode, true, "type lagoon to activate MVP mode, default is seed generator mode"));
        options.addOption(new Option(inputAddress, true, "input mesh adress"));
        options.hasLongOption("mode");


        return options;
    }

}
