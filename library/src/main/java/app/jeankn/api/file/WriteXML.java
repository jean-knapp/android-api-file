package app.jeankn.api.file;

import android.content.Context;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

class WriteXML extends Write {
    WriteXML(String path, Object data, Context context, OnFinishListener finishListener) {
        super(path, data, context, finishListener);
    }

    WriteXML(String path, Object data, Context context, OnFinishListener finishListener, OnProgressListener progressListener) {
        super(path, data, context, finishListener, progressListener);
    }

    @Override
    protected Integer onExecute(File file, Object data) {

        try {
            XmlSerializer serializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            serializer.setOutput(writer);

            traverseObject(serializer, (XMLNode) data);

            serializer.endDocument();
            serializer.flush();
            String string = writer.toString();

            FileOutputStream outputStream = null;
            outputStream = new FileOutputStream(file);
            outputStream.write(string.getBytes());
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            logError(ERROR);
            return ERROR;
        }

        return SUCCESS;
    }

    private void traverseObject(XmlSerializer serializer, XMLNode data) {
        try {
            serializer.startTag(null, data.key);
            if (data.hasChildren()) {
                for(XMLNode child : data.children) {
                    traverseObject(serializer, child);
                }
            } else if(data.value != null) {
                serializer.text(data.value.toString());
            } else {
                serializer.text("");
            }
            serializer.endTag(null, data.key);
        } catch (IOException e) {
            logError(ERROR);
        }
    }
}
