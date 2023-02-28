import ca.mcmaster.cas.se2aa4.a2.generator.DotGen;
import ca.mcmaster.cas.se2aa4.a2.generator.MeshKind;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.apache.commons.cli.*;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {

        String outputname="sampleS.mesh";
//
        MeshKind mode=MeshKind.irregular;
        int numPoly=50;
        int levelRelax=200;
        CommandLine cmd;
        CommandLineParser parser = new BasicParser();
        HelpFormatter helper = new HelpFormatter();

        Options options = new Options();
        Option alpha = new Option("h", "help", false, "help mode");
        Option b = new Option("k", "meshkind", true, "change mesh");
        Option out = new Option("o", "output", true, "output name");
        Option nP = new Option("n", "numPoly", true, "change number of poly");
        Option lR = new Option("l", "levelRelax", true, "change number of relax");
        options.addOption(alpha);
        options.addOption(b);
        options.addOption(out);
        options.addOption(nP);
        options.addOption(lR);
        try {
            cmd = parser.parse(options, args);
            if(cmd.hasOption("h")){
                System.out.println("Help option:");
                System.out.println("-k for mesh type, uses: -k grid, -k irregular,-k BONUS default is irregular");
                System.out.println("-o for change the name of output,default is sampleS.mesh ");
                System.out.println("-n for change number of poly default is 50");
                System.out.println("-l change the level relax default is 200");
                System.exit(0);
            }
            if (cmd.hasOption("k")) {
                String mks = cmd.getOptionValue("k");
                switch(mks){
                    case "grid":
                        mode=MeshKind.grid;
                        break;
                    case "irregular":
                        mode=MeshKind.irregular;
                        break;
                    case "bonus":
                        mode=MeshKind.BONUS;
                            break;
                    default:
                        System.out.println("Invalid input,grid or irregular\n");
                        System.exit(0);
                        break;
                }
            }
            if (cmd.hasOption("o")) {
                outputname= cmd.getOptionValue("o");
            }
            if (cmd.hasOption("n")) {
                numPoly= Integer.parseInt(cmd.getOptionValue("n"));
            }
            if (cmd.hasOption("l")) {
                levelRelax= Integer.parseInt(cmd.getOptionValue("l"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        DotGen generator = new DotGen();
        Mesh myMesh = generator.generate(mode,numPoly,levelRelax);
        MeshFactory factory = new MeshFactory();
        factory.write(myMesh, outputname);



}}
