package Part2;

import Part1.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class NodeMap {
    private String instruction;
    private HashMap<String, Part1.Node> network;

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
                network.put(start, new Part1.Node(left, right));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static long GCD(long a, long b)
    {
        while (b > 0)
        {
            long temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }

    private static long GCD(long[] input)
    {
        long result = input[0];
        for(int i = 1; i < input.length; i++) result = GCD(result, input[i]);
        return result;
    }

    public static long LCM(long a, long b) {
        return a * (b / GCD(a, b));
    }

    public static long LCM(ArrayList<Long> input) {
        long result = input.getFirst();
        for (int i = 1; i < input.size(); i++) result = LCM(result, input.get(i));
        return result;
    }

    public long getSteps() {
        String[] instructionsArray = instruction.split("");

        ArrayList<String> currentNodes = new ArrayList<>();
        ArrayList<Long> stepCount = new ArrayList<>();
        int index = 0;
        long steps = 0;
        boolean atEnd = false;

        for (String start : network.keySet()) {
            if (start.charAt(2) == 'A') currentNodes.add(start);
        }

        System.out.println(currentNodes);
        while (true) {

            if (stepCount.size() == currentNodes.size()) {
                System.out.println("aa");
                break;
            }

            for (String node : currentNodes) {
                if (node.charAt(2) != 'Z') {
                    ;
                } else {
                    stepCount.add(steps);
                    System.out.println(stepCount);
                }
            }

            if (atEnd) break;
            if (index == instructionsArray.length) index = 0;
            String currentInstruction = instructionsArray[index];
            for (int i = 0; i < currentNodes.size(); i++) {
                if (currentInstruction.equals("L")) {
                    currentNodes.set(i, network.get(currentNodes.get(i)).getLeft());
                } else {
                    currentNodes.set(i, network.get(currentNodes.get(i)).getRight());
                }
            }
            steps++;
            index++;
        }
        return LCM(stepCount);
    }
}
