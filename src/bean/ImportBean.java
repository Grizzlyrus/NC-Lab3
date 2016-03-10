package bean;

import model.ModelItem;
import model.ModelItemCollection;
import org.xml.sax.InputSource;

import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Map;
import java.util.Set;

/**
 * Created by iorlov on 10.03.2016.
 */
@Stateless
public class ImportBean implements Import{

    public ImportBean() {
    }

    @Override
    public ModelItemCollection xmlImport(String string) {
        ///InputSource is = new InputSource();
        //is.setCharacterStream(new StringReader(string));

        ModelItemCollection modcoll=null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ModelItemCollection.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            modcoll = (ModelItemCollection) jaxbUnmarshaller.unmarshal(new StringReader(string));
            Map<Integer,ModelItem> itemMap=modcoll.getModIt();
            for(Map.Entry<Integer,ModelItem> entry:itemMap.entrySet()){
                System.err.println(entry.getKey()+" "+entry.getValue());
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return modcoll;
    }
}
