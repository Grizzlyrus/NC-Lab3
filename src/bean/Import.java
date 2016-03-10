package bean;

import model.ModelItemCollection;

import javax.ejb.Remote;
import java.io.BufferedReader;
import java.io.Reader;

/**
 * Created by iorlov on 10.03.2016.
 */
@Remote
public interface Import{
    ModelItemCollection xmlImport(String string);
}
