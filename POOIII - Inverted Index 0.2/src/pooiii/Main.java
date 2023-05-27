package pooiii;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;
import javax.swing.JFileChooser;
import pooiii.index.InvertedIndex;
import pooiii.index.StopWords;

public class Main {

    private final InvertedIndex ii;
    private final StopWords stopWords;

    public Main() {
        ii = new InvertedIndex();
        stopWords = new StopWords();
    }

    // percorre a lista de arquivos contida no diretório previamente selecionado
    public void readFiles(File directory) {
        for (File f : directory.listFiles()) {
            //System.out.println("> " + f.getAbsolutePath());
            if (!f.isDirectory()) {
                parseFile(f);
            }
        }
    }

    // lê o conteúdo de um arquivo linha a linha ignorando termos de tamanho 1 e 'stopwords' 
    public void parseFile(File f) {
        BufferedReader reader;
        StringTokenizer st;
        try {
            reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                if (line != null) {
                    st = new StringTokenizer(line, " ");
                    while (st.hasMoreTokens()) {
                        String token = st.nextToken();
                        token = clearToken(token);
                        if (token.length() > 1 && !stopWords.contains(token)) {
                            ii.add(token, f.getAbsolutePath());
                        }
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.showOpenDialog(null);
        File dir = fc.getSelectedFile();

        Main m = new Main();
        m.readFiles(dir);

        //m.printIndex();
        m.saveIndex();
    }
}
