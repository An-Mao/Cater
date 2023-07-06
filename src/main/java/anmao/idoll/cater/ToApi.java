package anmao.idoll.cater;

import net.minecraft.util.RandomSource;

import java.util.Random;

public class ToApi {
    //
    public ToApi(){
        //
    }
    public static void echo(String con){
        //
        System.out.println(con);
    }
    private static int getRandomNumber(int min ,int max){
        return RandomSource.createNewThreadLocalInstance().nextInt(min,max);
        //Random random = new Random();
        //return random.nextInt(max) % (max - min + 1) + min;
    }
    /*
    *
    *  */
    public static boolean getRNwithLuck(float luck,float p){
        float lp = 0.0f;
        if(luck > 0){lp =  luck / ( 9 + luck );}
        lp = p * (1 + lp);
        int a = String.valueOf(lp).substring(2).length();
        int b = (int) Math.pow(10,a);
        float r = (float) getRandomNumber(1, b) / b;
        return r <= p;
    }
}
