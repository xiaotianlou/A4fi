import ca.mcmaster.cas.se2aa4.a2.generator.DotGen;
import ca.mcmaster.cas.se2aa4.a2.generator.MeshKind;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.apache.commons.cli.*;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {

        String outputname="sample.mesh";
//
        CommandLine cmd;
        CommandLineParser parser = new BasicParser();
        HelpFormatter helper = new HelpFormatter();

        Options options = new Options();
        Option alpha = new Option("h", "help", false, "help mode");
        Option b = new Option("k", "meshkind", true, "change mesh");
        Option out = new Option("o", "output", true, "output name");
        options.addOption(alpha);
        options.addOption(b);
        options.addOption(out);
        try {
            cmd = parser.parse(options, args);
            if(cmd.hasOption("h")){
                System.out.println("Help option:");
                System.out.println("-k for mesh type, uses: -k grid, -k irregular");
                System.exit(0);
            }
            if (cmd.hasOption("k")) {
                String mks = cmd.getOptionValue("k");
                MeshKind mode=MeshKind.irregular;
                switch(mks){
                    case "grid":
                        mode=MeshKind.grid;
                        break;
                    case "irregular":
                        mode=MeshKind.grid;
                        break;
                    default:
                        System.out.println("Invalid input,grid or irregular\n");
                        System.exit(0);
                }

            }
            if (cmd.hasOption("o")) {
                outputname= cmd.getOptionValue("o");

            }


        }catch (Exception e){
            e.printStackTrace();
        }
        DotGen generator = new DotGen();

        Mesh myMesh = generator.generate();
        MeshFactory factory = new MeshFactory();

        factory.write(myMesh, outputname);

//    Option config = Option.builder("h").longOpt("help")
//            .argName("temp")
//            .hasArg(false)
//            .required(false)
//            .desc("help mode").build();
//    options.addOption(config);



}}
