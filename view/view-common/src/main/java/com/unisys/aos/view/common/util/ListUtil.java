/*
 * Copyright 2020 Unisys.com All right reserved. This software is the
 * confidential and proprietary information of Unisys.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Unisys.com
 */
package com.unisys.aos.view.common.util;

import java.util.List;

/**
 * @author LiuJ2
 * @since 2020/10/26 20:12
 */
public class ListUtil {
    /**
     * check if two list have same collections of elements.
     *
     * @param oneList     -  one list to compare
     * @param anotherList - another list to compare
     * @return true if equals
     */
    public static <T> boolean isListEqual(List<T> oneList, List<T> anotherList) {
        if (oneList == anotherList) {
            return true;
        }

        if (oneList == null || anotherList == null) {
            return false;
        }
        // element count is different so must no equal.
        if (oneList.size() != anotherList.size()) {
            return false;
        }
        // same element count and containsAll so must be equal.
        return oneList.containsAll(anotherList);
    }

}
