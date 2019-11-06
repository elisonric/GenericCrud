package services;

import crud.AbstractCrud;
import crud.GenericCrud;
import models.Loterica;
import repositories.LotericaRepositorio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LotericaService {

	public List<Loterica> getAll() {
		AbstractCrud<Loterica> loterica = new AbstractCrud<>(Loterica.class);
		return loterica.findAll();
	}
}
