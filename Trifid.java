import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trifid {

    char[][] sqare1 = {
            {'0','0','0'},
            {'0','0','0'},
            {'0','0','0'}
    };

    char[][] sqare2 = {
            {'0','0','0'},
            {'0','0','0'},
            {'0','0','0'}
    };

    char[][] sqare3 = {
            {'0','0','0'},
            {'0','0','0'},
            {'0','0','0'}
    };

    List<char[][]> sqares = new ArrayList<>();

    String alphabet = "abcdefghiklmnopqrstuvwxyz";

    public Trifid(){


        initSqares();
        printSqares();


    }

    private void initSqares() {

        this.sqares.add(sqare1);
        this.sqares.add(sqare2);
        this.sqares.add(sqare3);

        char[] abcArr = this.alphabet.toCharArray();
        List<Character> abcList = new ArrayList<>();
        for (char c : abcArr) {
            abcList.add(c);
        }

        Collections.shuffle(abcList);
        abcList.add('+');
        abcList.add('-');

        int k = 0;

        for (char[][] sq : this.sqares){

            for(int i = 0; i < 3;i++){
                for (int j = 0 ; j < 3; j++){

                    sq[i][j] = abcList.get(k);
                    k++;
                }
            }


        }


    }

    private void printSqares(){

        int k = 1;
        for (char[][] sq : this.sqares){

            System.out.println(k +".   1 2 3");
            for (int i=0; i < 3; i++) {
                System.out.print("   " + (i+1) + " ");
                for (int j = 0; j < 3; j++) {
                    System.out.print(sq[i][j] + " " );
                }
                System.out.println();
            }
            System.out.println();
            k++;
        }

    }


    private int getPosInSqares(char c){

        int s = 1;
        int row = 0;
        int col = 0;
        boolean flag = false;
        for (char[][] sq : this.sqares){

            for (row = 0 ; row < 3 ; row++){
                for (col = 0 ; col < 3 ; col++){

                    if (sq[row][col] == c){
                        flag = true;
                        break;
                    }

                }

                if (flag){
                    break;
                }
            }
            if (flag){
                break;
            }
            s++;
        }
        if (flag){

            row++;
            col++;
            return (s*100 + row*10 + col);
        }else {
            throw new InvalidParameterException("Invalid input");
        }
    }

    private char getCharOnPos(int pos){

        if (pos >= 111 && pos <= 999){

            int s = pos/100;
            int row = (pos - (s*100)) / 10;
            int col = pos%10;
            s--;
            row--;
            col--;

            //System.out.println(s + " "+  row + " " + col);

            return this.sqares.get(s)[row][col];

        } else {
            throw new InvalidParameterException("Invalid input!");
        }
    }


    private int[] encrypt(String text){  // takes already TSA text

        int[] retval = new int[text.length()];
        int i = 0;
        for(char c : text.toCharArray()){

            retval[i] = getPosInSqares(c);
            i++;

        }

        return retval;

    }

    private String decrypt(int[] coords){

        StringBuilder sb = new StringBuilder();

        for (int i : coords){

            sb.append(getCharOnPos(i));

        }


        return new String(sb);
    }


    public String doAndReturnEncryption(String text){

        text=Main.textToTSAandRemoveJ(text);
        System.out.println("T - Open text: "+text);

        int[] zt = encrypt(text);

        System.out.print("T - Cryptical text without tricker: ");
       for (int item : zt){
            System.out.print(item);
        }
        System.out.println();

        String ztFinal = Main.intArrayToString(zt);

        return ztFinal;

    }


}
