import java.util.Arrays;

public class Decrypter {
    public Decrypter(){
    }

    public String decrypt(String num) {
        int intNum = Integer.parseInt(num);
        int digits[] = new int[4];

        //splices integer into an array of ints
        for(int i = 3; i >= 0; i--){
            digits[i] = reverseDigitConversion(intNum%10);
            intNum = intNum/10;
        }

        //swap(digits[0], digits[2]);
        int temp = digits[0];
        digits[0] = digits[2];
        digits[2] = temp;

        //swap(digits[1], digits[3]);
        temp = digits[1];
        digits[1] = digits[3];
        digits[3] = temp;

        //returns a string and gets rid of wacky [], symbols
        return Arrays.toString(digits).replaceAll("\\[|\\]|,|\\s", "");
    }

    //undo's encryption algorithm
    int reverseDigitConversion(int val){
        int temp = val % 10;
        temp += 3;
        temp = temp % 10;
        return temp;
    }
}
