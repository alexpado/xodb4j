package fr.alexpado.xodb4j.interfaces;

import fr.alexpado.xodb4j.impl.Rarity;
import fr.alexpado.xodb4j.interfaces.common.Colorable;
import fr.alexpado.xodb4j.interfaces.common.Identifiable;
import fr.alexpado.xodb4j.interfaces.common.Nameable;

import java.awt.*;

public interface IRarity extends Identifiable<Integer>, Nameable, Colorable {

    IRarity DEFAULT = new Rarity(0, "No Rarity", Color.BLACK);

}
