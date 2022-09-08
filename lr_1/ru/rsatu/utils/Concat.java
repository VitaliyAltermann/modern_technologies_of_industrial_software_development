package ru.rsatu.utils;

public class Concat {

   public static void Print(Concat cnct)
   {
      System.out.println(cnct.Get());
   }

   public void Add(String str)
   {
      mTxt += str;
   }
   
   public String Get()
   {
      return mTxt;
   }

   private String mTxt = "";
}