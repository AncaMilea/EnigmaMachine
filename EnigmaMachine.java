import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
/*
@myPlugboard is an object from Plugboard class
@reflector is an object from Reflector class
@slots is an array of BasicRotors, its porpouse is to take in 3 Rotors
@reader is a constant used to fill with a message coming from the user interface
 */
public class EnigmaMachine {
    private Plugboard myPlugboard;
    private Reflector reflector;
    private BasicRotor[] slots = new BasicRotor[3];
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    //EnifmaMachine is the constructor which initialise a Plugboard
    public EnigmaMachine() {
        myPlugboard = new Plugboard();
    }

    //addPlug verifies if it is possible to create a new plug, if it is, it creates it
    public void addPlug(char end1, char end2) {
        myPlugboard.addPlug(end1, end2);
    }

    //clearPlugboard accesses the clear method from Plugboard class
    public void clearPlugboard() {
        myPlugboard.clear();
    }

    //addRotor initialise each rotor to a slot in the Array
    public void addRotor(BasicRotor Br, int slot) {
        slots[slot] = Br;
    }

    public Rotor getRotor(int slot) {
        return slots[slot];
    }

    //addReflector sets the value of reflector
    public void addReflector(Reflector r) {
        reflector = r;
    }

    public Reflector getReflector() {
        return this.reflector;
    }

    //setPosition is used to set the position of the rotors
    public void setPosition(int slot, int positionInitialise) {
        slots[slot].setPosition(positionInitialise);
    }

    //encodeLetter gets letterToEncode through the pseudo-code explained in the Coursework
    public char encodeLetter(char letterToEncode) {
        //encodeLetter gets the substituted character from the Plugboard
        char encodedLetter = myPlugboard.substitute(letterToEncode);

        //using the ASCII table the letter becomes an integer
        int encodedIndex = encodedLetter - 'A';

        //this for loops through the rotors and maps the encodedIndex to a different mapping based on positions
        for (int i = 0; i < slots.length; i++)
        {
            encodedIndex = getRotor(i).substitute(encodedIndex);
        }

        //encodedIndex gets to pe reflected through another substitution
        encodedIndex = getReflector().substitute(encodedIndex);

        //this for loops through the rotors and maps the encodedIndex to a different inverseMapping based on positions
        for (int i = slots.length - 1; i >= 0; i--)
        {
            encodedIndex = getRotor(i).substituteBack(encodedIndex);
        }

        //encodedLetter gets the letter substituted from Plugboard, if any
        encodedLetter = myPlugboard.substitute((char) (encodedIndex + 'A'));

        //first rotor is rotated by one position in order to not have the same mapping again
        slots[0].rotate();

        return encodedLetter;
    }

    /*
    It creates the enigma machine by adding lugs, rotors, reflector and positions
    @StringBuilder class is designed for use as a drop-in replacement for StringBuffer
    @stringToCharArray is an Array of chars from a string
    append method always adds these characters at the end of the builder
    toString method returns a String in replace of the StringBuilder
    ANSWER: BADGER
     */
    public String start1(String decode) throws Exception {
        this.addPlug('A', 'M');
        this.addPlug('G', 'L');
        this.addPlug('E', 'T');
        this.addRotor(new BasicRotor("I"), 0);
        this.setPosition(0,6);
        this.addRotor(new BasicRotor("II"), 1);
        this.setPosition(1,12);
        this.addRotor(new BasicRotor("III"), 2);
        this.setPosition(2,5);
        this.addReflector(new Reflector("ReflectorI"));

        StringBuilder sb = new StringBuilder();
        char[] stringToCharArray = decode.toCharArray();
        for (char letter : stringToCharArray) {
            sb.append(encodeLetter(letter));
        }

        return sb.toString();
    }

    //same as start1. ANSWER:SNAKE
    public String start2(String decode) throws Exception {
        this.addPlug('B', 'C');
        this.addPlug('R', 'I');
        this.addPlug('S', 'M');
        this.addPlug('A', 'F');
        this.addRotor(new BasicRotor("IV"), 0);
        this.setPosition(0,23);
        this.addRotor(new BasicRotor("V"), 1);
        this.setPosition(1,4);
        this.addRotor(new BasicRotor("II"), 2);
        this.setPosition(2,9);
        this.addReflector(new Reflector("ReflectorII"));

        StringBuilder sb = new StringBuilder();
        char[] stringToCharArray = decode.toCharArray();
        for (char letter : stringToCharArray) {
            sb.append(encodeLetter(letter));
        }

        return sb.toString();
    }
    /*
    The only difference between start1 and start3 is the fact that here it uses TurnoverRotor
    setNextRotor remembers the next rotor for the rotation
    ANSWER: THEQUICKBROWNFOXJUMPEDOVERTHELAZYDOG
     */
    public String start3(String decode) throws Exception {
        this.addPlug('Q', 'F');
        this.addRotor(new TurnoverRotor("I"), 0);
        this.setPosition(0,23);
        this.addRotor(new TurnoverRotor("II"), 1);
        this.setPosition(1,11);
        slots[0].setNextRotor(slots[1]);
        this.addRotor(new TurnoverRotor("III"), 2);
        this.setPosition(2,7);
        slots[1].setNextRotor(slots[2]);
        this.addReflector(new Reflector("ReflectorI"));

        StringBuilder sb = new StringBuilder();
        char[] stringToCharArray = decode.toCharArray();
        for (char letter : stringToCharArray) {
            sb.append(encodeLetter(letter));
        }

        return sb.toString();
    }

    /*
    bomb1 is the same with star1 but without plugs
    the plugs are created in the Bombe class
    ANSWER: DAISYDAISYGIVEMEYOURANSWERDO  plugs: D-T S-A
     */
    public String bombe1(String decode) throws Exception {
        this.addRotor(new BasicRotor("IV"), 0);
        this.setPosition(0,8);
        this.addRotor(new BasicRotor("III"), 1);
        this.setPosition(1,4);
        this.addRotor(new BasicRotor("II"), 2);
        this.setPosition(2,21);
        this.addReflector(new Reflector("ReflectorI"));

        StringBuilder sb = new StringBuilder();
        char[] stringToCharArray = decode.toCharArray();
        for (char letter : stringToCharArray) {
            sb.append(encodeLetter(letter));
        }
        return sb.toString();
    }

    /*
    bombe2 gets in addition 3 parameters: i,j,k each of them are a position for the BasicRotors
    ANSWER: WELLALWAYSBETOGETHERHOWEVERFARITSEEMSWELLALWAYSBETOGETHERTOGETHERINELECTRICDREAMS Position 1: 3 Position 2: 9 Position 3: 15
     */
    public String bombe2(String decode, int i, int j, int k) throws Exception {
        this.addPlug('H', 'L');
        this.addPlug('G', 'P');
        this.addRotor(new BasicRotor("V"), 0);
        this.setPosition(0,i);
        this.addRotor(new BasicRotor("III"), 1);
        this.setPosition(1,j);
        this.addRotor(new BasicRotor("II"), 2);
        this.setPosition(2,k);
        this.addReflector(new Reflector("ReflectorI"));

        StringBuilder sb = new StringBuilder();
        char[] stringToCharArray = decode.toCharArray();
        for (char letter : stringToCharArray) {
            sb.append(encodeLetter(letter));
        }
        return sb.toString();
    }

    /*
    bombe3 gets 3 parameters: i,j,k
    each of them will take a position in the rotor string Array being a rotor for the enigma
    ANSWER: ILOVECOFFEEILOVETEAILOVETHEJAVAJIVEANDITLOVESME Rotor 1: V Rotor 2: III Rotor 3: II
     */
    public String bombe3(String decode, int i, int j, int k) throws Exception {
        String[] rotor = new String[]{"I", "II", "III", "IV", "V"};
        this.addPlug('M', 'F');
        this.addPlug('O', 'I');
        this.addRotor(new BasicRotor(rotor[i]), 0);
        this.setPosition(0,22);
        this.addRotor(new BasicRotor(rotor[j]), 1);
        this.setPosition(1,24);
        this.addRotor(new BasicRotor(rotor[k]), 2);
        this.setPosition(2,23);
        this.addReflector(new Reflector("ReflectorI"));

        StringBuilder sb = new StringBuilder();
        char[] stringToCharArray = decode.toCharArray();
        for (char letter : stringToCharArray) {
            sb.append(encodeLetter(letter));
        }
        return sb.toString();
    }
    /*
    correctIn is an extension method which includes the facility to convert the input file into appropriate text for encoding/decoding.
    @replaceAll replaces each substring of this string that matches the given regular expression with the given replacement.
    in this case if there is anything else than letters it replaces it with ""
    @toUpperCase sets all the letters to Upper letters
     */
    static public String correctIn(String input)
    {
        input= input.replaceAll("[^a-zA-Z]","");
        input= input.toUpperCase();

        return input;
    }

    /*
    readUser is the extension which allows the user to specify the plugs in the machine and the type and initial positions of the rotors
    @decoded is the message given by the user
    @rotor is the name of the rotor
    @reflector is the name of the reflector
    @plug is the number of plugs willing to create
    @position gets the value of the position
    readLine function reads the next line inserted
    Integer.parseInt used to get the primitive data type of a certain String
    @exception IOException On input error
    @see IOException
     */
    public void writeUser() throws IOException {

        String decoded, rotor, reflector;
        char letter1, letter2;
        int plug=0, position;
        System.out.println("Please, insert the message you want to encode: ");
        decoded= reader.readLine();
        decoded= correctIn(decoded);
        System.out.println("How many plugs do you want? [Max 13] ");
        plug= Integer.parseInt(reader.readLine());
        while(plug>0)
        {
            System.out.println("Enter letter 1 for plug: ");
            letter1=correctIn(reader.readLine()).charAt(0);
            System.out.println("Enter letter 2 for plug: ");
            letter2=correctIn(reader.readLine()).charAt(0);
            this.addPlug(letter1, letter2);
            plug--;
        }
        for(int i=0; i<3;i++)
        {
            System.out.println("Enter the type of the rotor [I,II,III,IV,V] ");
            rotor= correctIn(reader.readLine());
            this.addRotor(new TurnoverRotor(rotor), i);
            System.out.println("Give the position for the rotor: ");
            position= Integer.parseInt(reader.readLine());
            this.setPosition(i,position);
        }
        System.out.println("Enter the type of the reflector [I,II] ");
        reflector= correctIn(reader.readLine());
        if(reflector== "I"){
            Reflector myReflector= new Reflector("ReflectorI");
            this.addReflector(myReflector);
        }else
        {
            Reflector myReflector= new Reflector("ReflectorII");
            this.addReflector(myReflector);
        }

        StringBuilder sb = new StringBuilder();
        char[] stringToCharArray = decoded.toCharArray();
        for (char letter : stringToCharArray) {
            sb.append(encodeLetter(letter));
        }

        System.out.println("The message encoded is: "+sb.toString());
    }

    /*
    main method creates a new enigma machine called e
    it calls the writeUser method
    @param args Unused.
    @return Nothing.
    @exception IOException On input error
    @see IOException
     */
    public static void main(String [ ] args) throws IOException {
        EnigmaMachine e= new EnigmaMachine();
        e.writeUser();
    }
}
