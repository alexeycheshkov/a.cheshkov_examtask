/*
Class with different utilities for work with page data
 */

package utils;

import aquality.selenium.core.logging.Logger;
import com.google.common.collect.Ordering;

import java.util.List;
import java.util.stream.Collectors;

public class SortUtils {

    public static boolean isSorted(List<? extends Comparable> sortedList) {
        Logger.getInstance().debug("Checking sorting list");
        return Ordering.natural().reverse().isOrdered(sortedList);
    }
}
