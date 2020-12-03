package me.kalpha.generic;

public class TestService {
    public static void main(String[] args) {
        TypeA ta = new TypeA();
        ta.setCol1("aaaa");
        ta.setCol2(10);

        TypeAB<TypeA> tab1 = new TypeAB<TypeA>(ta);
        System.out.println(tab1.get().toString());


        TypeB tb = new TypeB();
        tb.setCol1("bbbb");
        tb.setCol2(20);

        TypeAB<TypeB> tab2 = new TypeAB<TypeB>(tb);
        System.out.println(tab2.get().toString());
    }
}
