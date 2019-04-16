package win.chenglei;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.Iterator;

public class ReadXml {
    private String path;
    public ReadXml(){

    }
    public ReadXml(String path){
        this.path= path;
    }

    public void setPath(String path){
        this.path = path;
    }
    public HashMap<String,String> jiexi(){
        HashMap<String,String> hp = new HashMap<>();
        if(null != path){
            SAXReader sax = new SAXReader();
            try {
                Document read = sax.read(this.getClass().getResource("/").getPath() + path);
                Element rootElement = read.getRootElement();
                Iterator<Element> elementIterator = rootElement.elementIterator();
                while (elementIterator.hasNext()){
                    Element next = elementIterator.next();
                    hp.put(next.element("sname").getText(),next.element("class").getText());
                }
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        return hp;
    }
}
