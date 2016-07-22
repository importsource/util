package com.importsource.util.getfilename;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Hezf
 *
 */
public class GetFoldFileNames {
    public static void main(String[] args) {
    	List<String> lst=getFileName("c:/");
    	for(int i=0;i<lst.size();i++){
    		String fileName=lst.get(i);
    		System.out.println(fileName);
    		
    	}
    }

    public static List<String> getFileName(String dir) {
        File f = new File(dir);
        List<String> lst=new ArrayList<String>();
        if (!f.exists()) {
            System.out.println(dir + " not exists");
            return null;
        }

        File fa[] = f.listFiles();
        for (int i = 0; i < fa.length; i++) {
            File fs = fa[i];
            if (!fs.isDirectory()) {
                lst.add(fs.getName());
            } 
            
        }
        return lst;
    }
}