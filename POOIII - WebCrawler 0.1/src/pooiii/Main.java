package pooiii;

import java.io.File;
import javax.swing.JFileChooser;
import pooiii.crawler.CrawlerPensador;

public class Main {

    // seleciona o diretório e repassa para a classe Main que inicia a indexação
    public static void main(String[] args) {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.showOpenDialog(null);

        File dir = fc.getSelectedFile();
        File root = new File(dir.getAbsolutePath() +"/poesia/");
        String path = "";
        if(dir != null){
            root.mkdirs();
            path = root.getAbsolutePath() +"/";
        }
        CrawlerPensador crawler = new CrawlerPensador();
        String start = crawler.getStartPage();
        crawler.setMaxDepth(1);
        crawler.setRoot(path);
        crawler.crawl(start, 0);
        //m.saveIndex();
    }
}
