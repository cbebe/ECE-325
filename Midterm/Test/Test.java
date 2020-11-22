interface ServiceA {
    void print();
}

class A implements ServiceA {
    public void print() {
        System.out.println("Class " + getName());
    }

    public String getName() {
        return "A";
    }
}

class B implements ServiceA {
    public void print() {
        System.out.println("Class " + getName());
    }

    public String getName() {
        return "B";
    }
}

class C extends A {
    public void print() {
        System.out.println("Class " + getName());
    }

    public String getName() {
        return "C";
    }
}

class D implements ServiceA {
    private A a;
    private B b;

    D(A a1) {
        a = a1;
        b = new B();
    }

    public void print() {
        System.out.println("Class " + getName());
        b.print();
        a.print();
    }

    public String getName() {
        return "D";
    }
}

public class Test {

    static void test(ServiceA obj) {
        System.out.println("----------------------");
        obj.print();
    }

    public static void main(String[] args) {
        A a1 = new C();
        A a2 = new A();
        D d1 = new D(a1);
        D d2 = new D(a2);
        test(d1);
        test(d2);
    }
}