package com.tiange;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<Integer> keys = new ArrayList<>();
        keys.add(1);
        keys.add(2);
        keys.add(3);
        keys.add(4);
        List<Integer> newkeys = keys.stream().filter(i -> (i % 2 == 0)).collect(Collectors.toList());
        ;
        System.out.println(newkeys);


    }
}
