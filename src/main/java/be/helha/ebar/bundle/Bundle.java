package be.helha.ebar.bundle;

import java.util.HashMap;
import java.util.Map;

public interface Bundle {
    Map<String, Object> map = new HashMap<String, Object>();

    void put (String clef, Object valeur);
    Object get (String clef);
    void vider();
}
