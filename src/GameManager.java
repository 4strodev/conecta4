import java.io.*;
import java.nio.file.Path;

public class GameManager {
    public static Board loadGame(String path) throws Exception {
        File file = Path.of(path).toFile();
        if (!file.exists()) {
            boolean success = file.createNewFile();
            if (!success) {
                throw new Exception("Cannot create file");
            }
        }

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
        Board board = (Board) in.readObject();
        in.close();
        return board;
    }

    public static void saveGame(String path, Board board) throws Exception {
        File file = Path.of(path).toFile();
        if (!file.exists()) {
            boolean success = file.createNewFile();
            if (!success) {
                throw new Exception("Cannot create file");
            }
        }
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(board);
        out.close();
    }
}
