package com.itcr.ce.datosparty.dataStructures.lists;

/**
 * Superclass made to add length to Lists and Stack
 */
public abstract class LinearStructure {

    protected int length;

    /**
     * Returns the length of the structure. Used on every structure.
     * @return
     */
    public int getLength() {
        return length;
    }
}
