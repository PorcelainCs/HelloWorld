package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JavaIO {
    public List<String> read(String path){
        List<String> lines=new ArrayList<>();
        File file=new File(path);
        BufferedReader reader=null;
        try{
            reader=new BufferedReader(new FileReader(file));
            String line=null;
            while((line=reader.readLine())!=null){
                lines.add(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return lines;
    }
    public void write(String path,List<String> lines){
        File file=new File(path);
        PrintWriter writer=null;
        try {
            writer=new PrintWriter(new FileWriter(file));
            for(String line:lines){
                writer.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writer!=null){
                writer.close();
            }
        }
    }
}
