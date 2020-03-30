package study.daydayup.wolf.demo.my.starter.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * study.daydayup.wolf.demo.my.starter.lucene
 *
 * @author Wingle
 * @since 2020/3/30 6:27 下午
 **/
public class Hello {
    public static void main(String[] args) throws IOException {
        Analyzer analyzer = new StandardAnalyzer();
        Path indexPath = Files.createTempDirectory("tmpIndex");
        Directory directory = FSDirectory.open(indexPath);

        IndexWriterConfig config = new IndexWriterConfig();
        IndexWriter writer = new IndexWriter(directory, config);

        Document document = new Document();
        String text = "This is the text to be indexed.";
        document.add(new Field("field", text, TextField.TYPE_STORED));
        writer.addDocument(document);
        writer.close();


        DirectoryReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);


    }
}
