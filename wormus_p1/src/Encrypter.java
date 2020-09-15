import java.util.Arrays;

public class Encrypter {

    public Encrypter(){
    }

    public String encrypt(String num){

        int intNum = Integer.parseInt(num);
        int digits[] = new int[4];

        for(int i = 3; i >= 0; i--){
            digits[i] = digitConversion(intNum%10);
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

        return Arrays.toString(digits).replaceAll("\\[|\\]|,|\\s", "");
    }
    //encrypt by adding 7 and modulating by 10
    int digitConversion(int val){
        int temp = val % 10;
        temp += 7;
        temp = temp % 10;
        return temp;
    }

    //problem with scope/static I think
/*    void swap(int x, int y){
        int temp = x;
        x = y;
        y = temp;
    }*/
}