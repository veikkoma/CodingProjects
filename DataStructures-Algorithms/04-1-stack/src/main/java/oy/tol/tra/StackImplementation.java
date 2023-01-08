package oy.tol.tra;

public class StackImplementation<E> implements StackInterface<E> {


   private Object [] itemArray;
   private int currentIndex;
   private int capacity;

   public StackImplementation() throws StackAllocationException {
      this(10);
   }
   public StackImplementation(int capacity) throws StackAllocationException {

      if(capacity < 2){
         throw new StackAllocationException("Stack size should be more than 1");
      }
      try{
         itemArray = new Object[capacity];
         this.capacity = capacity;
         currentIndex = -1;

      }catch(Exception e){
         throw new StackAllocationException(e.getMessage());
      }
   }

   @Override
   public int capacity() {
      return capacity;
   }

   @Override
   public void push(E element) throws StackAllocationException, NullPointerException {
      if(element==null){
         throw new NullPointerException("Cannot push null elements to stack");
      }

      if(currentIndex >= capacity - 1){
         reallocateArray();
      }
      currentIndex++;
      itemArray[currentIndex] = element;

   }

   @SuppressWarnings("unchecked")
   @Override
   public E pop() throws StackIsEmptyException {
      if(isEmpty()){
         throw new StackIsEmptyException("Cannot pop from empty Stack.");
      }

      E element = (E) itemArray[currentIndex];
      itemArray[currentIndex] = null;
      currentIndex--;
      return element;
   }

   @SuppressWarnings("unchecked")
   @Override
   public E peek() throws StackIsEmptyException {
      if(isEmpty()){
         throw new StackIsEmptyException("Cannot peek from empty Stack.");
      }else{
         return (E) itemArray[currentIndex];
      }
   }

   @Override
   public int size() {
      int size = currentIndex + 1;
      return size;
   }

   @Override
   public void clear() {
      currentIndex = -1;
      itemArray = new Object[capacity];
   }

   @Override
   public boolean isEmpty() {
      return currentIndex < 0;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("[");

      for(int index = 0; index <= currentIndex; index++){
         builder.append(itemArray[index]);
         if(index < currentIndex){
            builder.append(", ");
         }
      }

      builder.append("]");
      return builder.toString();
   }

   private void reallocateArray(){

      try{
      int newCapacity = capacity * 2;
      Object [] newArray = new Object[newCapacity];

      for(int index = 0; index <= currentIndex; index++){
         newArray[index] = itemArray[index];
      }
      itemArray = newArray;
      capacity = newCapacity;

      }catch(Exception e){
         throw new StackAllocationException("No room for bigger array");
}
}
}
