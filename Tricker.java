import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Tricker {

    public static String A(String zt){


        char[] ztChar = zt.toCharArray();
        StringBuilder sb = new StringBuilder();

        List<Character> ztList = new ArrayList<>();

        for (char c : ztChar) {
            ztList.add(c);
        }


        Collections.swap(ztList, 0 , ztList.size() -1 );

        for (char item : ztList){
            sb.append(item);
        }


        return new String(sb);

    }

    public static String B(String zt, int n, char variation){   // zasifrovany text, pocet cislic n, sposob (a/b/c)

        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        List<Integer> ztList = new ArrayList<>();

        int digit = 0;

        switch(n){
            case 1:  //b1x
                digit = rnd.nextInt(10);
                break;
            case 2:  //b2x
                digit = rnd.nextInt(100 - 10) + 10;
                break;
            case 3:   //b3x
                digit = rnd.nextInt(1000 - 100) + 100;
                break;
        }



        for (char c : zt.toCharArray()){
            ztList.add(Character.getNumericValue(c));
        }

        switch (variation){

            case 'a': // bna
                ztList.add(0 , digit);
                break;

            case 'b':  //bnb
                ztList.add(zt.length() , digit);
                break;

            case 'c':  //bnc
                ztList.add(0 , digit);
                ztList.add(zt.length() + 1, digit);
                break;
        }


        return Main.intListtoString(ztList);
    }


    public static String C(String zt) {


        char[] ztChar = zt.toCharArray();

        List<Character> ztList = new ArrayList<>();

        for (char c : ztChar) {
            ztList.add(c);
        }

        boolean addZeroToEnd = false;

        if (ztList.size()%5 == 0){
            addZeroToEnd = true;
        }

        for (int i = 5; i < ztList.size(); i+=6){

            ztList.add(i,'0');
        }

        if (addZeroToEnd){
            ztList.add('0');
        }

        return Main.charListtoString(ztList);
    }


}
