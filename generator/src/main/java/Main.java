import ca.mcmaster.cas.se2aa4.a2.generator.DotGen;
import ca.mcmaster.cas.se2aa4.a2.generator.MeshKind;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.apache.commons.cli.*;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {

        DotGen generator = new DotGen();

        Mesh myMesh = generator.generate();
        MeshFactory factory = new MeshFactory();
        factory.write(myMesh, args[0]);
        CommandLine cmd;
        CommandLineParser parser = new BasicParser();
        HelpFormatter helper = new HelpFormatter();

        Options options = new Options();
        Option alpha = new Option("h", "help", true, "help mode");
        options.addOption(alpha);

//    Option config = Option.builder("h").longOpt("help")
//            .argName("temp")
//            .hasArg(false)
//            .required(false)
//            .desc("help mode").build();
//    options.addOption(config);

        try {
            cmd = parser.parse(options, args);
            if(cmd.hasOption("h")){
                System.out.println("help option");

            }
            if (cmd.hasOption("n")) {
                int opt_config = Integer.parseInt(cmd.getOptionValue("number"));
                total=opt_config;
            }
            if (!cmd.hasOption("cc")&&!cmd.hasOption("n")&&args.length!=0) {


            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
