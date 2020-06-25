import static org.junit.Assert.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.easymock.EasyMock.*;

import org.junit.Test;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.repository.ResidentRepositoryStub;
import de.hfu.residents.service.BaseResidentService;

public class EasyMockTest {

	@Test
	public void test() {
		Konto quelle = new Konto("1", 1000.0);
		Konto ziel = new Konto("2", 100.0);
		KontoManager kontoManagerMock = createMock(KontoManager.class);
		expect(kontoManagerMock.findeKonto("1")).andReturn(quelle);
		expect(kontoManagerMock.findeKonto("2")).andReturn(ziel);
		kontoManagerMock.updateKonto(quelle);
		kontoManagerMock.updateKonto(ziel);
		replay(kontoManagerMock);
		KontoService service = new KontoService();
		service.setKontoManager(kontoManagerMock);
		service.transfer("1", "2", 500.0);
		verify(kontoManagerMock);
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// ResidentRepository residentRepository = new ResidentRepositoryStub();
		BaseResidentService residentService = new BaseResidentService();
		
		Resident r1 = new Resident("Vorname", "Nachname", "Strasse", "Stadt", null);
		Resident r2 = new Resident("Vorname2", "Nachname2", "Strasse2", "Stadt2", null);
		Resident r3 = new Resident("Max", "Mustermann", "Musterstrasse", "Musterstadt", null);
		
		ResidentRepository residentRepositoryMock = createMock(ResidentRepository.class);
		expect(residentRepositoryMock);
		
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

}
