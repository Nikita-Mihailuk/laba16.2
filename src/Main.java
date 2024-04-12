import java.io.*;
public class Main {
    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new FileReader("input_code.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("output_code.txt")))
        {
            String str;
            boolean inComment = false;

            while ((str = reader.readLine()) != null) {
                if (!inComment) {
                    int commentIndex = str.indexOf("//");
                    if (commentIndex != -1) {
                        str = str.substring(0, commentIndex);
                    }

                    int startCommentIndex = str.indexOf("/*");
                    int endCommentIndex = str.indexOf("*/");

                    if (startCommentIndex != -1) {
                        inComment = true;
                        str = str.substring(0, startCommentIndex);
                    }
                    else if (endCommentIndex != -1) {
                        inComment = false;
                        str = str.substring(endCommentIndex + 2);
                    }
                }
                else
                {
                    int endCommentIndex = str.indexOf("*/");
                    if (endCommentIndex != -1) {
                        inComment = false;
                        str = str.substring(endCommentIndex + 2);
                    }
                    else
                    {
                        continue;
                    }
                }

                writer.write(str + "\n");

            }
            System.out.println("Комментарии удалены, а код сохранен в output_code.txt");
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}