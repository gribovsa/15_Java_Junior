package org.gribov.task3;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*todo
    Аннотация - это некий маркер, атрибут который можно  задать полю, классу
    как принадлежность к чему либо
 */
/**
 * Аннотация будет указывать на некоторую сущность принадлежности к таблице
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //говорит что создаваемая нами аннотация для какого то типа


public @interface Table {
    String name();
}
