/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FileManagment;

import States.Game.MainGame.GameObjects.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

/**
 * In getMap:
 * - finish .player reader, and .space reader
 * - make the map, and return it.
 *
 * @author Yoseph
 */

public class MapReader {

    public static final String PATH = System.getProperty("user.home")  + "\\.Deturian\\saves\\";
    private static BufferedReader r;
    /**
     * stores the names of all block, and item classes, also other game objects.
     */
    private static String[] typeName;
    private static File directory;
    private static String[] files;


    public static void init() {
        directory = new File(PATH);
        if(!directory.exists()) {
            directory.mkdirs();
        }
        files = directory.list();
        File gameTypes = new File(new File("").getAbsoluteFile() + "\\src\\States\\Game\\MainGame\\GameObjects");
        if(!gameTypes.exists())
            gameTypes = new File(new File("").getAbsoluteFile() + "\\States\\Game\\MainGame\\GameObjects");
        System.out.print(gameTypes.getAbsolutePath() + ": ");
        typeName = (gameTypes).list();
        System.out.println(" has " + typeName.length);
        for (int i = 0; i < typeName.length; i++) {
            typeName[i] = typeName[i].substring(0, typeName[i].indexOf("."));
        }
    }


    /**
     * returns the list of all saves in the saves directory.
     * <p/>
     * If you are manually making saves, make sure that the save is a file, has  .planet,  .space, and  .player files, or else the loading will not occur properly.
     *
     * @return names of the saves
     */
    public static String[] getSaveNames() {
        return files;
    }

    /**
     * returns a map object of the specified save if exists.
     * Reads all the planets, players, and space files and makes objects of all, and adds it to a Map object.
     * <p/>
     * The block and item objects are made using the name of the block instance, to make sure your block or item can be read with this version of the loader, make sure the constructor is as follows.
     * ClassName(float.class, float.class, int.class)
     *
     * @param save
     * @return
     */
    public static Map getMap(String save) {
        Map curMap = null;
        Player player = null;
        long seed = 0;
        Space space = null;
        LinkedList<Planet> planets = new LinkedList<>();
        try {
            File curDirectory = new File(PATH + save);
            String[] currentFiles = curDirectory.list();
            for (String currentFile : currentFiles) {
                try (BufferedReader br = new BufferedReader(new FileReader(curDirectory.getAbsolutePath() + "\\" + currentFile))) {
                    if (currentFile.substring(currentFile.indexOf(".")).equals(".planet")) {
                        float posX = 0;
                        float posY = 0;
                        float radius = 0;
                        LinkedList<Chunk> chunks = new LinkedList<>();
                        String planetID = currentFile.substring(0, currentFile.indexOf("-"));
                        int planetNumber = Integer.parseInt(currentFile.substring(currentFile.indexOf("-") + 1, currentFile.indexOf(".")));
                        String name = null;
                        String imagePath = null;

                        String currentLine;
                        while ((currentLine = br.readLine()) != null) {
                            if (!currentLine.substring(0, 2).equals("//")) {
                                switch (currentLine.substring(0, currentLine.indexOf("="))) {
                                    case "name":
                                        name = currentLine.substring(currentLine.indexOf("\"") + 1, currentLine.indexOf("\"", currentLine.indexOf("\"") + 1));
                                        break;
                                    case "position":
                                        posX = Float.parseFloat(currentLine.substring(currentLine.indexOf("{") + 1, currentLine.indexOf(",")));
                                        posY = Float.parseFloat(currentLine.substring(currentLine.indexOf(",") + 1, currentLine.indexOf("}")));
                                        break;
                                    case "radius":
                                        radius = Float.parseFloat(currentLine.substring(currentLine.indexOf("=") + 1, currentLine.indexOf(";")));
                                        break;
                                    case "image":
                                        imagePath = currentLine.substring(currentLine.indexOf("\"") + 1, currentLine.indexOf("\"", currentLine.indexOf("\"") + 1));
                                        break;
                                }

                                if (currentLine.substring(0, 5).equals("chunk")) {
                                    int index = currentLine.indexOf("(");
                                    int chunkPos = Integer.parseInt(currentLine.substring(currentLine.indexOf("(") + 1, currentLine.indexOf(")")));
                                    LinkedList<BlockObject> b = new LinkedList<>();
                                    LinkedList<ItemObject> i = new LinkedList<>();
                                    while (index != -1) {
                                        String currentBlock = currentLine.substring(currentLine.indexOf("(", index + 1), currentLine.indexOf(")", currentLine.indexOf(")", index + 1) + 1) + 1);
                                        String blockName = currentBlock.substring(1, currentBlock.indexOf(","));
                                        float bx = Float.parseFloat(currentBlock.substring(currentBlock.indexOf(",") + 1, currentBlock.indexOf(",", currentBlock.indexOf(",") + 1)));
                                        float by = Float.parseFloat(currentBlock.substring(currentBlock.indexOf(",", currentBlock.indexOf(",") + 1) + 1, currentBlock.indexOf(",", currentBlock.indexOf(",", currentBlock.indexOf(",") + 1) + 1)));
                                        int s = Integer.parseInt(currentBlock.substring(currentBlock.indexOf(",", currentBlock.indexOf(",", currentBlock.indexOf(",") + 1) + 1) + 1, currentBlock.indexOf(")")));

                                        try {
                                            if (blockName.substring(0, 5).equals("Block")) {
                                                b.add(((BlockObject) (Class.forName("States.Game.MainGame.GameObjects." + blockName).getConstructor(float.class, float.class, int.class).newInstance(bx, by, s))));
                                            } else if (blockName.substring(0, 4).equals("Item")) {
                                                i.add(((ItemObject) (Class.forName("States.Game.MainGame.GameObjects." + blockName).getConstructor(float.class, float.class, int.class).newInstance(bx, by, s))));
                                            } else
                                                System.out.println("Unknown Type: " + blockName + "\n	Will this and proceed to next");
                                        } catch (Exception e) {
                                            System.out.println("Unexpected Error in space, found " + blockName + ", but could not make instance. \nGame will continue and ignore this error");
                                        }

                                        index = currentLine.indexOf("(", index + 1);
                                        if (currentLine.indexOf("(", index + 1) == -1)
                                            break;

                                    }
                                    chunks.add(new Chunk(b, i, chunkPos));
                                }
                            }
                        }
                        planets.add(new Planet(posX, posY, radius, planetNumber, planetID, name, imagePath, chunks, seed));

                    } else if (currentFile.substring(currentFile.indexOf(".")).equals(".player")) {
                        float x = 0;
                        float y = 0;
                        int s = 20;
                        String name = null;
                        String l = null;
                        LinkedList<ItemObject> items = new LinkedList<>();


                        String currentLine;
                        while ((currentLine = br.readLine()) != null) {
                            if (!currentLine.substring(0, 2).equals("//")) {
                                int index = 0;
                                switch (currentLine.substring(0, currentLine.indexOf("="))) {
                                    case "name":
                                        name = currentLine.substring(currentLine.indexOf("\"") + 1, currentLine.indexOf("\"", currentLine.indexOf("\"") + 1));
                                        break;
                                    case "location":
                                        l = currentLine.substring(currentLine.indexOf("\"") + 1, currentLine.indexOf("\"", currentLine.indexOf("\"") + 1));
                                        break;
                                    case "position":
                                        x = Float.parseFloat(currentLine.substring(currentLine.indexOf("{") + 1, currentLine.indexOf(",")));
                                        y = Float.parseFloat(currentLine.substring(currentLine.indexOf(",") + 1, currentLine.indexOf("}")));
                                        break;
                                    case "health":
                                        s = Integer.parseInt(currentLine.substring(currentLine.indexOf("=") + 1, currentLine.indexOf(";")));
                                        break;
                                    case "items":
                                        index = 0;
                                        index = currentLine.indexOf("(");
                                        while (index != -1) {
                                            //have a string that looks like "(className,0,0,20)"
                                            String currentItem = currentLine.substring(index, currentLine.indexOf(")", index + 1) + 1);
                                            String itemName = currentItem.substring(1, currentItem.indexOf(","));
                                            float bx = Float.parseFloat(currentItem.substring(currentItem.indexOf(",") + 1, currentItem.indexOf(",", currentItem.indexOf(",") + 1)));
                                            float by = Float.parseFloat(currentItem.substring(currentItem.indexOf(",", currentItem.indexOf(",") + 1) + 1, currentItem.indexOf(",", currentItem.indexOf(",", currentItem.indexOf(",") + 1) + 1)));
                                            int tempS = Integer.parseInt(currentItem.substring(currentItem.indexOf(",", currentItem.indexOf(",", currentItem.indexOf(",") + 1) + 1) + 1, currentItem.indexOf(")")));
                                            try {
                                                items.add(((ItemObject) Class.forName("States.Game.MainGame.GameObjects." + itemName).getConstructor(float.class, float.class, int.class).newInstance(bx, by, tempS)));
                                            } catch (Exception e) {
                                                System.out.println("Unexpected Error in player, found " + itemName + ", but could not make instance \nGame will continue and ignore this error");
                                            }


                                            index = currentLine.indexOf("(", index + 1);
                                        }
                                        break;
                                }
                            }
                        }
                        player = new Player(x, y, s, l, items);
                    } else if (currentFile.substring(currentFile.indexOf(".")).equals(".space")) {
                        LinkedList<Chunk> chunks = new LinkedList<>();
                        String currentLine;
                        while ((currentLine = br.readLine()) != null) {
                            if (currentLine.length() >= 2 && !currentLine.substring(0, 2).equals("//")) {
                                if (currentLine.substring(0, 4).equals("seed")) {
                                    seed = Long.parseLong(currentLine.substring(currentLine.indexOf("=") + 1, currentLine.indexOf(";")));
                                }
                                if (currentLine.substring(0, 5).equals("chunk")) {
                                    int index = currentLine.indexOf("(");
                                    int chunkPos = Integer.parseInt(currentLine.substring(currentLine.indexOf("(") + 1, currentLine.indexOf(")")));
                                    LinkedList<BlockObject> b = new LinkedList<>();
                                    LinkedList<ItemObject> i = new LinkedList<>();
                                    while (index != -1) {
                                        String currentBlock = currentLine.substring(currentLine.indexOf("(", index + 1), currentLine.indexOf(")", currentLine.indexOf(")", index + 1) + 1) + 1);
                                        String blockName = currentBlock.substring(1, currentBlock.indexOf(","));
                                        float bx = Float.parseFloat(currentBlock.substring(currentBlock.indexOf(",") + 1, currentBlock.indexOf(",", currentBlock.indexOf(",") + 1)));
                                        float by = Float.parseFloat(currentBlock.substring(currentBlock.indexOf(",", currentBlock.indexOf(",") + 1) + 1, currentBlock.indexOf(",", currentBlock.indexOf(",", currentBlock.indexOf(",") + 1) + 1)));
                                        int s = Integer.parseInt(currentBlock.substring(currentBlock.indexOf(",", currentBlock.indexOf(",", currentBlock.indexOf(",") + 1) + 1) + 1, currentBlock.indexOf(")")));

                                        try {
                                            if (blockName.substring(0, 5).equals("Block")) {
                                                b.add(((BlockObject) (Class.forName("States.Game.MainGame.GameObjects." + blockName).getConstructor(float.class, float.class, int.class).newInstance(bx, by, s))));
                                            } else if (blockName.substring(0, 4).equals("Item")) {
                                                i.add(((ItemObject) (Class.forName("States.Game.MainGame.GameObjects." + blockName).getConstructor(float.class, float.class, int.class).newInstance(bx, by, s))));
                                            } else
                                                System.out.println("Unknown Type: " + blockName + "\n	Will this and proceed to next");
                                        } catch (Exception e) {
                                            System.out.println("Unexpected Error in space, found " + blockName + ", but could not make instance. \nGame will continue and ignore this error");
                                        }

                                        index = currentLine.indexOf("(", index + 1);
                                        if (currentLine.indexOf("(", index + 1) == -1)
                                            break;

                                    }
                                    chunks.add(new Chunk(b, i, chunkPos));
                                }
                            }
                        }
                        space = new Space(chunks);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to load");
            e.printStackTrace();
        }
        curMap = new Map(save, seed, false, planets, space, player);
        return curMap;
    }
}