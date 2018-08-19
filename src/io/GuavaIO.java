package io;

import com.google.common.io.CharSource;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import sun.applet.Main;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class GuavaIO {
    public List<String> read(CharSource source){
        List<String> lines= null;
        try {
            lines = source.readLines();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void test(){
        CharSource fileSource= Files.asCharSource(new File("D:\\Ordering.txt"), Charset.defaultCharset());
        CharSource urlSource= Resources.asCharSource(Resources.getResource(""),Charset.defaultCharset());
        List<String> fileLines=read(fileSource);
        List<String> urlLines=read(urlSource);
        System.out.println(fileLines.toString());
    }

    public static void main(String[] args) {
        GuavaIO guavaIO=new GuavaIO();
        guavaIO.test();
    }
}
