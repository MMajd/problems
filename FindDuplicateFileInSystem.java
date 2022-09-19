/*
 
 @link https://leetcode.com/problems/find-duplicate-file-in-system
 @categories (map/list/vector/string)
  
 Given a list paths of directory info, including the directory path, and all the files with contents in this directory, return all the duplicate files in the file system in terms of their paths. You may return the answer in any order.

A group of duplicate files consists of at least two files that have the same content.

A single directory info string in the input list has the following format:

"root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"
It means there are n files (f1.txt, f2.txt ... fn.txt) with content (f1_content, f2_content ... fn_content) respectively in the directory "root/d1/d2/.../dm". Note that n >= 1 and m >= 0. If m = 0, it means the directory is just the root directory.

The output is a list of groups of duplicate file paths. For each group, it contains all the file paths of the files that have the same content. A file path is a string that has the following format:

"directory_path/file_name.txt"
 

Example 1:
    Input: paths = ["root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"]
    Output: [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]

Example 2:
    Input: paths = ["root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)"]
    Output: [["root/a/2.txt","root/c/d/4.txt"],["root/a/1.txt","root/c/3.txt"]]
 

Constraints:
    1 <= paths.length <= 2 * 104
    1 <= paths[i].length <= 3000
    1 <= sum(paths[i].length) <= 5 * 105
    paths[i] consist of English letters, digits, '/', '.', '(', ')', and ' '.
    You may assume no files or directories share the same name in the same directory.
    You may assume each given directory info represents a unique directory. A single blank space separates the directory path and file info.
 

Follow up:
    1.   Imagine you are given a real file system, how will you search files? DFS or BFS?
    2.1x If the file content is very large (GB level), how will you modify your solution?
    2.2  If you can only read the file by 1kb each time, how will you modify your solution?

    3. What is the time complexity of your modified solution? What is the most time-consuming part and memory-consuming part of it? 
    How to optimize?
    How to make sure the duplicated files you find are not false positive?
 
***************************************
*                                     *
*  FOLLOW UP QUESTIONS NOTES/ANSWERS  *
*                                     *
***************************************

@link https://leetcode.com/problems/find-duplicate-file-in-system/solution/458521
@link https://en.wikipedia.org/wiki/Locality_of_reference#:~:text=In%20computer%20science%2C%20locality%20of,a%20short%20period%20of%20time.

1. BFS vs DFS
    - BFS explores neighbors first. This means that files which are located close to each other are also accessed one after another. 
    This is great for space locality and that's why BFS is expected to be faster. 
    Also, BFS is easier to parallelize (more fine-grained locking). 
    - DFS will require a lock on the root node.

2. Very large files and false positives
For very large files we should do the following comparisons in this order:
    2.11 - Compare sizes, if not equal, then files are different and stop here!
    2.12 - Hash them with a fast algorithm e.g. MD5 or use SHA256 (no collisions found yet), if not equal then stop here!
    2.2x - Compare byte by byte to avoid false positives due to collisions.

Ex: Have you used an IDE in remote development mode?
For example, CLion has some options on how to compare the local files with the remote server files and then decides to synchronize or not.

3. Complexity: 
    Runtime - Worst case (which is very unlikely to happen): O(N^2 * L) where L is the size of the maximum bytes that need to be compared
    Space - Worst case: all files are hashed and inserted in the hashmap, so O(H^2*L), H is the hash code size and L is the filename size
*/


class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        int n = paths.length; 
        Map<String, List<String>> map = new HashMap<>();
        
        for (String path : paths) { 
            String[] pc = path.split(" ");
            
            for (int i=1; i<pc.length; i++) { 
                int j = pc[i].indexOf("(");
                int k = pc[i].indexOf(")");
                
                String data = pc[i].substring(j+1, k); 
                String filePath = pc[0] + "/" + pc[i].substring(0, j); 
                
                map.computeIfAbsent(data, _x -> new ArrayList<>()).add(filePath);
            }
        }
        
        
        List<List<String>> ans = new LinkedList<>(); 
        
        for (String key : map.keySet()) { 
            if (map.get(key).size() > 1) { 
                ans.add(map.get(key));
            }
        }
        
        return ans; 
    }
}
