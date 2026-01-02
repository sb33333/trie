package org.example.trie;

import java.util.List;

public class TrieImpl implements Trie {

    private final TrieNode root = new TrieNodeImpl();

    @Override
    public boolean insert(String word) {
        if (word == null || word.isBlank()) return false;
        return root.append(word);
    }

    @Override
    public boolean search(String word) {
        if(word==null || word.isBlank()) return false;
        TrieNode node = searchNode_internal(word);
        return (node != null && node.isEndOfWord());
    }

    @Override
    public boolean startsWith(String prefix) {
        if (prefix==null || prefix.isBlank()) return false;
        return searchNode_internal(prefix)!=null;}

    @Override
    public void delete (String word){
        if (word == null || word.isBlank()) return;
        root.remove(word);
    }

    @Override
    public List<String> getWordWithPrefix(String prefix){
        if (prefix == null || prefix.isBlank()) return List.of();

        TrieNode node = searchNode_internal (prefix);

        if (node == null) return List.of();

        return node.collectWords(prefix);
    }

    private TrieNode searchNode_internal (String word) {
        TrieNode node = root;
        int len = word.length();
        for (int index = 0; index < len; index++) {
            char c = word.charAt(index);
            TrieNode tmp = node.getChildNode(c);
            if (tmp == null) return null;
            node = tmp;
        }
        return node;
    }

    @Override
    public String toString() {
        return "TrieImpl " +
                root.collectWords("").toString()
                ;
    }
}
