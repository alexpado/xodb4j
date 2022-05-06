package fr.alexpado.xodb4j.interfaces;

import fr.alexpado.xodb4j.impl.Type;
import fr.alexpado.xodb4j.interfaces.common.Identifiable;
import fr.alexpado.xodb4j.interfaces.common.Nameable;

public interface IType extends Identifiable<Integer>, Nameable {

    IType DEFAULT = new Type(0, "No Type");

}
