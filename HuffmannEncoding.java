import java.util.*;

// Node structure for Huffman Tree
class Node {
    char ch;
    int freq;
    Node left, right;

    Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }

    Node(int freq, Node left, Node right) {
        this.ch = '-'; // internal node
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
}

// Comparator to sort nodes by frequency (smallest first)
class HuffmanComparator implements Comparator<Node> {
    public int compare(Node a, Node b) {
        return a.freq - b.freq;
    }
}

public class HuffmannEncoding {

    // Recursive function to print Huffman Codes
    static void printCodes(Node root, String code) {
        if (root == null) return;

        // Leaf node => print character and its code
        if (root.left == null && root.right == null) {
            System.out.println(root.ch + " : " + code);
            return;
        }

        // Traverse left with '0' and right with '1'
        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of characters: ");
        int n = sc.nextInt();

        char[] chars = new char[n];
        int[] freq = new int[n];

        System.out.println("Enter characters and their frequencies:");
        for (int i = 0; i < n; i++) {
            System.out.print("Character " + (i + 1) + ": ");
            chars[i] = sc.next().charAt(0);
            System.out.print("Frequency of " + chars[i] + ": ");
            freq[i] = sc.nextInt();
        }

        // Step 1: Create a priority queue (min-heap)
        PriorityQueue<Node> pq = new PriorityQueue<>(new HuffmanComparator());

        // Step 2: Create leaf nodes and add to the queue
        for (int i = 0; i < n; i++) {
            pq.add(new Node(chars[i], freq[i]));
        }

        // Step 3: Build Huffman Tree
        while (pq.size() > 1) {
            Node left = pq.poll();  // smallest frequency
            Node right = pq.poll(); // next smallest

            Node newNode = new Node(left.freq + right.freq, left, right);
            pq.add(newNode);
        }

        // Step 4: Root of the tree
        Node root = pq.poll();

        System.out.println("\nHuffman Codes for the given characters:");
        printCodes(root, "");
    }
}
