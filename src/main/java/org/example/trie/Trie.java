package org.example.trie;

import java.util.List;

/**
 * Trie(트라이) 자료구조를 정의하는 최상위 인터페이스입니다.
 * <p>Trie는 'Retrieval(검색)'의 약자에서 유래되었으며, 문자열 세트를 효율적으로 저장하고
 * 탐색하기 위한 트리 기반의 자료구조입니다. 각 노드는 문자열의 한 문자를 나타내며,
 * 루트에서 특정 노드까지의 경로가 곧 하나의 접두사(Prefix) 또는 단어가 됩니다.</p>
 * <p>주요 특징:</p>
 * <ul>
 * <li>시간 복잡도: 삽입 및 탐색 시 문자열의 길이에 비례하는 O(L) 성능을 보장합니다.</li>
 * <li>공유 접두사: 공통된 접두사를 가진 단어들이 노드를 공유하여 메모리 중복을 방지합니다.</li>
 * <li>활용 분야: 자동완성, 사전 구현, IP 라우팅, 철자 교정 등</li>
 * </ul>
 */
public interface Trie {

    /**
     * 새로운 단어를 트리에 삽입합니다.
     * @param word 삽입할 단어 (null 또는 빈 문자열은 무시될 수 있음)
     * @return 단어가 성공적으로 새로 삽입되었으면 true, 이미 존재하거나 삽입에 실패하면 false
     */
    boolean insert(String word);

    /**
     * 특정 단어가 트리에 완전한 형태로 존재하는지 확인합니다.
     * @param word 검색할 단어
     * @return 단어가 존재하고 마지막 글자가 단어의 끝(endOfWord)으로 표시되어 있으면 true
     */
    boolean search(String word);

    /**
     * 특정 접두사로 시작하는 단어가 트리에 하나라도 존재하는지 확인합니다.
     * @param prefix 확인하려는 접두사
     * @return 해당 접두사를 포함하는 경로가 트리 내에 존재하면 true
     */
    boolean startsWith(String prefix);

    /**
     * 특정 단어를 트리에서 삭제합니다.
     * <p>단어 삭제 시, 다른 단어의 접두사가 아니며 자식 노드가 없는 노드들은
     * 메모리 절약을 위해 물리적으로 제거(Pruning)됩니다.</p>
     * @param word 삭제할 단어
     */
    void delete(String word);

    /**
     * 주어진 접두사로 시작하는 모든 단어의 목록을 반환합니다.
     * 주로 자동완성 기능을 구현할 때 사용됩니다.
     * @param prefix 검색할 접두사
     * @return 접두사를 포함하는 모든 단어의 리스트 (결과가 없으면 빈 리스트 반환)
     */
    List<String> getWordWithPrefix(String prefix);
}