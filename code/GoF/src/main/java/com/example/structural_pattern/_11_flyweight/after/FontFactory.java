package com.example.structural_pattern._11_flyweight.after;

import java.util.HashMap;
import java.util.Map;

/**
 * Flyweight Factory 에 해당.
 * font 인스턴스에 접근할 수 있는, 캐싱하는 역할
 */
public class FontFactory {

    private Map<String, Font> cache = new HashMap<>();

    public Font getFont(String font) {
        if (cache.containsKey(font)) {
            return cache.get(font);
        } else {
            String[] split = font.split(":");
            Font newFont = new Font(split[0], Integer.parseInt(split[1]));
            cache.put(font, newFont);
            return newFont;
        }
    }
}
