/******************************************************************************
 * Copyright (c) 2015. By tuanna (Jackson Nguyen)                             *
 ******************************************************************************/

package com.tuanna.otto_lombokdemo.bus;

import com.tuanna.otto_lombokdemo.common.Word;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Otto-LombokDemo.
 * Created by tuanna on 28/10/2015.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
public class BusArrayListWord {
    @Getter
    private ArrayList<Word> wordArrayList;
}
