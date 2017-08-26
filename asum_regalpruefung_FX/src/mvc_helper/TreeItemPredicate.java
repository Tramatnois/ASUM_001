/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_helper;

import java.util.function.Predicate;
import javafx.scene.control.TreeItem;

/**
 *
 * @author Stefan
 */
@FunctionalInterface
public interface TreeItemPredicate<T> {
 
    boolean test(TreeItem<T> parent, T value);
 
    static <T> TreeItemPredicate<T> create(Predicate<T> predicate) {
        return (parent, value) -> predicate.test(value);
    }
 
}
