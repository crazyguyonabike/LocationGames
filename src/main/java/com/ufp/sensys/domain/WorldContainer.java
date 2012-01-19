package com.ufp.sensys.domain;

import java.util.Vector;
import java.util.HashSet;
import java.util.Iterator;

import com.bbn.openmap.util.quadtree.QuadTree;

import org.springframework.scheduling.annotation.Async;

import org.apache.log4j.Logger;

public class WorldContainer implements World {
    private static Logger logger = Logger.getLogger(WorldContainer.class);
    private QuadTree quadTree;

    public WorldContainer() {
        quadTree = new QuadTree(90.0f, -180.0f, -90.0f, 180.0f, 100, 50f);
    }

    public void addElement(Element element) {
        Vector<Element> elements = getElements(element.getType());
        if (elements != null) {
            for (Element e : elements) {
                if (element.getId().equals(e.getId())) {
                    Object o = quadTree.remove(e.getLatitude(), e.getLongitude(), e);
                }
            }
        }
        quadTree.put(element.getLatitude(), element.getLongitude(), element);
        processAsynchonousTasks();
    }
    
    public Vector<Element> getElements(String type) {
        Vector<Element> elements = null;
        Vector v = quadTree.get(90.0f, -180.0f, -90.0f, 180.0f);
        if ((v != null) && !v.isEmpty()) {
            elements = new Vector<Element>();

            for (Object object : v) {
                if (object instanceof Element) {
                    Element element = (Element)object;
                    if (element.getType().equals(type)) 
                        elements.add(element);
                }
            }
        }
        return elements;
    }

    @Async
    private void processAsynchonousTasks() {
        Vector<Element> elements = getElements("stationary");
        for (Element element : elements) {
            Vector v = quadTree.get(element.getLatitude()+0.002f, element.getLongitude()-0.0002f, element.getLatitude()-0.002f, element.getLongitude()+0.0002f);
            HashSet hashSet = new HashSet(v);
            Iterator iterator = hashSet.iterator();
            while (iterator.hasNext()) {
                Object object = iterator.next();
                if (object instanceof MovingElement) {
                    MovingElement movingElement = (MovingElement)object;
                    logger.debug("sending message \"" + ((StationaryElement)element).getDescription() + " from " + element.getName() + "\" to " + movingElement.getName() + "(" + movingElement.getId() + ") at " + movingElement.getLatitude() + ", " + movingElement.getLongitude() + " at approximately " + movingElement.getPlacedAt());
                } 
            }
        }
    }
}

