/*
@name is the type of the Rotor
@position the current position of the rotor
@mapping  is the mapping of the rotor (an array of integers).
@ROTORSIZE  a constant that defines the number of positions on a rotor (26)
Rotor class is abstract because it gives a template for its children
The abstract methods have defined functionality in the children's classes
 */
public abstract class Rotor {
    protected String name;
    protected int position;
    protected int[] mapping= new int[26];
    static final int ROTORSIZE= 26;

    public void setPosition(int newPosition)
    {
        position= newPosition;
    }
    public int getPosition()
    {
        return position;
    }
    abstract void initialise(String choosen);
    abstract int substitute(int change);
    abstract void rotate();
    abstract int substituteBack(int letter);
    abstract void setNextRotor(BasicRotor newr);
}
