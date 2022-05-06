package fr.alexpado.xodb4j.interfaces;

import fr.alexpado.xodb4j.impl.Faction;
import fr.alexpado.xodb4j.interfaces.common.Identifiable;
import fr.alexpado.xodb4j.interfaces.common.Nameable;

public interface IFaction extends Identifiable<Integer>, Nameable {

    IFaction DEFAULT = new Faction(0, "No Faction");

}
