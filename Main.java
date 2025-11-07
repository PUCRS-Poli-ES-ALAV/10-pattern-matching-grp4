import java.util.Random;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        String s1 = "abcdefghij";
        String s2 = "efg";
        String randomStr = "";
        String pattern = UUID.randomUUID().toString().replaceAll("-","");

        

        Random rnd = new Random();
        long rndLong = rnd.nextLong();

        for(int i = 0; i < 15625 - pattern.length(); i++){ //500.000 caracteres
            randomStr += UUID.randomUUID().toString().replaceAll("-","");
            if(i == rndLong)
            randomStr += pattern;
        }

        System.out.println(randomStr + "size: " + randomStr.length());

        System.out.println("pmatch: " + pmatch(randomStr, pattern));

        System.out.println("RK: " + search_RK(randomStr, pattern));


    }

    public static int pmatch(String s1, String s2) {
        int j = 0;
        for (int i = 0; i < s1.length() - 1; i++) {
            if (j == s2.length()) {
                return i - j;
            } else if (s1.charAt(i) == s2.charAt(j)) {
                j++;
            } else {
                j = 0;
            }
        }
        return 0;
    }

    public static int search_RK(String pat, String txt){
        int tamPat = pat.length();
        int tamTxt = txt.length();
        long patHash = hash(pat, tamPat);
        
        for(int i = 0; i <= tamTxt - tamPat; i++){
            long txtHash = hash(txt.substring(i, i+tamPat), tamPat);
            if(patHash == txtHash)
                return i; //ocorrencia ou colisão
        }

        return tamTxt; //nenhuma ocorrencia
    }

    public static long hash(String s, int patt){
        long hash = 0;
        int ALFBT = 26;
        int modHash = 199; //número primo grande

        for(int i = 0; i < patt; i++){
            hash = (hash * ALFBT + (int)s.charAt(i)) % modHash;
        }
        
        return hash;
    }

}
