public class t {
    public static void main(String[] args){

//        B b=new B();
//        b.b=3;
//        A a=new A();
//        B b2=(B)a;
//        System.out.println(b2.b);
        try {
            try {
                throw new E2();
            }catch (E1 e){
                throw e;
            }
        }catch (E2 e2){
            System.out.println("e2");
            return;
        }finally {
            System.out.println("f");
        }


    }
}
class A{
    public int a=1;
}
class B extends A{
    public int b=2;
}
class E1 extends Exception{}
class E2 extends E1{}