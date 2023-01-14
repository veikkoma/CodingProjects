package oy.tol.tra;

import java.util.function.Predicate;

public class Algorithms {
    public static <T extends Comparable<T>> void sort(T [] array){
        //listan järjestäminen pienimmästä isompaan
        T temp;
        int i = 1;
        int j = 0;
        while (i < array.length) {
           temp = array[i];
           j = i - 1;
           while((j >= 0) && (array[j].compareTo(temp) > 0)){
               array[j + 1] = array[j];
               j = j - 1;
           }
           array[j + 1] = temp;
           i = i+1;
    }
    }

     public static <T> void reverse(T [] array) {
        //listan järjestäminen järjestykseen (käänteinen)
        int i = 0;
        while ( i < array.length/2) {
            swap(array, i, (array.length - i - 1));
            i++;
        }
     }

     public static <T> void swap(T [] array, int first, int second) {
        // vaihto-operaatio algoritmissa
        T temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    public static class ModeSearchResult<T> {
        // pitää tallessa mikä mode on ja montako niitä on.
        public T theMode;
        public int count = 0;
    }

    /**
     * @param <T>
     * @param array
     * @return
     */
    public static <T extends Comparable<T>> ModeSearchResult<T> findMode(T [] array) {
        ModeSearchResult<T> result = new ModeSearchResult<>();
         // vertaillaan elementtejä toisiinsa ja etsitään modea
         result.theMode = null;
         result.count = -1;

        if (array == null || array.length < 2) {
            return result;
        }
        // jos edellä mainitut ehdot ovat kunnossa - lajitellaan taulukko.
        sort(array);

        //lähestytään olettamalla, että ensimmäinen arvo on kaikkein yleisin..
        result.theMode = array[0];
        result.count = 1;

        int topFrequency = 1;
        int tempFrequency = 1;
        int indexF = 1;

        // käydään lista läpi mikä on yleisin arvo - indeksi kerrallaan listan loppuun asti
        while (indexF < array.length){
            if (array[indexF].compareTo(array[indexF - 1]) == 0) {
                tempFrequency++;
            } else {
                if (tempFrequency > topFrequency) {
                    result.count = tempFrequency;
                    result.theMode = array[indexF - 1];
                    topFrequency = tempFrequency;
                }
                tempFrequency = 1;
            }
            indexF++;
            }
            if (tempFrequency > topFrequency) {
                    result.count = tempFrequency;
                    result.theMode = array[indexF - 1];
                    topFrequency = tempFrequency;
            }
            return result;
        }


        public static <T> int partitionByRule(T [] array, int count, Predicate<T> rule) {
            // Testataan predicaatin avulla testaako testi ehdot.
            if (null == array) {
                return -1;
            }
            int index = 0;
            for ( ; index < count; index++ ) {
                if (rule.test(array[index])) {
                    break;
                }
            }
            if (index >= count){
                return count;
            }
            int nIndex = index + 1;
            while (nIndex < count) {
                if (!rule.test(array[nIndex])) {
                    swap(array, index, nIndex);
                    index++;
                }
                nIndex++;
            }
            return index;
        }


    public static <T extends Comparable<T>> int binarySearch(T aValue, T [] fromArray, int fromIndex, int toIndex) {
        if (fromIndex == toIndex) {
            if (fromArray[fromIndex].compareTo(aValue) == 0) {
                return fromIndex;
            } else {
                return -1;
            }
            } else {
                int mid = fromIndex + (toIndex - fromIndex)/2;
                if (aValue.compareTo(fromArray[mid]) <= 0) {
                    return binarySearch(aValue, fromArray, fromIndex, mid);
                } else {
                    return binarySearch(aValue, fromArray, mid + 1, toIndex);
                }
            }
        }

    public static <E extends Comparable<E>> void fastSort(E[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static <E extends Comparable<E>> void quickSort(E[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private static <E extends Comparable<E>> int partition(E[] array, int low, int high) {
        E pivotValue = array[low];
        int i = low - 1;
        int j = high + 1;
        while (true) {
            do {
                i++;
            } while (array[i].compareTo(pivotValue) < 0);
            do {
                j--;
            } while (array[j].compareTo(pivotValue) > 0);
            if (i >= j) {
                return j;
            }
            swap(array, i, j);
        }
    }
}



