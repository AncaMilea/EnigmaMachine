import java.io.*;

public class Bombe{
    /*
    same as in EnigmaFile reading from files and putting the output in another one
     */
    public static void main(String[] args) throws IOException {
        EnigmaMachine enigma = new EnigmaMachine();
        try {

            File finstream = new File("filein.txt");
            BufferedReader brIn = new BufferedReader(new FileReader(finstream));
            BufferedWriter writeOut = new BufferedWriter(new FileWriter("fileout.txt"));
            //Challenge1(enigma, brIn, writeOut);
            Challenge2(enigma, brIn, writeOut);
            //Challenge3(enigma, brIn, writeOut);
        }
            catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    Challenge 1 gets through 2 fors to create plugs
    contains method sees if the string is containing the parameter
    clearPlugboard is used to erase the two plugs, each time another 2 being initialise
    'A'=65
    it uses bombe1 method from EnigmaMachine
     */
    static public void Challenge1(EnigmaMachine enigma, BufferedReader brIn, BufferedWriter writeOut)
    {
        String current, encode;
        int i,j;
        try
        {
            while ((current = brIn.readLine()) != null) {
                current= enigma.correctIn(current);
                for( i=0; i<26; i++) {
                    for ( j = 0; j < 26; j++) {
                        if(i!=j &&((char) (i+'A'))!='D' &&((char) (i+'A'))!='S'&&((char) (j+'A'))!='D' &&((char) (j+'A'))!='S' )
                        {
                            enigma.addPlug('D', (char) (i+65));
                            enigma.addPlug('S',((char) (j+65)));
                            encode = enigma.bombe1(current);
                            if(encode.contains("ANSWER")) {
                                writeOut.write(encode +" D-" +(char)(i+65)+ " S-" +(char)(j+65));
                                writeOut.newLine();
                            }

                        }
                        enigma.clearPlugboard();
                    }

                }

            }
            writeOut.close();}
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    /*
    Challenge 2 has 3 fors from 0 to 25, i,j,k are the positions of the rotors sent to bombe2
     */
    static public void Challenge2(EnigmaMachine enigma, BufferedReader brIn, BufferedWriter writeOut)
    {
        String current, encode;
        int i,j,k;
        try
        {
            while ((current = brIn.readLine()) != null) {
                current= enigma.correctIn(current);
                for( i=0; i<26; i++) {
                    for ( j = 0; j < 26; j++) {
                        for (k = 0; k < 26; k++) {
                            if (i != j && i!=k && j!=k) {
                                encode = enigma.bombe2(current, i, j, k);
                                if (encode.contains("ELECTRIC")) {
                                    writeOut.write(encode + " Position 1: " + i + " Position 2: " + j + " Position 3: "+k);
                                    writeOut.newLine();
                                }

                            }
                        }
                    }

                }

            }
            writeOut.close();}
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    /*
    Challenge 3 has 3 fors from 0 to 4, i,j,k are the rotors sent to bombe3
     */
    static public void Challenge3(EnigmaMachine enigma, BufferedReader brIn, BufferedWriter writeOut)
    {
        String current, encode;
        String[] rotor = new String[]{"I", "II", "III", "IV", "V"};
        int i,j,k;
        try
        {
            while ((current = brIn.readLine()) != null) {
                current= enigma.correctIn(current);
                for( i=0; i<5; i++) {
                    for ( j = 0; j < 5; j++) {
                        for (k = 0; k < 5; k++) {
                            if (i != j && i!=k && j!=k) {
                                encode = enigma.bombe3(current, i, j, k);
                                if (encode.contains("JAVA")) {
                                    writeOut.write(encode + " Rotor 1: " + rotor[i] + " Rotor 2: " + rotor[j] + " Rotor 3: "+ rotor[k]);
                                    writeOut.newLine();
                                }

                            }
                        }
                    }

                }

            }
            writeOut.close();}
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}