import java.security.InvalidParameterException;
import java.util.*;

public class Polybius {

    char[][] sqare = {
            {'0','0','0','0','0'},
            {'0','0','0','0','0'},
            {'0','0','0','0','0'},
            {'0','0','0','0','0'},
            {'0','0','0','0','0'}
    };
    String alphabet = "abcdefghiklmnopqrstuvwxyz";

    public Polybius(){

        initSqare();
        printSqare();

    }

    private void initSqare(){

        char[] abcArr = this.alphabet.toCharArray();
        List<Character> abcList = new ArrayList<>();
        for (char c : abcArr){
            abcList.add(c);
        }

        Collections.shuffle(abcList);

        int k = 0;
        for (int i=0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                this.sqare[i][j] = abcList.get(k);
                k++;
            }
        }
    }

    private void printSqare(){

        System.out.println("  1 2 3 4 5");
        for (int i=0; i < 5; i++) {
            System.out.print(i+1 + " ");
            for (int j = 0; j < 5; j++) {
                System.out.print(this.sqare[i][j] + " " );
            }
            System.out.println();
        }
        System.out.println();
    }


    private int getPosInSqare(char c){

        int row = 0;
        int col = 0;
        boolean flag = false;

        for (row = 0; row < 5; row++){
            for (col = 0; col < 5; col++){

                if(this.sqare[row][col] == c){
                    flag = true;
                    break;
                }
            }
            if (flag){
                break;
            }
        }

        if (flag){
            col++;
            row++;
            return 10*row + col;
        } else {
            throw new InvalidParameterException("Invalid input!");
        }
    }


    private char getCharOnPos(int pos){

        int row = pos/10;
        int col = pos%10;
        row--;
        col--;


        return this.sqare[row][col];

    }

    private int[] encrypt(String text){ // takes already TSA text

        int[] retval = new int[text.length()];
        int i = 0;
        for (char c : text.toCharArray()){

            retval[i] = getPosInSqare(c);
            i++;
        }

        return retval;
    }

    private String decrypt(int[] coords){

        StringBuilder sb = new StringBuilder();

        for (int i : coords){

            sb.append(getCharOnPos(i));
            System.out.println(i);

        }


        return new String(sb);
    }


    public String doAndReturnEncryption(String text){

        text=Main.textToTSAandRemoveJ(text);
        System.out.println("P - Open text: "+text);

        int[] zt = encrypt(text);
        System.out.print("P - Cryptical text without trickers: ");
        for (int item : zt){
            System.out.print(item);
       }
        System.out.println();

        String ztFinal = Main.intArrayToString(zt);

        return ztFinal;

    }



}
