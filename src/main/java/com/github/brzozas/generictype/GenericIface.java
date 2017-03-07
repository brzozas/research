package com.github.brzozas.generictype;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Optional;

public interface GenericIface<T> {

    T genericOperation(T type);

    /**
     * Retrieves generic type of class implements this interface.
     * If there is more specific interface extends this interface this method have to be override
     * @return
     */
    default Class<T> getGenericType() {
        Type[] interfacesTypes = this.getClass().getGenericInterfaces();
        Optional<Type> iface = Arrays.stream(interfacesTypes)
                .filter(i -> i.getTypeName().startsWith(GenericIface.class.getName()))
                .findFirst();
        if (iface.isPresent()) {
            return (Class<T>) (((ParameterizedType) iface.get()).getActualTypeArguments()[0]);
        }
        throw new IllegalStateException("Can't discover process context type");
    }
}
