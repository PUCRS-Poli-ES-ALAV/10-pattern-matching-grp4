import java.util.Random;

// pior caso O(n*m)
public class Ex1 {

    private static long iter = 0;
    private static long inst = 0;

    public static void main(String[] args) {
        Random rnd = new Random();

        int[] tamanhosTexto = { 10_000, 100_000, 500_000 };
        int[] tamanhosPadrao = { 5, 100, 1_000 };

        for (int t = 0; t < tamanhosTexto.length; t++) {
            int n = tamanhosTexto[t];
            int m = tamanhosPadrao[t];

            String padrao = geraStringAleatoria(m);
            String texto = geraStringAleatoria(n);

            int posInsercao = rnd.nextInt(n - m + 1);
            texto = texto.substring(0, posInsercao)
                    + padrao
                    + texto.substring(posInsercao + m);

            System.out.println("=======-================================");
            System.out.println("TEXTO  tamanho = " + texto.length());
            System.out.println("PADRÃO tamanho = " + padrao.length());

            // zera contadores
            iter = 0;
            inst = 0;

            long inicio = System.nanoTime();
            int pos = pmatch(texto, padrao);
            long fim = System.nanoTime();

            long tempoMs = (fim - inicio) / 1_000_000;

            System.out.println("Posição encontrada: " + pos);
            System.out.println("Tempo: " + tempoMs + " ms");
            System.out.println("Iterações: " + iter);
            System.out.println("Instruções (aprox): " + inst);
        }
    }

    public static int pmatch(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        // caso padrao
        if (m == 0) {
            return 0;
        }

        for (int i = 0; i <= n - m; i++) {
            iter++;
            inst++;

            int j = 0;
            // compara caracteres enquanto estiverem batendo
            while (j < m && s1.charAt(i + j) == s2.charAt(j)) {
                inst += 3;
                j++;
            }

            inst++;
            if (j == m) {
                return i;
            }
        }

        return -1;
    }

    public static String geraStringAleatoria(int tam) {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(tam);
        Random rnd = new Random();
        for (int i = 0; i < tam; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
