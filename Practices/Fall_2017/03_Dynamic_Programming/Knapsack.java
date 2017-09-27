public class Knapsack {

  // Example Usage
  public static void main(String[] args) {
    Item[] items = new Item[] {
      new Item(700, 700),
      new Item(300, 299),
      new Item(500, 499),
    };

   assert maxValue(items, 800) == 798;
  }

  static class Item {
    int weight;
    int value;

    Item(int weight, int value) {
      this.weight = weight;
      this.value = value;
    }
  }

  static int maxValue(Item[] items, int maxWeight) {
    int[] best = new int[maxWeight + 1];
    for (int w = 1; w <= maxWeight; w++) {
      best[w] = best[w-1];
      for (Item item : items) {
        if (w - item.weight >= 0) {
          best[w] = Math.max(best[w-item.weight] + item.value, best[w]);
        }
      }
    }

    return best[maxWeight];
  }
}
