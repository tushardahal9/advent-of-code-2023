package Part1;

public class Part1 {
    public static void main(String[] args) {
        NodeMap nodeMap = new NodeMap("./src/input.txt");
        System.out.println(nodeMap.getSteps());
    }
}
