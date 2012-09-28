/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.wexcontent.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jerry Goodnough
 */
public class LanguageHelper {

    //Translate Language code th 639.2 format
    // Defaults to english
    private static Map<String,String> cvt639;
    static
    {
        cvt639 = new HashMap<String,String>();
        cvt639.put("en", "eng");
        cvt639.put("fr", "fra");
        cvt639.put("ar", "ara");
        cvt639.put("de", "deu");
        cvt639.put("ja", "jpn");
        cvt639.put("ru", "rus");
        cvt639.put("es", "esl");
        cvt639.put("de", "deu");
    }

    public static String normalizeToLanguage639dot2(String lang)
    {

        String out = "eng";
        if (lang != null)
        {
            if (lang.length() == 3)
            {
                out = lang;
            }
            else if (lang.length() ==2 )
            {
                if (cvt639.containsKey(lang))
                {
                    out = cvt639.get(lang);
                }
            }
        }
        return out;
    }
}
