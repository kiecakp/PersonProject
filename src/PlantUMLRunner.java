import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// zad 2.1
public class PlantUMLRunner {
    public static String jarPath;

    public static void setJarPath(String jarPath) {
        PlantUMLRunner.jarPath = jarPath;
    }
    public static void generate(String data, String resultPath, String file){
        try{
            File catalog = new File(resultPath);        // tworzenie pliku
            catalog.mkdirs();

            String filePath = resultPath + '/' + file + ".txt";
            FileWriter writer = new FileWriter(filePath);
            writer.write(data);
            writer.close();

             // uruchamianiae procesu
            ProcessBuilder builder = new ProcessBuilder(
                    "java", "-jar", PlantUMLRunner.jarPath, filePath
            );
            Process process = builder.start();
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
