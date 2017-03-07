package com.github.brzozas.generictype;

public class GenericIfaceImpl implements GenericIface<MySimpleType> {

    @Override
    public MySimpleType genericOperation(MySimpleType type) {
        return type;
    }
}
