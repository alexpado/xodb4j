package fr.alexpado.xodb4j.interfaces;

import fr.alexpado.xodb4j.impl.Category;
import fr.alexpado.xodb4j.interfaces.common.Identifiable;
import fr.alexpado.xodb4j.interfaces.common.Nameable;

public interface ICategory extends Identifiable<Integer>, Nameable {

    ICategory DEFAULT = new Category(0, "No Category");

}
