package com.piggymade.helper;


import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HandleBarUtil {
    public static final Logger log = Logger.getLogger(HandleBarUtil.class.getName());

    private HandleBarUtil() {
        throw new IllegalStateException("Utility class");
    }


    public static String processHBS(HashMap<String, String> hsMap, String filename) {
        log.info("param = {} " + hsMap);

        String templateString;
        TemplateLoader firstLoader = new ClassPathTemplateLoader("", ".html");
        try {
            Handlebars handlebars = new Handlebars().with(firstLoader);
            Template template = handlebars.compile(filename);
            templateString = template.apply(hsMap);
        } catch (Exception e) {
            log.log(Level.SEVERE, "[PROCESS_STRING_HBS]", e);
            return null;
        }
        return templateString;
    }

    public static String processStringHBS(HashMap<String, String> hsMap, String content) {
        log.info("param = {} " + hsMap);

        String templateString;
        try {
            Handlebars handlebars = new Handlebars();
            templateString = handlebars.compileInline(content).apply(hsMap);
        } catch (Exception e) {
            log.log(Level.SEVERE, "[PROCESS_STRING_HBS]", e);
            return null;
        }
        return templateString;
    }

}