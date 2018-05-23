
public class Plug {
    private char end1, end2;
    /*
    Plug method is a constructor which initialise a plug through 2 ends (letters). The plug will be used to substitute letters between themselves.
    @End1 is the first letter
    @End2 is the second letter
     */
    public Plug(char End1, char End2)
    {
        setEnd1(End1);
        setEnd2(End2);
    }
    /*
    getEnd1/2 methods return the ends depending on which of the two is needed. Good for encapsulation.
     */
    public char getEnd1()
    {
        return end1;
    }
    public char getEnd2()
    {
        return end2;
    }

    /*
    setEnd1/2 methods initialise each end to a letter. It is good for encapsulation.
     */
    public void setEnd1(char End1)
    {
        end1= End1;
    }
    public void setEnd2(char End2)
    {
        end2= End2;
    }
    /*
    encode method switches the letter in the plug, if there is one for that character.
    @letterIn is the letter provided to be switched.
     */
    public char encode(char letterIn)
    {
        if(letterIn==this.getEnd1())
        {
            return end2;
        }
        else{
            if(letterIn==this.getEnd2())
                return end1;
        }
        return letterIn;
    }
    /*
    @Boolean methods are specific methods which returns only true or false.
    clashesWith method verifies if there already exists a plug with one of the ends provided.
    It compares the ends of the two plugs, if there is a common end it returns true (it clashes),
    if there is no plug it returns false (that plug can be created)
     */
    public Boolean clashesWith(Plug plugin)
    {
        if((this.getEnd1()==plugin.getEnd1()) || (this.getEnd2()==plugin.getEnd2()) || (this.getEnd2()==plugin.getEnd1()) || (this.getEnd1()==plugin.getEnd1())  )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
