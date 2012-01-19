package com.ufp.sensys.domain;

import java.util.Vector;

public interface World {
    public void addElement(Element element);
    public Vector<Element> getElements(String type);
}