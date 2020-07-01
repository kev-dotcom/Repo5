import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class EasyMockTest {

    private ResidentRepository mockResidentRepository;
    private BaseResidentService baseResidentService = new BaseResidentService();

    private Resident r1;
    private Resident r2;
    private Resident r3;


    public EasyMockTest() {
    	
        r1 = new Resident("Vorname", "Nachname", "Strasse", "Stadt", null);
		r2 = new Resident("Vorname2", "Nachname2", "Strasse2", "Stadt2", null);
		r3 = new Resident("Max", "Mustermann", "Musterstrasse", "Musterstadt", null);
		
        ArrayList<Resident> residents = new ArrayList<Resident>();
        residents.add(r1);
        residents.add(r2);
        residents.add(r3);
        
        mockResidentRepository = createMock(ResidentRepository.class);
        
        expect(mockResidentRepository.getResidents()).andReturn(residents);
        replay(mockResidentRepository);
        
        baseResidentService.setResidentRepository(mockResidentRepository);
    }

    @Test(expected = ResidentServiceException.class, timeout = 1000)
    public void testGetUniqueResidentWithWildcard() throws Exception {
        Resident r = new Resident("TestMitWildcard*", "", "", "", null);
        baseResidentService.getUniqueResident(r);
        verify(mockResidentRepository);
    }


    @Test(expected = ResidentServiceException.class, timeout = 1000)
    public void testGetUniqueResidentNoResult() throws Exception {
        Resident r = new Resident("Test", "", "", "", null);
        baseResidentService.getUniqueResident(r);
        verify(mockResidentRepository);
    }


    @Test
    public void testGetUniqueResident() throws Exception {
        Resident r = new Resident("Vorname", "Nachname", "Strasse", "Stadt", null);
        Assert.assertThat(r.getFamilyName(), equalTo(baseResidentService.getUniqueResident(r).getFamilyName()));
        verify(mockResidentRepository);
    }


    @Test
    public void testGetFilteredResidentsListWithWildcard() {
        Resident r = new Resident("", "Na*", "", "", null);
        Assert.assertThat(Arrays.asList(r1, r2), equalTo(baseResidentService.getFilteredResidentsList(r)));
        verify(mockResidentRepository);
    }

    
    @Test
    public void testGetFilteredResidentsList() throws Exception {
        Resident r = new Resident("", "Nachname", "", "", null);
        Assert.assertThat(Arrays.asList(r1), equalTo(baseResidentService.getFilteredResidentsList(r)));
        verify(mockResidentRepository);
    }

    
    @Test
    public void testGetFilteredResidentsListNoResult() throws Exception {
        Resident r = new Resident("error", "", "", "", null);
        Assert.assertThat(Arrays.asList(), equalTo(baseResidentService.getFilteredResidentsList(r)));
        verify(mockResidentRepository);
    }
} 