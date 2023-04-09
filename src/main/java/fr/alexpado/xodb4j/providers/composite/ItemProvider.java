package fr.alexpado.xodb4j.providers.composite;

import fr.alexpado.xodb4j.providers.CategoryProvider;
import fr.alexpado.xodb4j.providers.FactionProvider;
import fr.alexpado.xodb4j.providers.RarityProvider;
import fr.alexpado.xodb4j.providers.TypeProvider;

public interface ItemProvider extends CategoryProvider, FactionProvider, RarityProvider, TypeProvider {
}
