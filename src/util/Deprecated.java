package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Deprecated {

    public static <T> void permutations(List<T> items, Stack<T> permutation, int size) {

        /* permutation stack has become equal to size that we require */
        if (permutation.size() == size) {
            /* print the permutation */
            System.out.println(Arrays.toString(permutation.toArray()));
        }

        /* items available for permutation */
        List<T> clone = new ArrayList<>(items);

        for (T i : clone) {
            /* add current item */
            permutation.push(i);

            /* remove item from available item set */
            items.remove(i);

            /* pass it on for next permutation */
            permutations(items, permutation, size);

            /* pop and put the removed item back */
            items.add(permutation.pop());
        }
    }

}
