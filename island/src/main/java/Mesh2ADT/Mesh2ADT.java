package Mesh2ADT;

import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.PairOfVertex;
import ca.mcmaster.cas.se2aa4.a2.generator.adt.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.Map;

/**
 * @author loux8@mcmaster.ca
 * @date 2023/3/23 15:47
 */
public interface Mesh2ADT {

    public Mesh trans(Structs.Mesh mesh);

    void registerPolygons(Structs.Mesh mesh);

    void registerSegments(Structs.Mesh mesh);

    void registerVertices(Structs.Mesh mesh);



}
