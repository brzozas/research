package com.github.brzozas.generictype;

public class GenericAbstractClassImpl extends GenericAbstractClass<MySimpleType> {

    @Override
    public MySimpleType genericOperation(MySimpleType type) {
        return type;
    }
}
