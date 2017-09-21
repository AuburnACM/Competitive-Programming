public class UnionFind {
  int[] parent;

  UnionFind(int N) {
    parent = new int[N];
    for (int i = 0; i < N; i++) parent[i] = i;
  }

  public int find(int a) {
    if (parent[a] == a) return a;
    parent[a] = find(parent[a]);
    return parent[a];
  }

  public void union(int a, int b) {
    parent[find(a)] = find(b);
  }

  public static void main(String[] args) {
    UnionFind UF = new UnionFind(10);

    assert UF.find(0) != UF.find(1);
    UF.union(0, 1);
    assert UF.find(0) == UF.find(1);
    UF.union(1, 2);
    UF.union(2, 3);
    UF.union(5, 4);
    assert UF.find(5) == UF.find(4);
    assert UF.find(0) != UF.find(4);
    UF.union(4, 3);
    assert UF.find(0) == UF.find(4);
    assert UF.find(0) == UF.find(5);
  }
}
