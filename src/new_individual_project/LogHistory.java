package new_individual_project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class LogHistory {

    public void printToFile(Message msg) throws IOException {
        BufferedWriter bw = null;
        FileWriter fw = null;
        String FILENAME = "Log_History.txt";

        try {

            File file = new File(FILENAME);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // true = append file
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);

            bw.write(msg.toString());
            bw.write("\n");

        } catch (IOException ex) {
            System.out.println(ex);
        } finally {

            try {

                if (bw != null) {
                    bw.close();
                }

                if (fw != null) {
                    fw.close();
                }

            } catch (IOException ex) {

                System.out.println(ex);
            }
        }
    }
}
