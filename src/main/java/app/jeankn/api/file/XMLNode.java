package app.jeankn.api.file;

import android.util.Log;

import java.util.ArrayList;

public class XMLNode {
    public String key;
    public Object value = null;
    public XMLNode[] children = null;

    public XMLNode(String key, Object value) {
        this.key = key;

        if (value instanceof ArrayList<?>) {
            Log.d("KeyVal", "converting " + key + " to array");
            this.children = ((ArrayList<XMLNode>) value).toArray(new XMLNode[0]);
        } else {
            this.value = value;
        }
    }

    public XMLNode(String key, XMLNode[] children) {
        this.key = key;
        this.children = children;
    }

    public boolean hasChildren() {
        return children != null;
    }
}
