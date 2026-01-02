package org.example.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieNodeImpl implements TrieNode {
    private final Map<Character, TrieNode> children = new HashMap<>();
    private boolean endOfWord = false;

    // --- Public API ---

    @Override
    public boolean append(String word) {
        return append(word, 0); // 내부 재귀 호출
    }

    @Override
    public boolean remove(String word) {
        return remove(word, 0); // 내부 재귀 호출
    }

    @Override
    public List<String> collectWords(String prefix) {
        List<String> result = new ArrayList<>();
        collectWords(new StringBuilder(prefix), result);
        return result;
    }

    @Override
    public TrieNode getChildNode(char c) {
        return children.get(c);
    }

    @Override
    public boolean isEndOfWord() {
        return this.endOfWord;
    }
// --- Private Recursive Methods (구현 상세) ---

    private boolean append(String word, int index) {
        if (index == word.length()) {
            endOfWord = true;
            return true;
        }
        char c = word.charAt(index);
        TrieNode childNode = children.computeIfAbsent(c, k -> new TrieNodeImpl());
        return ((TrieNodeImpl)childNode).append(word, index + 1);
    }

    private boolean remove(String word, int index) {
        if (index == word.length()) {
            if (!endOfWord) return false;
            endOfWord = false;
            return children.isEmpty();
        }
        char c = word.charAt(index);
        TrieNode childNode = children.get(c);
        if (childNode == null) return false;

        boolean shouldDeleteChild = ((TrieNodeImpl)childNode).remove(word, index + 1);
        if (shouldDeleteChild) {
            children.remove(c);
            return children.isEmpty() && !endOfWord;
        }
        return false;
    }

    private void collectWords(StringBuilder pathBuilder, List<String> result) {
        if (endOfWord) result.add(pathBuilder.toString());
        for (Map.Entry<Character, TrieNode> entry : children.entrySet()) {
            pathBuilder.append(entry.getKey());
            ((TrieNodeImpl)entry.getValue()).collectWords(pathBuilder, result);
            pathBuilder.deleteCharAt(pathBuilder.length() - 1);
        }
    }


}