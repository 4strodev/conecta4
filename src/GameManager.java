import java.io.*;
import java.nio.file.Path;

public class GameManager {
    public static Board loadGame(String path) throws Exception {
        createGamesFolder();
        File file = Path.of("games", path).toFile();
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
        createGamesFolder();
        File file = Path.of("games", path).toFile();
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

    private static void createGamesFolder() throws Exception {
        File gamesFolder = Path.of("games").toFile();
        if (!gamesFolder.exists()) {
            boolean success = gamesFolder.mkdirs();
            if (!success) {
                throw new Exception("Cannot create folder");
            }
            return;
        }

        if (!gamesFolder.isDirectory()) {
            throw new Exception(String.format("%s is not a directory", gamesFolder.getAbsolutePath()));
        }
    }
}
