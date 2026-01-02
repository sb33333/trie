package org.example.trie;

import org.example.trie.Trie;
import org.example.trie.TrieImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.List;

public class TrieTest {

    Logger log = LoggerFactory.getLogger(TrieTest.class);

    Trie trie;

    @BeforeEach
    void setUp () {
        trie = new TrieImpl();
    }

    @AfterEach
    void tearDown() {
        trie = null;
    }

    @Test
    void insertTest() {
        final var word = "apple";
        Assertions.assertTrue(trie.insert(word));

        log.info(trie::toString);
    }

    @Test
    void searchTest () {
        final var word = "apple";
        Assertions.assertTrue(trie.insert(word));
        Assertions.assertTrue(trie.search(word));
        log.info(trie::toString);
    }

    @Test
    void startsWithTest() {
        final var word = "applepie";
        final var prefix = "apple";

        trie.insert(prefix);
        Assertions.assertTrue(trie.startsWith(prefix));
        trie.insert(word);
        Assertions.assertTrue(trie.startsWith(prefix));

        log.info(trie::toString);
    }

    @Test
    void deleteTest () {
        final var word = "apple";

        trie.insert(word);
        Assertions.assertTrue(trie.search(word));

        trie.delete(word);
        Assertions.assertFalse(trie.search(word));

        log.info(trie::toString);
    }

    @Test
    void getWordWithPrefixTest() {
        final var words = List.of("applepie", "applejuice", "applejam");

        words.forEach(word -> trie.insert(word));
        words.forEach(word -> Assertions.assertTrue(trie.search(word)));

        List<String> contains = trie.getWordWithPrefix("apple");

        Assertions.assertTrue(contains.containsAll(words));
        Assertions.assertTrue(words.containsAll(contains));

        log.info(trie::toString);
    }
}