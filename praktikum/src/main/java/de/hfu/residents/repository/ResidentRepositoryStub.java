package de.hfu.residents.repository;

import java.util.ArrayList;
import java.util.List;

import de.hfu.residents.domain.Resident;

public class ResidentRepositoryStub implements ResidentRepository {
	
	private List<Resident> residents = new ArrayList<Resident>();

	@Override
	public List<Resident> getResidents() {
		// TODO Auto-generated method stub
		return residents;
	}
	
	public void addResident(Resident resident) {
		// TODO Auto-generated method stub
		residents.add(resident);
	}

}
