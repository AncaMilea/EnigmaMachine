
public class Plugboard {
    private Plug[] plugsArray;
    private int numPlugs;
    /*
    Plugboard method is a constructor which initialise an Array of plugs (max 13 plugs)
    Sets the number of plugs on 0;
    @numPlugs the number of plugs
     */
    public Plugboard()
    {
        this.plugsArray= new Plug[13];
        numPlugs=0;
    }
    /*
    addPlug methods creates new plugs after it verifies there is no clash with other plugs.
    @newPlug uses the constructor to initialise the new plug
    @plugsArray is an Array of plugs
    This method uses getNumPlugs() method
     */
    public Boolean addPlug(char end1, char end2)
    {
        Plug newPlug= new Plug(end1,end2);
        plugsArray[numPlugs]= newPlug;
        for( int i=0; i<numPlugs; i++)
        {
            if(newPlug.clashesWith(plugsArray[i]))
            {
                plugsArray[this.numPlugs]=null;
                return true;
            }
        }
        numPlugs= getNumPlugs()+1;
        return false;

    }
    /*
    getNumPlugs is an accessor method
     */
    public int getNumPlugs()
    {
        return this.numPlugs;
    }
    /*
    clear method empties the plugs Array
     */
    public void clear()
    {
        for(int i=0; i<this.numPlugs; i++)
        {
            plugsArray[i]=null;
        }
        this.numPlugs= 0;
    }
    /*
    substitute method searches to change the input letter with the one connected to its plug, if any.
    @newLetter is the letter given to be substituted
    @testingPlug takes each plug from the array to verify
    @letterReturn is the encoded newLetter
     */
    public char substitute(char newLetter)
    {
        Plug testingPlug;
        char letterReturn= newLetter;
        for(int i=0; i<this.numPlugs; i++)
        {
            testingPlug= plugsArray[i];
            if(testingPlug.getEnd1()==newLetter || testingPlug.getEnd2()==newLetter)
            {
                letterReturn= testingPlug.encode(newLetter);
            }
        }
        return letterReturn;
    }


}
