package de.hfu.residents.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.repository.ResidentRepositoryStub;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentService;
import de.hfu.residents.service.ResidentServiceException;

public class ResidentRepositoryTest {

	@Test
	public void testGetFilteredResidentList1() {
		ResidentRepository residentRepository = new ResidentRepositoryStub();
		BaseResidentService residentService = new BaseResidentService();
		
		Resident r1 = new Resident("Vorname", "Nachname", "Strasse", "Stadt", null);
		Resident r2 = new Resident("Vorname2", "Nachname2", "Strasse2", "Stadt2", null);
		Resident r3 = new Resident("Max", "Mustermann", "Musterstrasse", "Musterstadt", null);
		
		((ResidentRepositoryStub) residentRepository).addResident(r1);
		((ResidentRepositoryStub) residentRepository).addResident(r2);
		((ResidentRepositoryStub) residentRepository).addResident(r3);
		
		residentService.setResidentRepository(residentRepository);
		
		Resident filter = new Resident("M*", "*", "", "*", null);
		List<Resident> filterResult = residentService.getFilteredResidentsList(filter);
		
		for(int i = 0; i < filterResult.size(); i++) {
			if(filterResult.get(i).equals(r3)) {
				assertEquals(r3, filterResult.get(i));
			} else {
				assertEquals(null, filterResult.get(i));
			}
		}

	}
	
	@Test
	public void testGetFilteredResidentList2() {
		ResidentRepository residentRepository = new ResidentRepositoryStub();
		BaseResidentService residentService = new BaseResidentService();
		
		Resident r1 = new Resident("Vorname", "Nachname", "Strasse", "Stadt", null);
		Resident r2 = new Resident("Vorname2", "Nachname2", "Strasse2", "Stadt2", null);
		Resident r3 = new Resident("Max", "Mustermann", "Musterstrasse", "Musterstadt", null);
		
		((ResidentRepositoryStub) residentRepository).addResident(r1);
		((ResidentRepositoryStub) residentRepository).addResident(r2);
		((ResidentRepositoryStub) residentRepository).addResident(r3);
		
		residentService.setResidentRepository(residentRepository);
		
		Resident filter = new Resident("Vorn*", "Nachn*", "Stra*", "Stadt*", null);
		List<Resident> filterResult = residentService.getFilteredResidentsList(filter);
		
		for(int i = 0; i < filterResult.size(); i++) {
			if(filterResult.get(i).equals(r2)) {
				assertEquals(r2, filterResult.get(i));
			} else if (filterResult.get(i).equals(r1)) {
				assertEquals(r1, filterResult.get(i));
			} else {
				assertEquals(null, filterResult.get(i));
			}
		}

	}
	
	@Test
	public void testGetFilteredResidentList3() {
		ResidentRepository residentRepository = new ResidentRepositoryStub();
		BaseResidentService residentService = new BaseResidentService();
		
		Resident r1 = new Resident("Vorname", "Nachname", "Strasse", "Stadt", null);
		Resident r2 = new Resident("Vorname2", "Nachname2", "Strasse2", "Stadt2", null);
		Resident r3 = new Resident("Max", "Mustermann", "Musterstrasse", "Musterstadt", null);
		
		((ResidentRepositoryStub) residentRepository).addResident(r1);
		((ResidentRepositoryStub) residentRepository).addResident(r2);
		((ResidentRepositoryStub) residentRepository).addResident(r3);
		
		residentService.setResidentRepository(residentRepository);
		
		// Stadt Pattern wird ignoriert und liefert falsche Liste zurück
		Resident filter = new Resident("Vorn*", "Nachn*", "Stra*", "Stadt2", null);
		List<Resident> filterResult = residentService.getFilteredResidentsList(filter);
		
		for(int i = 0; i < filterResult.size(); i++) {
			if(filterResult.get(i).equals(r2)) {
				assertEquals(r2, filterResult.get(i));
			} else {
				assertEquals(null, filterResult.get(i));
			}
		}

	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testGetUniqueResident1() {
		ResidentRepository residentRepository = new ResidentRepositoryStub();
		BaseResidentService residentService = new BaseResidentService();
		
		Resident r1 = new Resident("Vorname", "Nachname", "Strasse", "Stadt", null);
		Resident r2 = new Resident("Vorname2", "Nachname2", "Strasse2", "Stadt2", null);
		Resident r3 = new Resident("Max", "Mustermann", "Musterstrasse", "Musterstadt", null);
		
		((ResidentRepositoryStub) residentRepository).addResident(r1);
		((ResidentRepositoryStub) residentRepository).addResident(r2);
		((ResidentRepositoryStub) residentRepository).addResident(r3);
		
		residentService.setResidentRepository(residentRepository);
		
		Resident filter = new Resident("Max", "Mustermann", "Musterstrasse", "Musterstadt", null);
		Resident uniqueResult = new Resident();
		try {
			uniqueResult = residentService.getUniqueResident(filter);
		} catch (ResidentServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(r3.getGivenName(), uniqueResult.getGivenName());
		assertEquals(r3.getFamilyName(), uniqueResult.getFamilyName());
		assertEquals(r3.getStreet(), uniqueResult.getStreet());
		assertEquals(r3.getCity(), uniqueResult.getCity());
		assertEquals(r3.getDateOfBirth(), uniqueResult.getDateOfBirth());

	}
	
	@Test
	public void testGetUniqueResident2() {
		ResidentRepository residentRepository = new ResidentRepositoryStub();
		BaseResidentService residentService = new BaseResidentService();
		
		Resident r1 = new Resident("Vorname", "Nachname", "Strasse", "Stadt", null);
		Resident r2 = new Resident("Vorname2", "Nachname2", "Strasse2", "Stadt2", null);
		Resident r3 = new Resident("Max", "Mustermann", "Musterstrasse", "Musterstadt", null);
		
		((ResidentRepositoryStub) residentRepository).addResident(r1);
		((ResidentRepositoryStub) residentRepository).addResident(r2);
		((ResidentRepositoryStub) residentRepository).addResident(r3);
		
		residentService.setResidentRepository(residentRepository);
		
		Resident filter = new Resident("Vorname2", "Nachname2", "Strasse2", "Stadt2", null);
		Resident uniqueResult = new Resident();
		try {
			uniqueResult = residentService.getUniqueResident(filter);
		} catch (ResidentServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(r2.getGivenName(), uniqueResult.getGivenName());
		assertEquals(r2.getFamilyName(), uniqueResult.getFamilyName());
		assertEquals(r2.getStreet(), uniqueResult.getStreet());
		assertEquals(r2.getCity(), uniqueResult.getCity());
		assertEquals(r2.getDateOfBirth(), uniqueResult.getDateOfBirth());

	}
	
	@Test
	public void testGetUniqueResident3() {
		ResidentRepository residentRepository = new ResidentRepositoryStub();
		BaseResidentService residentService = new BaseResidentService();
		
		Resident r1 = new Resident("Vorname", "Nachname", "Strasse", "Stadt", null);
		Resident r2 = new Resident("Vorname2", "Nachname2", "Strasse2", "Stadt2", null);
		Resident r3 = new Resident("Max", "Mustermann", "Musterstrasse", "Musterstadt", null);
		
		((ResidentRepositoryStub) residentRepository).addResident(r1);
		((ResidentRepositoryStub) residentRepository).addResident(r2);
		((ResidentRepositoryStub) residentRepository).addResident(r3);
		
		residentService.setResidentRepository(residentRepository);
		
		// Angegebener Resident existiert nicht. -> Test schlägt fehl.
		Resident filter = new Resident("Vorname2", "Nachname", "Strasse", "Stadt", null);
		Resident uniqueResult = new Resident();
		try {
			uniqueResult = residentService.getUniqueResident(filter);
		} catch (ResidentServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(r1.getGivenName(), uniqueResult.getGivenName());
		assertEquals(r1.getFamilyName(), uniqueResult.getFamilyName());
		assertEquals(r1.getStreet(), uniqueResult.getStreet());
		assertEquals(r1.getCity(), uniqueResult.getCity());
		assertEquals(r1.getDateOfBirth(), uniqueResult.getDateOfBirth());

	}
	

}
