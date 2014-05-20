package com.example.puppy;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestDummy {

    @Test
    public void test() {
        assertThat(1, is(1));
    }
}
