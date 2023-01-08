package oy.tol.tra;

public class StackFactory {

   private StackFactory() {
   }


   public static StackInterface<Integer> createIntegerStack(int capacity) {
      return new StackImplementation<>(capacity);
   }

   public static StackInterface<Character> createCharacterStack(int capacity) {
      return new StackImplementation<>(capacity);
   }
}
