package org.example.trie;

import java.util.List;

/**
 * Trie 자료구조의 개별 단위를 나타내는 노드 인터페이스입니다.
 * <p>각 노드는 자식 노드들에 대한 연결 정보를 관리하며, 자신이 하나의 완성된
 * 단어의 끝인지를 나타내는 상태값을 가집니다. 실제적인 트리 탐색과 노드 생성/삭제의
 * 재귀적 로직을 담당합니다.</p>
 */
public interface TrieNode {

    /**
     * 현재 노드 혹은 자식 노드에 단어를 재귀적으로 추가합니다.
     * @param word 추가할 단어의 남은 부분
     * @return 새로운 노드가 생성되거나 단어의 끝 상태가 변경되어 트리에 변화가 생기면 true
     */
    boolean append(String word);

    /**
     * 현재 노드의 하위 경로에서 특정 단어를 재귀적으로 제거합니다.
     * <p>삭제 과정에서 자식 노드가 더 이상 유효하지 않게 되면(자식이 없고 단어의 끝도 아님),
     * 부모 노드에게 해당 자식을 제거해야 함을 알립니다.</p>
     * * @param word 삭제할 단어의 남은 부분
     * @return 호출한 부모 노드에서 현재 노드를 삭제해도 되는지 여부 (자식이 없고 단어 끝이 아니면 true)
     */
    boolean remove(String word);

    /**
     * 현재 노드를 루트로 하는 하위 트리의 모든 완성된 단어를 수집합니다.
     * 백트래킹(Backtracking) 기법을 사용하여 경로를 탐색합니다.
     * @param prefix 현재 노드까지 도달하기 위해 구성된 접두사 문자열
     * @return 현재 노드 아래에 존재하는 모든 완성된 단어 리스트
     */
    List<String> collectWords(String prefix);

    /**
     * 특정 문자에 해당하는 자식 노드를 반환합니다.
     * @param c 찾으려는 문자
     * @return 자식 노드가 존재하면 해당 TrieNode, 없으면 null
     */
    TrieNode getChildNode(char c);

    /**
     * 현재 노드가 특정 단어의 마지막 글자인지 여부를 반환합니다.
     * @return 단어의 끝이면 true, 다른 단어의 중간 경로이거나 접두사일 뿐이면 false
     */
    boolean isEndOfWord();
}