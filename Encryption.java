/**
 * Encryption
 */
public class Encryption {

    Encryption() {
    }

    String encrypt(String message) {
        String codedMessage = "somemessage";
        String parts [] = message.split("a");
        return parts;
    }

    public static void main(String[] args) {
        String m = "how are you";
        Encryption box = new Encryption(); 

        String newMessage [] = box.encrypt(m);
        

    }
    
}