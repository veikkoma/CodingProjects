package oy.tol.tra;

public class Grades {

   private Integer [] grades = null;

   /**
    * A constructor for building IntArrays.
    * @param grades the plain Java integer array with numbers to add.
    */
   public Grades(Integer [] grades) {
      this.grades = new Integer [grades.length];
      for (int counter = 0; counter < grades.length; counter++) {
         this.grades[counter] = grades[counter];
      }
   }

   public void reverse() {
      //luodaan käänteinen järjestetys
      int i = 0;
      while (i < grades.length/2) {
         int temp = grades[i];
         grades[i] = grades[grades.length-i-1];
         grades[grades.length-i-1] = temp;
         i++;
      }

   }

   public void sort() {
      //listan järjestämistä käytetään bubble sort tapaa
      boolean swapped = true;
      int temp;

      while (swapped) {
         swapped = false;
         for (int i = 0; i < grades.length - 1; i++) {
               if (grades[i] > grades[i + 1]) {
                  temp = grades[i];
                  grades[i] = grades[i + 1];
                  grades[i + 1] = temp;
                  swapped = true;
         }
      }
   }
}

   //palautetaan grades matriisi
   public Integer [] getArray() {
      return grades;
   }
}
