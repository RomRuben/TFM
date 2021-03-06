package postagger;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.util.InvalidFormatException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ruben on 20/07/15.
 */
public class OpenNLPPostTagger {


    public String[] tag(String[] corpus, String modelPath) {
        POSTaggerME tagger = getPOTagger(modelPath);
        return tagger.tag(corpus);
    }

    private POSTaggerME getPOTagger(String modelPath) {
        InputStream modelIn = getStreamOfModelBinary(modelPath);
        POSModel model = getPOSModel(modelIn);
        return new POSTaggerME(model);
    }

    private POSModel getPOSModel(InputStream modelIn) {
        try {
            return new POSModel(modelIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private InputStream getStreamOfModelBinary(String modelPath) {
        try {
            return new FileInputStream(modelPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
