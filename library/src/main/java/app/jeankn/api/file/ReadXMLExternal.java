package app.jeankn.api.file;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

public class ReadXMLExternal extends ReadExternal {
    ReadXMLExternal(String path, Context context, OnFinishListener finishListener) {
        super(path, context, finishListener);
    }

    ReadXMLExternal(String path, Context context, OnFinishListener finishListener, OnProgressListener progressListener) {
        super(path,context, finishListener, progressListener);
    }

    @Override
    protected Integer onExecute(File file) {
        int READ_BLOCK_SIZE = 128;
        char[] inputBuffer = new char[READ_BLOCK_SIZE];
        StringBuilder stringBuilder = new StringBuilder();

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);

            InputStreamReader reader = new InputStreamReader(inputStream);

            int charRead;
            while ((charRead = reader.read(inputBuffer)) > 0) {
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                stringBuilder.append(readString);
            }
            reader.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            logError(FILE_NOT_FOUND);
            return FILE_NOT_FOUND;
        } catch (IOException e) {
            logError(ERROR);
            return ERROR;
        }

        String string = stringBuilder.toString();

        try {
            XmlPullParserFactory factory = null;
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = null;
            parser = factory.newPullParser();

            parser.setInput(new StringReader(string));

            ArrayList<XMLNode> keyVals = new ArrayList<>();
            while(parser.getEventType() != XmlPullParser.END_DOCUMENT) {

                XMLNode keyVal = traverseParser(parser);
                if (keyVal != null) {
                    keyVals.add(keyVal);
                }
            }

            if (keyVals.size() > 1) {
                this.data = keyVals.toArray(new XMLNode[0]);
            } else {
                this.data = keyVals.get(0);
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
            logError(ERROR);
            return ERROR;
        }

        return SUCCESS;
    }

    private XMLNode traverseParser(XmlPullParser parser) {
        try {
            if (parser.getEventType() == XmlPullParser.START_TAG) {
                XMLNode keyVal = new XMLNode(parser.getName(), null);
                ArrayList<XMLNode> children = new ArrayList<>();
                parser.next();

                while (parser.getEventType() == XmlPullParser.START_TAG) {
                    XMLNode child = traverseParser(parser);
                    if (child != null) {
                        children.add(child);
                    }
                }

                if (parser.getEventType() == XmlPullParser.TEXT) {
                    keyVal.value = parser.getText();
                    parser.next();
                }

                if (children.size() > 0) {
                    keyVal.children = children;
                }

                if (parser.getEventType() == XmlPullParser.END_TAG) {
                    parser.next();
                }

                return keyVal;

            } else {
                parser.next();
            }
        } catch (XmlPullParserException e) {
            logError(ERROR);
            e.printStackTrace();
        } catch (IOException e) {
            logError(ERROR);
            e.printStackTrace();
        }
        return null;
    }
}
