package app.jeankn.api.file;

import android.content.Context;

import java.util.ArrayList;

/**
 * API for reading and writing files
 */
public class File {

    /**
     * Reads a text file from the app-specific directory and calls an interface with its contents.
     * @param path File path relative to <i>/data/data/[packagename]/files</i>.
     * @param context The application context.
     * @param onReadText If the file is read, <b>onReadText</b> is called with the file contents in a string.
     */
    public static void ReadText(String path, Context context, final OnReadText onReadText) {
        new ReadText(path, context, new Read.OnFinishListener() {
            @Override
            public void OnFinish(int status, Object data) {
                if (status == IO.SUCCESS && onReadText != null) {
                    onReadText.onDone((String) data);
                }
            }
        });
    }

    /**
     * Reads a text file from the external directory and calls an interface with its contents.
     * @param path File path relative to <i>/Android/data/[packagename]/files</i>.
     * @param context The application context.
     * @param onReadText If the file is read, <b>onReadText</b> is called with the file contents in a string.
     */
    public static void ReadTextExternal(String path, Context context, final OnReadText onReadText) {
        new ReadTextExternal(path, context, new Read.OnFinishListener() {
            @Override
            public void OnFinish(int status, Object data) {
                if (status == IO.SUCCESS && onReadText != null) {
                    onReadText.onDone((String) data);
                }
            }
        });
    }

    /**
     * Reads a CSV file from the app-specific directory and calls an interface with its contents.
     * @param path File path relative to <i>/data/data/[packagename]/files</i>.
     * @param context The application context.
     * @param onReadCSV If the file is read, <b>onReadCSV</b> is called with the file contents in an ArrayList of an ArrayList of Strings.
     */
    public static void ReadCSV(String path, Context context, final OnReadCSV onReadCSV) {
        ReadCSV(path, context, ",", onReadCSV);
    }

    /**
     * Reads a CSV file from the app-specific directory and calls an interface with its contents.
     * @param path File path relative to <i>/data/data/[packagename]/files</i>.
     * @param context The application context.
     * @param onReadCSV If the file is read, <b>onReadCSV</b> is called with the file contents in an ArrayList of an ArrayList of Strings.
     */
    public static void ReadCSV(String path, Context context, String separator, final OnReadCSV onReadCSV) {
        new ReadCSV(path, context, separator, new Read.OnFinishListener() {
            @Override
            public void OnFinish(int status, Object data) {
                if (status == IO.SUCCESS && onReadCSV != null) {
                    onReadCSV.onDone((ArrayList<ArrayList<String>>) data);
                }
            }
        });
    }

    /**
     * Reads a CSV file from the external directory and calls an interface with its contents.
     * @param path File path relative to <i>/Android/data/[packagename]/files</i>.
     * @param context The application context.
     * @param onReadCSV If the file is read, <b>onReadCSV</b> is called with the file contents in an ArrayList of an ArrayList of Strings.
     */
    public static void ReadCSVExternal(String path, Context context, final OnReadCSV onReadCSV) {
        ReadCSVExternal(path, context, ",", onReadCSV);
    }

    /**
     * Reads a CSV file from the external directory and calls an interface with its contents.
     * @param path File path relative to <i>/Android/data/[packagename]/files</i>.
     * @param context The application context.
     * @param onReadCSV If the file is read, <b>onReadCSV</b> is called with the file contents in an ArrayList of an ArrayList of Strings.
     */
    public static void ReadCSVExternal(String path, Context context, String separator, final OnReadCSV onReadCSV) {
        new ReadCSVExternal(path, context, separator, new Read.OnFinishListener() {
            @Override
            public void OnFinish(int status, Object data) {
                if (status == IO.SUCCESS && onReadCSV != null) {
                    onReadCSV.onDone((ArrayList<ArrayList<String>>) data);
                }
            }
        });
    }

    /**
     * Reads an XML file from the app-specific directory and calls an interface with its contents.
     * @param path File path relative to <i>/data/data/[packagename]/files</i>.
     * @param context The application context.
     * @param onReadXML If the file is read, <b>onReadXML</b> is called with the file contents in a root XMLNode.
     */
    public static void ReadXML(String path, Context context, final OnReadXML onReadXML) {
        new ReadXML(path, context, new Read.OnFinishListener() {
            @Override
            public void OnFinish(int status, Object data) {
                if (status == IO.SUCCESS && onReadXML != null) {
                    onReadXML.onDone((XMLNode) data);
                }
            }
        });
    }

    /**
     * Reads an XML file from the external directory and calls an interface with its contents.
     * @param path File path relative to <i>/Android/data/[packagename]/files</i>.
     * @param context The application context.
     * @param onReadXML If the file is read, <b>onReadXML</b> is called with the file contents in a root XMLNode.
     */
    public static void ReadXMLExternal(String path, Context context, final OnReadXML onReadXML) {
        new ReadXMLExternal(path, context, new Read.OnFinishListener() {
            @Override
            public void OnFinish(int status, Object data) {
                if (status == IO.SUCCESS && onReadXML != null) {
                    onReadXML.onDone((XMLNode) data);
                }
            }
        });
    }

    /**
     * Writes a text file into the app-specific directory.
     * @param path File path relative to <i>/data/data/[packagename]/files</i>.
     * @param context The application context.
     * @param data The string to be written.
     * @param onWrite When the file is written, <b>onWrite</b> is called.
     */
    public static void WriteText(String path, Context context, String data, final OnWrite onWrite) {
        new WriteText(path, data, context, new Write.OnFinishListener() {
            @Override
            public void OnFinish(int status) {
                if (status == IO.SUCCESS && onWrite != null) {
                    onWrite.onDone();
                }
            }
        });
    }

    /**
     * Writes a text file into the app-specific directory.
     * @param path File path relative to <i>/data/data/[packagename]/files</i>.
     * @param context The application context.
     * @param data The string to be written.
     */
    public static void WriteText(String path, Context context, String data) {
        WriteText(path, context, data, null);
    }

    /**
     * Writes a text file into the external directory.
     * @param path File path relative to <i>/Android/data/[packagename]/files</i>.
     * @param context The application context.
     * @param data The String to be written.
     * @param onWrite When the file is written, <b>onWrite</b> is called.
     */
    public static void WriteTextExternal(String path, Context context, String data, final OnWrite onWrite) {
        new WriteText(path, data, context, new Write.OnFinishListener() {
            @Override
            public void OnFinish(int status) {
                if (status == IO.SUCCESS && onWrite != null) {
                    onWrite.onDone();
                }
            }
        });
    }

    /**
     * Writes a text file into the external directory.
     * @param path File path relative to <i>/Android/data/[packagename]/files</i>.
     * @param context The application context.
     * @param data The String to be written.
     */
    public static void WriteTextExternal(String path, Context context, String data) {
        WriteTextExternal(path, context, data, null);
    }

    /**
     * Writes an XML file into the app-specific directory.
     * @param path File path relative to <i>/data/data/[packagename]/files</i>.
     * @param context The application context.
     * @param data The String to be written.
     * @param onWrite If the file is read, <b>onReadXML</b> is called with the file contents in a root XMLNode.
     */
    public static void WriteXML(String path, Context context, XMLNode data, final OnWrite onWrite) {
        new WriteXML(path, data, context, new Write.OnFinishListener() {
            @Override
            public void OnFinish(int status) {
                if (status == IO.SUCCESS && onWrite != null) {
                    onWrite.onDone();
                }
            }
        });
    }

    /**
     * Writes an XML file into the app-specific directory.
     * @param path File path relative to <i>/data/data/[packagename]/files</i>.
     * @param context The application context.
     * @param data The String to be written.
     */
    public static void WriteXML(String path, Context context, XMLNode data) {
        WriteXML(path, context, data, null);
    }

    /**
     * Writes an XML file into the external directory.
     * @param path File path relative to <i>/Android/data/[packagename]/files</i>.
     * @param context The application context.
     * @param data The String to be written.
     * @param onWrite If the file is read, <b>onReadXML</b> is called with the file contents in a root XMLNode.
     */
    public static void WriteXMLExternal(String path, Context context, XMLNode data, final OnWrite onWrite) {
        new WriteXMLExternal(path, data, context, new Write.OnFinishListener() {
            @Override
            public void OnFinish(int status) {
                if (status == IO.SUCCESS && onWrite != null) {
                    onWrite.onDone();
                }
            }
        });
    }

    /**
     * Writes an XML file into the external directory.
     * @param path File path relative to <i>/Android/data/[packagename]/files</i>.
     * @param context The application context.
     * @param data The String to be written.
     */
    public static void WriteXMLExternal(String path, Context context, XMLNode data) {
        WriteXMLExternal(path, context, data, null);
    }

    /**
     * Checks if the file exists in the app-specific directory.
     * @param path File path relative to <i>/data/data/[packagename]/files</i>.
     * @param context The application context.
     * @return true if the file exists.
     */
    public static boolean Exists(String path, Context context) {
        return new Exists(path, context).getResult();
    }

    /**
     * Checks if the file exists in the external directory.
     * @param path File path relative to <i>/Android/data/[packagename]/files</i>.
     * @param context The application context.
     * @return true if the file exists.
     */
    public static boolean ExistsExternal(String path, Context context) {
        return new ExistsExternal(path, context).getResult();
    }

    /**
     * Interface called when a text file is successfully read
     */
    public interface OnReadText {
        void onDone(String result);
    }

    /**
     * Interface called when a CSV file is successfully read
     */
    public interface OnReadCSV {
        void onDone(ArrayList<ArrayList<String>> result);
    }

    /**
     * Interface called when an XML file is successfully read
     */
    public interface OnReadXML {
        void onDone(XMLNode result);
    }

    /**
     * Interface called when a file is successfully written
     */
    public interface OnWrite {
        void onDone();
    }
}
