import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.crypto.KeyGenerator;
import java.util.Iterator;

/**
 * Encryption
 */
public class Encryption {

    Encryption() {
    }

    // Description: Encrypts a message using ascii values 
    public ArrayList<Character> encrypt(String message) {

        int lengthOfMessage = message.length();
        char testLetter;
        int ascii;
        ArrayList<Integer> presecretMessage = new ArrayList<Integer>(lengthOfMessage);
        ArrayList<Integer> intMessage = new ArrayList<Integer>(lengthOfMessage);
        ArrayList<Character> secretMessage = new ArrayList<Character>(lengthOfMessage);
        ArrayList<Integer> key = new ArrayList<Integer>(lengthOfMessage);
        int sum; 
        Character cipher;


        for (int i = 0; i < lengthOfMessage; i++) {

            // Get char value from message 
            testLetter = message.charAt(i);

            // Convert char to ascii value
            ascii = (int) testLetter;

            // Store ascii value in array
            presecretMessage.add(i ,ascii);
        }

        // Use key for encryption 
        key = this.keyGenerator(lengthOfMessage);

        System.out.println("key: ");
        for (int i =  0; i < key.size(); i++) {
            System.out.print(key.get(i) + " ");
        }

        for (int i = 0; i < lengthOfMessage; i++) {

            // Add preselected value + key value
            sum = presecretMessage.get(i) + key.get(i);

            // divide the sum by 2 and add it the intMessage list 
            intMessage.add(i, sum/2);
        }

        System.out.println(" ");
        System.out.println("postsum: ");

        int n = 0;
        for (int i =  0; i < lengthOfMessage; i++) {
            n = intMessage.get(i);
            cipher = (char) n;
            System.out.print(cipher);
        }
        System.out.println("");

        return secretMessage;
    }

    public ArrayList<Integer> keyGenerator(int lengthOfMessage) {
        
        // ASCII limits
        int lowerLimit = 32;
        int upperLimit = 126;


        // Array that will store the encryption key
        ArrayList<Integer> arrayList = new ArrayList<Integer>(lengthOfMessage);

        int currentRandomValue = 0;

        // Init randomizer 
        Random rand = new Random();


        for (int i = 0; i < lengthOfMessage; i++) {

            // Range-random formula
            currentRandomValue = rand.nextInt((upperLimit - lowerLimit) + 1) + lowerLimit;

            // Store random value in array 
            arrayList.add(i ,currentRandomValue);
        }

        return arrayList;
    }

    public static void main(String[] args) {
        String message = "how are you";
        Encryption box = new Encryption(); 
        System.out.println(message);
        ArrayList<Character> newMessage = box.encrypt(message);

    }
    
}