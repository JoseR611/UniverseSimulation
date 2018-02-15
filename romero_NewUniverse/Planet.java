/**
 * Planet --- class to calculate and display location of planet objects
 * @author    Jose Romero
 */
public class Planet{
    private double posX;
    private double posY;
    private double velX;
    private double velY;
    private double mass;
    private String object;
    private double G = 6.67E-11;
    private int counter = 0;
    
    /**
     * Constructor assigns attributes to appropriate values
     * @param px A double with the value of the x-position of the Planet object
     * @param py A double with the value of the y-position of the Planet object
     * @param vx A double with the value of the x-velocity of the Planet object
     * @param vy A double with the value of the y-velcoity of the Planet object
     * @param m A double with the value of the mass of the Planet object
     * @param file A String containing the name of the picture of the Planet object
     * @return No return value
     */
    public Planet(double px, double py, double vx, double vy, double m, String file){
        posX = px;
        posY = py;
        velX = vx;
        velY = vy;
        mass = m;
        object = file;
    }
    
    /**
     * Draws the current planet with positionX and positionY
     * @exception
     * @return Void return value
     */
    public void display(){
        StdDraw.picture(posX, posY, object);
    }
    
    /**
     * Updates the position and velocity of the planet
     * @param fx A double containing the net force in the x-direction
     * @param fy A double containing the net force in the y-direction, 
     * @param dt A double containing the interval of time passed
     * @exception
     * @return Void return value
     */
    public void updatePosition (double fx, double fy, double dt){
        double accelX = fx / mass;
        double accelY = fy / mass;
        velX = velX + dt * accelX;
        velY = velY + dt * accelY;
        posX = posX + dt * velX;
        posY = posY + dt * velY;
    }
    
    /**
     * Calculates the force between two Planet objects in the x-direction
     * @param other A Planet object containing the position of the planet
     * @exception
     * @return Returns the force applied on the planet in the x-direction as a double
     */
    public double calculateFX (Planet other){
        double betweenParticles = Math.sqrt(Math.pow(other.posX - this.posX, 2) + Math.pow(other.posY - this.posY, 2));
        double totalForce = G * other.mass * this.mass / Math.pow(betweenParticles,2);
        double FX = totalForce * ((other.posX - this.posX) / betweenParticles);
        return FX;
    }

    /**
     * Calculates the force between two Planet objects in the y-direction
     * @param other A Planet object containing the position of the planet
     * @exception
     * @return Returns the force applied on the planet in the y-direction
     */
    public double calculateFY (Planet other){
        double betweenParticles = Math.sqrt(Math.pow(other.posX - this.posX, 2) + Math.pow(other.posY - this.posY, 2));
        double totalForce = G * other.mass * this.mass / betweenParticles;
        double FY = totalForce * ((other.posY - this.posY) / Math.pow(betweenParticles, 2));
        return FY;
    }
    
}
