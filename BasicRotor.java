/*
@RotorI/II/III/IV/V has the mapping for each rotor
@inverseMapping is an array from the mapping array, changes the position with the value
 */
public class BasicRotor extends Rotor{
    protected int[] RotorI= new int[]{ 4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14, 22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9 };
    protected int[] RotorII= new int[]{ 0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22, 19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21, 14, 4 };
    protected int[] RotorIII= new int[]{ 1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14 };
    protected int[] RotorIV= new int[]{4, 18, 14, 21, 15, 25, 9, 0, 24, 16, 20, 8, 17, 7, 23, 11, 13, 5, 19, 6, 10, 3, 2, 12, 22, 1 };
    protected int[] RotorV= new int[]{ 21, 25, 1, 17, 6, 8, 19, 24, 20, 15, 18, 3, 13, 7, 11, 23, 0, 22, 12, 9, 16, 14, 5, 4, 2, 10 };
    protected int[] inverseMapping= new int[26];
    /*
   BasicRotor method= constructor which takes the name and initialise the mapping
    */
    public BasicRotor(String name)
    {
        this.name=name;
        this.initialise(name);
    }
    /*
    initialise method does the mapping according to the type
    creates the inverseMapping
     */
    public void initialise(String type)
    {
        if(type=="I")
        {
            this.mapping=RotorI;
        }
        else
        if(type=="II")
        {
            this.mapping=RotorII;
        }
        else
        if(type=="III")
        {
            this.mapping=RotorIII;
        }
        else
        if(type=="IV")
        {
            this.mapping=RotorIV;
        }
        else
        if(type=="V")
        {
            this.mapping=RotorV;
        }
        for(int i=0; i<26; i++)
        {
            inverseMapping[mapping[i]]=i;
        }
    }
    /*
    @changeLetter is an integer symbolizing a letter
    substitute method should return the number corresponding to that element of the array after substracting and adding
    the position of the Rotor
    if the substraction is negative the ROTORSIZE is added in order to keep the integer from 0 to 25
    %ROTORSIZE is used to return only integers from 0 to 25, even though changeLetter is over 26
     */
    public int substitute (int changeLetter)
    {
        if(changeLetter - position < 0)
        {
            changeLetter= changeLetter + ROTORSIZE - getPosition();
        }
        else
        {
            changeLetter = changeLetter-getPosition();
        }
        changeLetter=mapping[changeLetter] + getPosition();
        changeLetter=changeLetter % ROTORSIZE;
        return changeLetter;
    }
    /*
    does the same thing as the substitute method, but uses the inverseMapping
    substituteBack method is used after the reflector
     */
    public int substituteBack(int letter) {
        if (letter - getPosition() < 0)
        {
            letter = letter + ROTORSIZE - getPosition();
        }
        else
        {
            letter = letter- getPosition();
        }
        letter = inverseMapping[letter]+ getPosition();

        letter = letter % ROTORSIZE;
        return letter;
    }
    /*
    the rotor is rotated by one position in order to map different
     */
    public void rotate()
    {
        position = (getPosition() + 1) % ROTORSIZE;
    }
    /*
    no use here for setNextRotor
     */
    public void setNextRotor(BasicRotor newr){}
}
