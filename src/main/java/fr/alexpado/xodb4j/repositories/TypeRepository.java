package fr.alexpado.xodb4j.repositories;

import fr.alexpado.lib.rest.interfaces.IRestAction;
import fr.alexpado.xodb4j.XoDB;
import fr.alexpado.xodb4j.interfaces.IType;
import fr.alexpado.xodb4j.repositories.type.FindAllTypesAction;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class TypeRepository {

    private final XoDB xoDB;

    public TypeRepository(XoDB xoDB) {

        this.xoDB = xoDB;
    }

    /**
     * Retrieve every entity from the REST API of the current type.
     *
     * @return A {@link IRestAction}
     */
    public IRestAction<List<IType>> findAll() {

        return new FindAllTypesAction(this.xoDB);
    }

}
