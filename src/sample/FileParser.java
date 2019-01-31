package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class FileParser extends File {

    private List<String> keys = new ArrayList<>();

    public FileParser(String pathname) {
        super(pathname);
    }

    public FileParser(String parent, String child) {
        super(parent, child);
    }

    public FileParser(File parent, String child) {
        super(parent, child);
    }

    public FileParser(URI uri) {
        super(uri);
    }

    public List<String> getKeys(String key) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(new File(this.getPath())));
        String line = "";
        while((line = br.readLine()) != null){
            if(key == null){
                if(!keys.contains(line.split(":")[0])){
                    keys.add(line.split(":")[0]);
                }
            } else {
                String[] args = line.split(";");
                if(args[0].contains(key)){
                   String[] keysList = args[0].split(":");
                   if(!keys.contains(keysList[keysList.length - 1])){
                       keys.add(keysList[keysList.length - 1]);
                   }
                }
            }
        }
        return keys;
    }

    public String contains(String needle, boolean multiple, String returnOnNull) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(new File(this.getPath())));
        String line = "";
        while((line = br.readLine()) != null){
            String[] args = line.split(";");
            String[] haystack = args[1].split("-");
            if(haystack[0].contains(needle.toLowerCase())){
                if(args[0].contains(":") && !multiple){
                    String[] a = args[0].split(":");
                    return a[a.length - 1];
                }
                return args[0];
            }

        }
        return returnOnNull;
    }

    public Object get(String key) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(new File(this.getPath())));
        String line = "";
        while((line = br.readLine()) != null){
            String[] args = line.split(";");
            if(args[0].equals(key)){
                return args[1];
            }
        }
        return null;
    }

}
