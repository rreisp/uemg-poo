package pooiii;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;
import javax.swing.JFileChooser;
import pooiii.index.InvertedIndex;
import pooiii.index.StopWords;

public class Main {

    private final InvertedIndex ii;
    private final StopWords stopWords;

    public Main(InvertedIndex ii) {
        this.ii = ii;
        stopWords = new StopWords();
    }

    // lê o conteúdo de um arquivo linha a linha ignorando termos de tamanho 1 e 'stopwords' 
    public ArrayList<String> parseQuery(String query) {
        ArrayList<String> tokens = new ArrayList<>();
        StringTokenizer st = null;
        if (query != null) {
            st = new StringTokenizer(query, " ");
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                token = clearToken(token);
                if (token.length() > 1 && !stopWords.contains(token)) {
                    tokens.add(token);
                }
            }
        }

        return tokens;
    }

    // remove caracteres especiais dos tokens
    private String clearToken(String token) {
        String st = token.replace(".", "");
        st = st.replace("(", "");
        st = st.replace(")", "");
        st = st.replace("!", "");
        st = st.replace("?", "");
        st = st.replace(":", "");
        st = st.replace(";", "");
        st = st.replace(",", "");
        st = st.replace("\'", "");
        st = st.replace("\"", "");
        st = st.toLowerCase();
        st = st.replace("à", "a");
        st = st.replace("ã", "a");
        st = st.replace("á", "a");
        st = st.replace("é", "e");
        st = st.replace("ê", "e");
        st = st.replace("í", "i");
        st = st.replace("ó", "o");
        st = st.replace("õ", "o");
        st = st.replace("ú", "u");
        st = st.replace("ç", "c");
        return st;
    }

    // imprime o índice invertido obtido no processo
    public void printIndex() {
        SortedSet<String> tokens = new TreeSet<String>(ii.keySet());
        for (String token : tokens) {
            System.out.println("> " + token);
            for (String path : ii.get(token).keySet()) {
                System.out.println("* " + path + "\t\t" + ii.get(token).get(path));
            }
        }
    }

    // imprime o índice invertido obtido no processo
    public void saveIndex() {
        try {
            File file = new File("inverted-index.txt");
            System.out.println(">> " + file.getAbsolutePath());
            FileOutputStream f = new FileOutputStream(file);
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(ii);

            o.close();
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // seleciona o diretório e repassa para a classe Main que inicia a indexação
    public static void main(String[] args) {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.showOpenDialog(null);
        File file = fc.getSelectedFile();

        InvertedIndex ii = null;

        FileInputStream fi;
        try {
            fi = new FileInputStream(file);
            ObjectInputStream oi = new ObjectInputStream(fi);
            ii = (InvertedIndex) oi.readObject();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Main m = new Main(ii);

        m.printIndex();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchGUI().setVisible(true);
            }
        });
        //m.saveIndex();
    }
}
