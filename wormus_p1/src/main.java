import java.util.Scanner;

public class main {
    public static void main(String args[]){

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter your number: ");
        String num = scan.next();

        //create encrypter object and call encrypt function
        Encrypter crypt = new Encrypter();
        String encryptedNum = crypt.encrypt(num);

        System.out.println("Encrypted number: " + encryptedNum);

    }
}

