public class Fibonacci {
    public static void main(String[] args) {

        int i = 5;
        System.out.println("val i "+(i--));
        System.out.println("val i "+(i));
        System.out.println("parte 2 ");
        i = 5;
        System.out.println("val i "+(--i));
        System.out.println("val i "+(i));

        int pos = 14;
        Fibonacci tmp = new Fibonacci();
        int res = tmp.resolveF( pos);
        System.out.println("pos: "+pos + " es: "+res);
    }

    private int resolveF(int res){
        System.out.print(" 0 1");
        return resolveF(0, 1, res);
    }

    private int resolveF(int ant, int act, int res){
        if (res!=0){
            System.out.print(" "+(act+ant));
            return resolveF(act, (act+ant), --res);
        }

        return act;
    }

}


