package com.company;
public class Main {
    public static void main(String[] args) {
        //Функция 1
        System.out.println("Первая функция");
        String message = "Я хочу сегодня 09.10.2020 получить хэш от этого сообщения до 23:59 вечера по GMT+3:00 и потом спать";
        System.out.println(hashFunction1(message));
        //Функция 2
        System.out.println("Вторая функция");
        System.out.println(hashFunction2("Not a go0d hash23 15function 2020"));
        //System.out.println("---------------------------------------------------------------------------------------");
        //System.out.println(hashFunction2("papa pada deda maman olen lala azbuka los"));
    }
    //Методы Функции 1
    public static String hashFunction1(String message){
        int maxU=0;
        for(char sym:message.toCharArray()) {
            if(maxU<(int)sym) maxU=((int)sym);
        }
        String hash = "";
        for (String word : message.split(" ")) {
            //System.out.println("исходник: "+word+" / хэш: "+ rev(word, maxU));
            hash+=rev(word, maxU);
        }
        return hash;
    }
    public static String rev(String word, int maxU) {
        String hashword = "";
        int wordL=word.length();
        if (wordL==1){
            hashword+=0;
            hashword+=Integer.toHexString((int)word.charAt(0));
        }
        else if (wordL < 10) hashword+=1;
        else hashword+=(wordL/10)+1;
        hashword+=Integer.toHexString(wordL);
        for(char symb:word.toCharArray())
        {
            hashword+=codingTail(symb,maxU,wordL);
        }
        return hashword+" ";
    }
    public static String codingTail(char symb, int maxU, int wordL) {
        String[] tails=new String[3];
        String tail="Z";
        int delta=0;
        delta=(maxU-symb);
        tails[2]=Integer.toHexString(delta);
        tails[1]=Integer.toHexString(tails[2].length());
        tails[0]=Integer.toHexString(tails[1].length());

        return tail+tails[0]+tails[1]+tails[2];
    }
    //Методы Функции 2
    public static String hashFunction2(String data){

        String hash = "";
        for (String word : data.split(" ")) {
            //System.out.println("исходник: "+word+" / хэш: "+ rev(word));
            hash+=rev(word);
        }
        return hash;
    }
    public static String rev(String word) {
        char[] coding = {'A','7','X','1','Z','6','L','S','4','p','a','g','m','5','3'};
        char[] pair = new char[2];
        String neword =" ";
        if(word.length()==1) word+="F";
        int key;
        for(int i=0;i<word.length();i++)
        {
            if(i==word.length()-1)
            {
                pair[0]=word.charAt(i);
                pair[1]=word.charAt(0);
            }
            else{
                pair[0]=word.charAt(i);
                pair[1]=word.charAt(i+1);
            }
            key=Math.abs((int)pair[1]-(int)pair[0])%coding.length;
            key=(key+((int)pair[0])%100)%coding.length;
            pair[0]=word.charAt(0);
            pair[1]=word.charAt(1);

            neword+=coding[key];
            neword+=getSymb(word,coding);
        }

        return neword;
    }
    public static char getSymb(String word, char[] coding) {
        int k=(int)word.charAt(word.length()/2)%100;
        k+=Math.abs((int)word.charAt(0)-(int)word.charAt(word.length()-1))%100;
        char symb=coding[k%coding.length];
        return symb;
    }
}
