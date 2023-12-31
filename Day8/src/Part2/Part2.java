package Part2;

public class Part2 {
    public static void main(String[] args) {
        NodeMap nodeMap = new NodeMap("./src/input.txt");
        System.out.println(nodeMap.getSteps());
    }
}