package inputoutput;

import charobjects.Text;
import textreaders.TextByteReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileTextReader extends TextByteReader {


    public Text readFile(File file) {
        try (BufferedReader bufferedReader =
                     new BufferedReader(new java.io.FileReader(addTechSymbolToFile(file)))) {
            int readerOutput;
            while ((readerOutput = bufferedReader.read()) != -1) {
                saveCharacterToText((char) readerOutput);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getText();
    }

    private File addTechSymbolToFile(File file) {
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.append('&');
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
