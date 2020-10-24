public class Test {
  int x;

  Test() {
    x = 1;
  }

  void change() {
    x = 2;
  }

  public static void main(String args[]) {
    Test t = new Test();
    t = NULL;
    System.out.println(t.x);
    t.change();
    System.out.println(t.x);
  }
}
