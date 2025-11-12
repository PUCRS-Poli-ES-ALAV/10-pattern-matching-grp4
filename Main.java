import java.util.Random;
// pattern matching algorithms
// pmatch: O(n*m) - pior caso O(n*m)
// RK: O(n+m) - pior caso O(n*m)

public class Main {
    private static int inst = 0;
    private static int iter = 0;

    public static void main(String[] args) {
        System.out.println(".\n");

        String randomStr = aleatorizador(512_000);
        String pattern = aleatorizador(100_000);

        Random rnd = new Random();
        int rndInt = rnd.nextInt(randomStr.length());

        randomStr = randomStr.substring(0, rndInt) + pattern + randomStr.substring(rndInt, randomStr.length());
        System.out.println();

        System.out.println(randomStr + " \nsize: " + randomStr.length());

        System.out.println("pattern: " + pattern);
        System.out.println("========");

        long inicio = System.nanoTime();
        System.out.println("pmatch: " + pmatch(randomStr, pattern));
        long fim = System.nanoTime();

        long tempo = (fim - inicio) / 1_000_000;
        System.out.println("tempo: " + tempo + "ms");
        System.out.println("iterações: " + iter + " // instruções: " + inst);

        System.out.println("========");
        iter = 0;
        inst = 0;
        inicio = System.nanoTime();
        System.out.println("RK: " + search_RK(randomStr, pattern));
        fim = System.nanoTime();

        tempo = (fim - inicio) / 1_000_000;
        System.out.println("tempo: " + tempo + "ms");
        System.out.println("iterações: " + iter + " // instruções: " + inst);

    }

    public static int pmatch(String s1, String s2) {
        int j = 0;
        inst++;
        for (int i = 0; i < s1.length(); i++) {
            if (i == 0)
                inst += 4;

            iter++;
            if (s1.charAt(i) == s2.charAt(j)) {
                j++;
                inst += 2;
                if (j == s2.length()) {
                    inst++;
                    return i - j + 1;
                }
            } else {
                i = i - j;
                j = 0;

                inst += 2;
            }
        }
        return -1;
    }

    public static int search_RK(String txt, String pat) {
        iter++;
        int tamPat = pat.length();
        inst++;
        int tamTxt = txt.length();
        inst++;
        long patHash = hash(pat, tamPat);
        inst += 2;

        for (int i = 0; i <= tamTxt - tamPat; i++) {
            if (i == 0)
                inst += 4;
            iter++;
            long txtHash = hash(txt.substring(i, i + tamPat), tamPat);
            inst += 2;
            if (patHash == txtHash) {
                inst++;
                iter++;
                return i; // ocorrencia ou colisão
            }
        }

        return tamTxt; // nenhuma ocorrencia
    }

    public static long hash(String s, int patt) {
        iter++;
        long hash = 0;
        inst++;
        final int ALFBT = 26;
        inst++;
        final int modHash = 93281341; // número primo grande

        for (int i = 0; i < patt; i++) { inst+=4;
            hash = (hash * ALFBT + s.charAt(i)) % modHash; inst+=4;
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
