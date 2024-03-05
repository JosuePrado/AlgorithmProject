import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {

    private File file1;
    private File file2;
    private String file1Contend;
    private String file2Contend;

    public FileManager() {
        file1Contend = "";
        file2Contend = "";
    }

    public void readFiles(String path1, String path2) {
        try {
            file1 = new File(path1);
            file2 = new File(path2);
            file1Contend = readFileContend(file1);
            file2Contend = readFileContend(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFileContend(File file ) throws IOException {
        String fileContend = "";
        FileReader fileReader = new FileReader(file);

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            fileContend = fileContend.concat(line);
        }
        bufferedReader.close();
        return fileContend;
    }

    public String getFile1Contend() {
        return file1Contend.trim().toLowerCase().replace(".","");
    }

    public String getFile2Contend() {
        return file2Contend.trim().toLowerCase().replace(".","");
    }
}


