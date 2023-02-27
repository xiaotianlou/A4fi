package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class DotGenTest {

    @Test
    public void meshIsNotNull() {
        DotGen generator = new DotGen();
        Structs.Mesh aMesh = generator.generate();
        assertNotNull(aMesh);
    }

    @Test
    public void step2NotNull(){
        DotGen generator = new DotGen();
        Structs.Mesh aMesh = generator.generate(MeshKind.grid,0,0);
        assertNotNull(aMesh);
    }
    @Test
    public void step3NotNull(){
        DotGen generator = new DotGen();
        Structs.Mesh aMesh = generator.generate(MeshKind.irregular,50,200);
        assertNotNull(aMesh);

    }
    @Test
    public void generatorCorrectStep3(){
        DotGen generator = new DotGen();
        Structs.Mesh aMesh = generator.generate(MeshKind.irregular,50,200);
        assertTrue(new File("sampleS.mesh").exists());
    }
    @Test
    public void generatorCorrectStep3WithBigValue(){
        DotGen generator = new DotGen();
        Structs.Mesh aMesh = generator.generate(MeshKind.irregular,400,200);
        assertTrue(new File("sampleS.mesh").exists());
    }

    @Test
    public void generatorCorrectStep2(){
        DotGen generator = new DotGen();
        Structs.Mesh aMesh = generator.generate(MeshKind.grid,50,200);
        assertTrue(new File("sampleS.mesh").exists());
    }
    @Test
    public void generatorCorrectStep3WithSmallValue(){
        DotGen generator = new DotGen();
        Structs.Mesh aMesh = generator.generate(MeshKind.irregular,0,200);
        assertTrue(new File("sampleS.mesh").exists());
//        Structs.Mesh aMesh = generator.generate(MeshKind.irregular,300,200);
//        assertTrue(new File("sampleS.mesh").exists());
    }




}
