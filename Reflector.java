/*
 The Reflector class is the child of the Rotor abstract class
 @ReflectorI the mapping for the first reflector
 @ReflectorII the mapping for the second reflector
 */
public class Reflector extends Rotor {

    private int[] ReflectorI= new int[]{ 24, 17, 20, 7, 16, 18, 11, 3, 15, 23, 13, 6, 14, 10, 12, 8, 4, 1, 5, 25, 2, 22, 21, 9, 0, 19 };
    private int[] ReflectorII= new int[]{ 5, 21, 15, 9, 8, 0, 14, 24, 4, 3, 17, 25, 23, 22, 6, 2, 19, 10, 20, 16, 18, 1, 13, 12, 7, 11 };

    /*
    Reflector method= constructor which takes the name and initialise the mapping
     */
    public Reflector(String name)
    {
        this.name=name;
        this.initialise(name);
    }

    public void initialise(String choosen)
    {
        if(choosen=="ReflectorI")
        {
            this.name= choosen;
            this.mapping= ReflectorI;
        }
        if(choosen=="ReflectorII")
        {
            this.name= choosen;
            this.mapping= ReflectorII;
        }
    }
    /*
    substitute method returns the number corresponding to that element of the array
     */
    public int substitute (int change)
    {
        return this.mapping[change];
    }
    /*
    the next methods have no use in this class, therefore they are left empty
     */
    public void rotate() {}
    public int substituteBack(int letter) { return 0;}
    public void setNextRotor(BasicRotor br) {}

}
