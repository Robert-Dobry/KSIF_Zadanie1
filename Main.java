import java.io.*;
import java.text.Normalizer;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        // 100.txt --> P_100.txt & T_100.txt
        // 500.txt
        // 1000.txt

        // prve spustenie = vytvori subory P_XXX.txt, T_XXX.txt = ZT bez klamacov
        // druhe spustenie vytvori subory s klamacmi
        // tretie spustenie by znova aplikovalo klamace na povodne ZT bez klamacov
        // t.j. pre nove vytvorenie klucov treba vymazat vsetky subory okrem 100.txt, 500.txt, 1000.txt.
        // novy kluc sa generuje pri spusteni pokial neexistuju subor P/T_XYZ.txt

        File file = new File("P_100.txt");
        if (file.exists()) {
            doTrickerEnc();
            System.exit(0);
        } else {
            doNoTrickerEnc();
            System.exit(0);
        }

    }


    public static String textToTSAandRemoveJ(String text) {

        Normalizer.Form NFD = Normalizer.Form.NFD;

        text = Normalizer.normalize(text.toLowerCase(), NFD);

        text = text.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        String regex = "[^a-z]";
        text = text.replaceAll(regex, "");
        text = text.replaceAll("j", "i");

        return text;
    }

    public static String readFileToString(File file) {


        StringBuilder sb = new StringBuilder();
        if (file.exists()) {
            BufferedReader br = null;

            try {
                br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            } catch (Exception e) {
                sb = null;

            } finally {
                try {
                    br.close();
                } catch (IOException ex) {
                }
            }
        }

        return sb.toString();
    }


    public static boolean writeToFile(File file, String content) {

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(content);
            bw.flush();
            return true;
        } catch (IOException ex) {

        } finally {
            try {
                bw.close();
            } catch (IOException ex) {
            }
        }


        return false;
    }


    public static String intArrayToString(int[] arr) {

        StringBuilder sb = new StringBuilder();

        for (int element : arr) {
            sb.append(String.valueOf(element));
        }

        return new String(sb);

    }

    public static String intListtoString(List<Integer> list) {

        StringBuilder sb = new StringBuilder();
        for (int item : list) {
            sb.append(item);
        }

        return new String(sb);
    }

    public static String charListtoString(List<Character> list) {

        StringBuilder sb = new StringBuilder();
        for (char item : list) {
            sb.append(item);
        }

        return new String(sb);
    }

    public static void doNoTrickerEnc() {

        Polybius p = new Polybius();
        Trifid t = new Trifid();

        File inFile1 = new File("100.txt");
        File inFile2 = new File("500.txt");
        File inFile3 = new File("1000.txt");

        File P_100 = new File("P_100.txt");
        File P_500 = new File("P_500.txt");
        File P_1000 = new File("P_1000.txt");

        File T_100 = new File("T_100.txt");
        File T_500 = new File("T_500.txt");
        File T_1000 = new File("T_1000.txt");

        String s1 = readFileToString(inFile1);
        String s2 = readFileToString(inFile2);
        String s3 = readFileToString(inFile3);

        writeToFile(P_100, p.doAndReturnEncryption(s1));
        writeToFile(P_500, p.doAndReturnEncryption(s2));
        writeToFile(P_1000, p.doAndReturnEncryption(s3));

        writeToFile(T_100, t.doAndReturnEncryption(s1));
        writeToFile(T_500, t.doAndReturnEncryption(s2));
        writeToFile(T_1000, t.doAndReturnEncryption(s3));

    }

    public static void doTrickerEnc() {

        File P_100 = new File("P_100.txt");
        File P_500 = new File("P_500.txt");
        File P_1000 = new File("P_1000.txt");

        File T_100 = new File("T_100.txt");
        File T_500 = new File("T_500.txt");
        File T_1000 = new File("T_1000.txt");

        String Ps1 = readFileToString(P_100);
        String Ps2 = readFileToString(P_500);
        String Ps3 = readFileToString(P_1000);

        String Ts1 = readFileToString(T_100);
        String Ts2 = readFileToString(T_500);
        String Ts3 = readFileToString(T_1000);

        // na kazdy ZT s prveho spustenia aplikujeme jeden klamac, keby chceme mozme aj viac

        File A_P_100 = new File("A_P_100.txt");        // ps1
        File B2b_P_500 = new File("B2b_P_500.txt");     //  ps2
        File C_P_1000 = new File("C_P_1000.txt");    //   ps3

        File A_T_100 = new File("A_T_100.txt");          // ts1
        File B3c_T_500 = new File("B3c_T_500.txt");         //  ts2
        File C_T_1000 = new File("C_T_1000.txt");  //   ts3


        writeToFile(A_P_100, Tricker.A(Ps1));
        writeToFile(B2b_P_500, Tricker.B(Ps2, 2, 'b'));
        writeToFile(C_P_1000, Tricker.C(Ps3));

        writeToFile(A_T_100, Tricker.A(Ts1));
        writeToFile(B3c_T_500, Tricker.B(Ts2, 3, 'c'));
        writeToFile(C_T_1000, Tricker.C(Ts3));

        System.exit(0);

    }



}

