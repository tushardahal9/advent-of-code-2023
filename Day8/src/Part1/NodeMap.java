package Part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class NodeMap {
    private String instruction;
    private HashMap<String, Node> network;

    public NodeMap(String file) {
        try {
            File input = new File(file);
            Scanner reader = new Scanner(input);

            instruction = reader.nextLine();
            reader.nextLine();
            network = new HashMap<>();

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String start = line.split("=")[0].trim();
                String left = line.split("\\(")[1].split(",")[0];
                String right = line.split(",")[1].split("\\)")[0].trim();
                network.put(start, new Node(left, right));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public HashMap<String, Node> getNodeMap() {
        return network;
    }

    public int getSteps() {
        String[] instructionsArray = instruction.split("");

        int index = 0;
        int steps = 0;
        String currentNode = "AAA";
        while (!currentNode.equals("ZZZ")) {

            if (index == instructionsArray.length) index = 0;

            String currentInstruction = instructionsArray[index];
            if (currentInstruction.equals("L")) {
                currentNode = network.get(currentNode).getLeft();
            } else {
                currentNode = network.get(currentNode).getRight();
            }
            steps++;
            index++;
        }

        return steps;
    }
}
