import java.io.*;

public class EnigmaFile {
    /*
    cretes a new enigma machine
    @finstream is a File object which memorize the pathname from filein.txt
    @brIn is a BufferedReader which gets the input string
    @writeOut is a BufferedWriter which gets the output string
    newLine is a function which sets the next message to a new line

     */
    public static void main(String[] args)
    {
        EnigmaMachine enigma= new EnigmaMachine();
    try{

        File finstream= new File("filein.txt");
        BufferedReader brIn= new BufferedReader(new FileReader(finstream));
        BufferedWriter writeOut= new BufferedWriter(new FileWriter("fileout.txt"));
        String current, encode;

        while((current= brIn.readLine()) != null)
        {
            current= enigma.correctIn(current);
            encode= enigma.start1(current);
            //encode= enigma.start2(current);
            //encode= enigma.start3(current);
            writeOut.write(encode);
            writeOut.newLine();


        }
        //closes the fileout.txt
        writeOut.close();
    }
    catch(Exception e)
    {
        System.err.println(e);
    }

    }
}
