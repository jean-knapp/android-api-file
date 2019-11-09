package app.jeankn.api.file;

import android.util.Log;

import java.util.ArrayList;

/**
 * An XML node
 */
public class XMLNode {
    public String key;
    Object value = null;
    public ArrayList<XMLNode> children = null;

    /**
     * Constructor for nodes with a value.
     * @param key The node key.
     * @param value The node value.
     */
    public XMLNode(String key, Object value) {
        this.key = key;

        if (value instanceof ArrayList<?>) {
            this.children = ((ArrayList<XMLNode>) value);
        } else {
            this.value = value;
        }
    }

    /**
     * Constructor for nodes with children.
     * @param key The node key.
     * @param children An ArrayList of its children.
     */
    public XMLNode(String key, ArrayList<XMLNode> children) {
        this.key = key;
        this.children = children;
    }

    /**
     * Constructor for nodes with an empty value and no children.
     * @param key The node key.
     */
    public XMLNode(String key) {
        this.key = key;
        this.children = new ArrayList<XMLNode>();
    }

    /**
     * Checks if the node has any children.
     * @return true if it has at least one children.
     */
    public boolean hasChildren() {
        return children != null && children.size() > 0;
    }

    /**
     * Gets a child node based on its key.
     * @param key The node key.
     * @return the XMLNode the key references to.
     */
    public XMLNode getChild(String key) {
        if (!hasChildren())
            return null;

        for(XMLNode node : children) {
            if (node.key.equals(key))
                return node;
        }

        return null;
    }

    /**
     * Gets the node value.
     * @return the node value in String format; empty String if it's null.
     */
    public String getStringValue() {
        if (value == null)
            return "";

        return String.valueOf(value);
    }

    /**
     * Gets the node value.
     * @return the node value in Integer format; 0 if it's null.
     */
    public int getIntValue() {
        if (value == null)
            return 0;

        return Integer.valueOf(getStringValue());
    }

    /**
     * Gets the node value.
     * @return the node value in Float format; 0f if it's null.
     */
    public float getFloatValue() {
        if (value == null)
            return 0f;

        return Float.valueOf(getStringValue());
    }

    /**
     * Gets the node value.
     * @return the node value in Double format; 0 if it's null.
     */
    public double getDoubleValue() {
        if (value == null)
            return 0;

        return Double.valueOf(getStringValue());
    }

    /**
     * Gets the node value.
     * @return the node value in Boolean format; false if it's null.
     */
    public boolean getBooleanValue() {
        if (value == null)
            return false;

        return Boolean.valueOf(getStringValue());
    }
}
