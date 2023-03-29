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
mosser@azrael A2 % java -jar generator/generator.jar -k irregular -h 1920 -w 1920 -p 1000 -r 5 -o ireg.mesh
```

One can run the generator with `-help` as option to see the different command line arguments that are available

### Visualizing a mesh, (regular or debug mode)

```
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/grid.mesh -o img/grid.svg          
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/grid.mesh -o img/grid_debug.svg -x
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/irregular.mesh -o img/irregular.svg   
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/irregular.mesh -o img/irregular_debug.svg -x
```

### Create the graph
```
mosser@azrael A2 % java -jar island/island.jar -i xxxxxx -o xxxxx -help    
mosser@azrael A2 % java -jar island/island.jar -help
mosser@azrael A2 % java -jar island/island.jar -i IOArea/inputoff.mesh -o IOArea/lagoon.mesh --mode lagoon   
mosser@azrael A2 % java -jar island/island.jar -i IOArea/inputoff.mesh -o IOArea/test.mesh   
mosser@azrael A2 % java -jar island/island.jar -i IOArea/inputoff.mesh -o IOArea/test.mesh 
mosser@azrael A2 % java -jar island/island.jar -seed 42939
mosser@azrael A2 % java -jar island/island.jar -seed 42939 -biomes Tropical_Rain_Forest


seed is a positive integer

usage: java -jar island.jar
 -al <arg>         Seed for generator altitude
 -aq <arg>         number of aquifers
 -b <arg>          biomes name
 -help             print help message
 -i <arg>          input mesh adress
 -l <arg>          max number of lake lake
 -m,--mode <arg>   type lagoon to activate MVP mode, default is seed
                   generator mode
 -o <arg>          adress of output mesh
 -r <arg>          number of river
 -s <arg>          seed for soil
 -seed <arg>       global seed
 -shape <arg>      Seed for shape

  



```


Note: PDF versions of the SVG files were created with `rsvg-convert`.
### Feature plan
| Feature(id) | Feature title | Who? | Start | End | Status |
|:--:|---------------|------|-------|-----|--------|
|F01 | MVP| xiaotian lou | 3/22 | 3/26 | D |
|F02 | Reproducibility and seed| xiaotian lou | 3/25 | 3/26 | D |
|F03 | Shape and Reproducibility| xiaotian lou | 3/25| 3/26 | D |
|F04 | Elevation and Reproducibility| xiaotian lou | 3/25 | 3/26 | D |
|F05 | test case| xiaotian lou | 3/25 | 2/13 | D |
|F06 | generation and vislise depoly| xiaotian lou | 3/25 | 3/26 | D |
|F7 | Lakes| Changhao Wu | 3/25 | 3/26 | D |
|F8 | Rivers| Changhao Wu | 3/25 | 3/26 | D |
|F9 | River flow| Changhao Wu | 3/25 | 3/26 | D |
|F10 | Aquifers| Changhao Wu | 3/25 | 3/26 | D |
|F11 | SetColor| Changhao Wu | 3/25 | 3/26 | D |
|F12 |Biomes| Jiaming Li | 3/25 | 3/26 | D |
|F13 |Soil absorption| Jiaming Li | 3/25 | 3/26 | D |
|F14| Whittaker Diagrams| Jiaming Li | 3/25 | 3/26 | D |
