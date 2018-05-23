/*
 The TurnoverRotor class is the child of the BasicRotor abstract class
 @turnoverPosition is put in order to rotate once again the rotor when the position exceeds the same value
 @nextRotor stores the next rotor if the rotor is placed in slot 0 or 1, to be rotated if needed
 */
public class TurnoverRotor extends BasicRotor {
     private int turnoverPosition;
     private BasicRotor nextRotor;
    public TurnoverRotor(String name) {
        super(name);
        this.initialise(name);
    }

    @Override
    public void initialise(String type) {
        if (type == "I") {
            this.mapping = RotorI;
            turnoverPosition = 24;
        } else if (type == "II") {
            this.mapping = RotorII;
            turnoverPosition = 12;
        } else if (type == "III") {
            this.mapping = RotorIII;
            turnoverPosition = 3;
        } else if (type == "IV") {
            this.mapping = RotorIV;
            turnoverPosition = 17;
        } else if (type == "V") {
            this.mapping = RotorV;
            turnoverPosition = 7;
        }
        for (int i = 0; i < 26; i++) {
            inverseMapping[mapping[i]] = i;
        }
    }

    public void setNextRotor(BasicRotor newR)
    {
        nextRotor= newR;

    }
    /*
    rotate method rotates the nextRotor if the turnoverPosition is the same with the position
    the other rotate method comes from the BasicRotor class
     */
    public void rotate()
    {
        position = (getPosition() + 1)%ROTORSIZE;
        if(nextRotor!=null) {
            if ((getPosition()) == turnoverPosition) {
                nextRotor.rotate();
            }
        }
    }
}