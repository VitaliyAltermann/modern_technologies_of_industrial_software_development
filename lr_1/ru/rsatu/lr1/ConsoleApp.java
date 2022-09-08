package ru.rsatu.lr1;

import ru.rsatu.utils.Consts;
import ru.rsatu.utils.Concat;

public class ConsoleApp {

   public static void main(String[] args)
   {
      Concat concat = new Concat();
      concat.Add(Consts.EN_HELLO_WORLD);
      concat.Add(" - ");
      concat.Add(Consts.RU_HELLO_WORLD);
      Concat.Print( concat );
   }

}