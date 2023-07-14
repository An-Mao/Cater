package anmao.idoll.cater.client;

public class ClientSanData {
    private static int playerSan;
    public static void set(int san){
        playerSan = san;
    }
    public static int getSan(){
        return playerSan;
    }
}
