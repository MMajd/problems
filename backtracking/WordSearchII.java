/*
 @link https://leetcode.com/problems/word-search-ii/
 @categories (trie/dfs/bactracking/hash-table)

 Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

Example 1:
    Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
    Output: ["eat","oath"]

Example 2:
    Input: board = [["a","b"],["c","d"]], words = ["abcb"]
    Output: []
 

Constraints:
    m == board.length
    n == board[i].length
    1 <= m, n <= 12
    board[i][j] is a lowercase English letter.
    1 <= words.length <= 3 * 10^4
    1 <= words[i].length <= 10
    words[i] consists of lowercase English letters.
    All the strings of words are unique.
*/
class Solution { 
    private static final int[] DIRS = {-1, 0, 1, 0, -1}; 
    private static final char a = 'a'; 
    private static final char dash = '-'; 
    private int m, n; 


    private static class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }

    public List<String> findWords(char[][] B, String[] words) {
        m = B.length; 
        n = B[0].length; 

        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                search (B, i, j, root, res);
            }
        }
        
        return res;
    }
    
    public void search(char[][] B, int x, int y, TrieNode node, List<String> res) {
        if (x<0 || y<0 || x>=m || y>=n) return;
        if (B[x][y] == dash || node.next[B[x][y]-a] == null) return; 

        char c = B[x][y]; 
        B[x][y] = dash; 

        node = node.next[c-a];

        if (node.word != null) {
            res.add(node.word);
            node.word = null; 
        }

        for (int k=0; k<4; k++) {
            search(B, x+DIRS[k], y+DIRS[k+1], node, res);
        }

        B[x][y] = c;
    }
    
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();

        for (String word : words) {
            insertWord(root, word);
        }

        return root;
    }  

    private void insertWord(TrieNode root, String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (node.next[i] == null) node.next[i] = new TrieNode();
            node = node.next[i];
        }
        node.word = word;
    }
}

// Gives TLE, just added for reference 
class Solution2 {
    private static final int[] DIRS = {-1, 0, 1, 0, -1}; 
    private int M, N; 

    public List<String> findWords(char[][] B, String[] words) {
        M = B.length; 
        N = B[0].length;

        List<String> ans = new ArrayList<>(words.length); 
        Map<Character, List<int[]>> map = new HashMap<>(); 

        for (int i=0; i<M; i++) { 
            for (int j=0; j<N; j++) { 
                map.computeIfAbsent(B[i][j], k -> new ArrayList<>()).add(new int[]{i, j});
            }
        }

        for (String word : words) {
            for (int[] pos : map.getOrDefault(word.charAt(0), new ArrayList<>())) { 
                if (bt(B, pos[0], pos[1], word.toCharArray(), 1)) { 
                    ans.add(word);
                    break;
                }
            }
        }

        return ans; 
    }

    private boolean bt(char[][] B, int r, int c, char[] word, int x) {
        if (x == word.length) return true;
        if (r<0 || c<0 || r>=M || c>=N) return false;
        if (B[r][c] == '.') return false; 

        char t = B[r][c]; 
        B[r][c] = '.'; 

        boolean res = false; 

        for (int k=0; k<4; k++) { 
            int i = r + DIRS[k]; 
            int j = c + DIRS[k+1];

            if (i<0 || j<0 || i>=M || j>=N || B[i][j] != word[x]) continue; 

            if (bt(B, i, j, word, x+1)) { 
                res = true; 
                break;
            }
        }

        B[r][c] = t; 
        return res; 
    }
}
