package com.github.brzozas.generictype;

import java.lang.reflect.ParameterizedType;

public abstract class GenericAbstractClass<T> {

    public abstract T genericOperation(T type);

    /**
     * Retrieves generic type of class extends this abstract.
     * If there is more specific abstract class extends this one this method have to be override
     * @return
     */
    public Class<T> getGenericType() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
