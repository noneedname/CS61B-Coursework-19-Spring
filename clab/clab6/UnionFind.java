import java.util.ArrayList;
import java.util.List;

public class UnionFind {

    private int[] array;
    private int capacity;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("The vertex is not valid. ");
        }
        capacity = n;
        array = new int[n];
        for (int i = 0; i < capacity; i++) {
            array[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex < 0 || vertex >= capacity) {
            throw new IllegalArgumentException("The vertex is not valid. ");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        return -array[find(v1)];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return array[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        int root1 = find(v1);
        int root2 = find(v2);
        if (root1 == root2) {
            return;
        }

        int size1 = parent(root1);
        int size2 = parent(root2);
        //if v2-set is larger (caution: sizes are negative!)
        if (size1 >= size2) {
            array[root1] = root2;
            array[root2] = size1 + size2;
        } else {
            array[root2] = root1;
            array[root1] = size1 + size2;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        List<Integer> branch= new ArrayList<>();
        int parent = parent(vertex);
        while (parent >= 0) {
            branch.add(vertex);
            vertex = parent;
            parent = parent(vertex);
        }
        for (int item : branch) {
            array[item] = vertex;
        }
        return vertex;
    }
}
