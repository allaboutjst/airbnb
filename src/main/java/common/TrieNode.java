package common;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    char c;
    Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean isLeaf;

    public TrieNode() {
    }

    public TrieNode(char c) {
        this.c = c;
    }
}
