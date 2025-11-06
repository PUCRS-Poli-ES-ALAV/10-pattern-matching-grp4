public class Main {
    public static void main(String[] args) {
        String s1 = "abcdefghij";
        String s2 = "efg";
        
        System.out.println(pmatch(s1, s2));

        System.out.println(search_RK(s2, s1));

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
