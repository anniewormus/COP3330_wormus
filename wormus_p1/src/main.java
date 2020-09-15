import java.util.Scanner;

public class main {
    public static void main(String args[]){

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter your number: ");
        String num = scan.next();

        //create encrypter object and call encrypt function
        Encrypter crypty = new Encrypter();
        String encryptedNum = crypty.encrypt(num);

        System.out.println("Encrypted number: " + encryptedNum);

        //create encrypter object and call encrypt function
        Decrypter cryptor = new Decrypter();
        String decryptedNum = cryptor.decrypt(num);

        System.out.println("Decrypted number: " + decryptedNum);

    }
}

