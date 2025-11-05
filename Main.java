public class Main {
    public static void main(String[] args) {
        String s1 = "abcdefghij";
        String s2 = "efg";
        System.out.println(pmatch(s1, s2));
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

    public int search_RK(){

        return 0;
    }

    public static long hash(String s, int M){
        long hs = 0;
        int R = 26;
        int Q = 199;

        for(int i = 0; i< M; i++){
            hs = (hs * R + (int)s.charAt(i)) % Q;
        }
        
        return 0;
    }

}
