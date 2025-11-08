import java.util.Random;
public class Main {
    public static void main(String[] args) {
        System.out.println(".\n");
        // String s1 = "aaaaaaaaaaaaaaaaaaaaaaaaabc";
        // String s2 = "abc";

        String randomStr = "";
        String pattern = aleatorizador(32);

        Random rnd = new Random();
        int rndInt = rnd.nextInt(15625);

        for(int i = 0; i < 15625 - pattern.length(); i++){ //500.000 caracteres
            if(i == rndInt)
            randomStr += pattern;
            else
            randomStr += aleatorizador(32);
        }

        System.out.println(randomStr + " size: " + randomStr.length());

        System.out.println("pmatch: " + pmatch(randomStr, pattern));

        System.out.println("RK: " + search_RK(randomStr, pattern));

        System.out.println("pattern: " + pattern);

    }

    public static int pmatch(String s1, String s2) {
        int j = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(j)) {
                j++;
                if (j == s2.length()) {
                    return i - j + 1;
                }
            } else {
                i = i - j;
                j = 0;
            }
        }
        return -1;
    }

    public static int search_RK(String txt, String pat){
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
        int modHash = 93281341; //número primo grande

        for(int i = 0; i < patt; i++){
            hash = (hash * ALFBT + s.charAt(i)) % modHash;
        }
        
        return hash;
    }


    public static String aleatorizador(int tam) {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        for (int i = 0; i < tam; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

}
