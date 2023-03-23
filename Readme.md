# Mesh Generator (Assignment #2 Walkthrough)

  - Author: Sébastien Mosser

## How to install?

```
mosser@azrael A2 % mvn install
```

It creates two jars:

  1. `generator/generator.jar` to generate meshes
  2. `visualizer/visualizer.jar` to visualize such meshes as SVG files

## Examples of execution

### Generating a mesh, grid or irregular

```
mosser@azrael A2 % java -jar generator/generator.jar -k grid -h 1080 -w 1920 -p 1000 -s 20 -o img/grid.mesh
mosser@azrael A2 % java -jar generator/generator.jar -k irregular -h 1080 -w 1920 -p 1000 -s 20 -o img/irregular.mesh
```

One can run the generator with `-help` as option to see the different command line arguments that are available

### Visualizing a mesh, (regular or debug mode)

```
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/grid.mesh -o img/grid.svg          
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/grid.mesh -o img/grid_debug.svg -x
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/irregular.mesh -o img/irregular.svg   
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/irregular.mesh -o img/irregular_debug.svg -x
```

Note: PDF versions of the SVG files were created with `rsvg-convert`.


### Product Backlog

| Id | Feature title | Who? | Start | End | Status |
|:--:|---------------|------|-------|-----|--------|
|F01 | New vertice class| Jiaming Li | 2/12 | 2/12 | D |
|F02 | New segment class| Jiaming Li | 2/12 | 2/12 | D |
|F03 | New polygonADT class| Jiaming Li | 2/12 | 2/12 | D |
|F04 | New mesh class| Jiaming Li | 2/12 | 2/12 | D |
|F05 | New generator| Jiaming Li | 2/12 | 2/13 | D |
|F06 | RemoveDuplicates| Jiaming Li | 2/14 | 2/14 | D |
|F07 | SetsScale| Jiaming Li | 2/14 | 2/14 | D |
|F08 | GetCentroid| Jiaming Li | 2/15 | 2/15 | D |
|F09 | GetNeighbours| Jiaming Li | 2/15 | 2/15 | D |
|F10 | SetColor| Changhao Wu | 2/17 | 2/18 | D |
|F11 | id | Changhao Wu | 2/23 | 2/24 | D |
|F12 | transform | Changhao Wu Jiaming Li | 2/23 | 2/24 | D |
|F13 | UML | xiaotian Lou | 2/25 | 2/27 | D |
|F14 | Cli | xiaotian Lou | 2/25 | 2/27 | D |
|F15 | draw segment | xiaotian Lou | 2/10 | 2/15 | D |
|F16 | visulization | xiaotian Lou | 2/10 | 2/15 | D |
|F17 | visulizationDEbug | xiaotian Lou | 2/10 | 2/15 | D |
|F18 | runconfig | xiaotian Lou | 2/10 | 2/15 | D |
|F19 | SwitchMode | xiaotian Lou | 2/10 | 2/15 | D |
|F20 | Foundneighbourindebug | xiaotian Lou | 2/10 | 2/15 | D |
|F21 | addthcikness | xiaotian Lou | 2/10 | 2/15 | D |
|F22 | addAlphaimplement | xiaotian Lou | 2/10 | 2/15 | D |
|F23 | Bonus | xiaotian Lou | 2/26 | 2/27 | D |
|FA01 | Accept mesh to adt | xiaotian Lou |  | |  |
