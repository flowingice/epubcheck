package hr.tvz.util;

import com.adobe.epubcheck.tool.EpubChecker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class InputHandler {
    public static List<String> handle(String filename) {
        EpubChecker checker = new EpubChecker();
        String[] args = new String[1];
        args[0] = filename;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        System.setErr(new PrintStream(baos));
        checker.run(args);
        String tmp = baos.toString();
        return Arrays.asList(tmp.split("\n"));
    }

}
