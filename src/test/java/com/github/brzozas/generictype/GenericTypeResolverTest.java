package com.github.brzozas.generictype;

import org.junit.Assert;
import org.junit.Test;

public class GenericTypeResolverTest {

    @Test
    public void interfaceGenericTypeResolveTest() {
        //given
        GenericIface<MySimpleType> objectUnderTest = new GenericIfaceImpl();
        //when
        Class genericType = objectUnderTest.getGenericType();
        //then
        Assert.assertEquals(genericType, MySimpleType.class);
    }

    @Test
    public void abstractClassGenericTypeResolveTest() {
        //given
        GenericAbstractClass<MySimpleType> objectUnderTest = new GenericAbstractClassImpl();
        //when
        Class genericType = objectUnderTest.getGenericType();
        //then
        Assert.assertEquals(genericType, MySimpleType.class);
    }
}
