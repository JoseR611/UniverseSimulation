/**
 * Universe --- program to display the universe and planets rotating around a sun Planet
 * @author Jose Romero
 */
public class Universe{
    private double endT;
    private double deltaT;
    private String universeFile;
    private Planet [] p;
    
    /**
     * Creates a Universe object, fills in the data for Planet objects, 
     * and runs a simulation of the Planet objects orbiting a sun Planet object
     * @param args A string array containing any commands from the command line
     * @return Void return value
     */
    public static void main(String[] args){
        Universe u = new Universe(157788000.0, 25000.0, "planets.txt");
        u.readUniverse();
        u.runSimulation();
    }
    
    /**
     * Constructor assigns attributes to appropriate values
     * @param endT_init A double with the value to stop the simulation
     * @param deltaT_init A double with the value of change in time for the simulation
     * @param universeFile_init A String with the name of the text file it will use to create the Universe object     * @exception
     * @return No return value 
     */    
    public Universe(double endT_init, double deltaT_init, String universeFile_init){
        endT = endT_init;
        deltaT = deltaT_init;
        universeFile = universeFile_init;
    }
    
    /**
     * Runs a for loop that updates the data on the Planet objects and displays the data of the 
     * Planets on the canvas
     * @param No parameters
     * @return Void return value
     */
    public void runSimulation(){
        for(double t = 0; t < endT; t = t + deltaT){
            updatePhysics();
            displayUniverse();
        }
    }
    
    /**
     * Creates an In object, ultlizing the file as its parameter,
     * as well as setting the X and Y scale of the canvas and filling a Planet array with appropriate data
     * @param No parameters
     * @return Void return value
     */
    public void readUniverse(){
        In planets = new In(universeFile);
        int numOfBodies = planets.readInt();
        double radiusOfUniverse = planets.readDouble();
        p = new Planet [numOfBodies];
        StdDraw.setXscale(-1 * radiusOfUniverse, radiusOfUniverse);
        StdDraw.setYscale(-1 * radiusOfUniverse, radiusOfUniverse);
        for(int i = 0; i < numOfBodies; i++){
            p[i] = new Planet (planets.readDouble(), planets.readDouble(), planets.readDouble(), planets.readDouble(), planets.readDouble(), planets.readString());
        }
    }
    
    /**
     * Displays the background and the Planet objects on the canvas
     * @param No parameters
     * @return Void return value
     */
    public void displayUniverse(){
        StdDraw.enableDoubleBuffering();
        StdDraw.picture(0.0, 0.0, "starfield.jpg");
        for(int i = 0; i < p.length; i++){
            p[i].display();
        }
        StdDraw.show();
        StdDraw.pause(20);
    }
    
    /**
     * Calculates the total force applied in the X and Y direction on a Planet object and updates 
     * the X and Y position based on that data
     * @param No parameters
     * @return Void return value
     */
    public void updatePhysics(){
        In planets = new In(universeFile);
        int numOfBodies = planets.readInt();
        double [] netFXApplied = new double [numOfBodies];
        double [] netFYApplied = new double [numOfBodies];
        for(int i = 0; i < numOfBodies; i++){
            for(int j = 0; j < numOfBodies; j++){
                if(j != i){
                    netFXApplied[i] = netFXApplied[i] + p[i].calculateFX(p[j]);
                    netFYApplied[i] = netFYApplied[i] + p[i].calculateFY(p[j]);
                }
            }
            p[i].updatePosition(netFXApplied[i], netFYApplied[i], deltaT);
            for(int k = 0; k < numOfBodies; k++){
                netFXApplied[i] = 0;
                netFYApplied[i] = 0;
            }
        }
    }
    
}
