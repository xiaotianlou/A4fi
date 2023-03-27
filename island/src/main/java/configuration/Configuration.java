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
    public static final String INPUT = "i";
    public static final String OUTPUT = "o";
    public static final String shape = "s";
    public static final String altitude  = "al";
    public static final String lakes = "l";
    public static final String rivers = "r";
    public static final String HELP = "help";
    public static final String aquifers = "aq";
    public static final String soil = "s";
    public static final String biomes = "b";
    public static final String seed = "s";

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

    private Options options() {
        Options options = new Options();
        options.addOption(new Option(INPUT, true, "input mesh adress"));
        options.addOption(new Option(OUTPUT, true, "adress of output mesh"));
        options.addOption(new Option(shape, true, "Seed for shape"));
        options.addOption(new Option(altitude, true, "Seed for generator altitude"));
        options.addOption(new Option(lakes, true, "seed to generator lake"));
        options.addOption(new Option(rivers, true, "seed to generator river"));

        options.addOption(new Option(aquifers, true, "a int as seed to generator for quifier"));

        options.addOption(new Option(soil, true, "seed for soil"));
        options.addOption(new Option(biomes, true, "seed for biomes"));
        options.addOption(new Option(seed, true, "seed for all in one times"));
        options.addOption(new Option(HELP, false, "print help message"));
        return options;
    }

}
