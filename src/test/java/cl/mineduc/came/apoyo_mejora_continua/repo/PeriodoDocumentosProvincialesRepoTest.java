package cl.mineduc.came.apoyo_mejora_continua.repo;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import cl.mineduc.came.apoyo_mejora_continua.mappers.PeriodoDocumentosProvincialesMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoDocumentosProvinciales;

@RunWith(MockitoJUnitRunner.class)
public class PeriodoDocumentosProvincialesRepoTest {
	
	@Mock
	private PeriodoDocumentosProvincialesMapper documentMapper;
	
	@InjectMocks
	private PeriodoDocumentosProvincialesRepo documentRepo;
	
	PeriodoDocumentosProvinciales documents;
	
	List<PeriodoDocumentosProvinciales> documentos = new ArrayList<PeriodoDocumentosProvinciales>();
	
	@Before
	public void setUp() {
		documents = mock(PeriodoDocumentosProvinciales.class);
		documentos.add(documents);
		
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_insertDocumentsProvincials_happyPath() {
		when(documentMapper.insert(documents)).thenReturn(1);
		documentRepo.insertDocumentsProvincials(documents);
	}
	
	@Test
	public void test_deleteDocumentsProvincialsById_happyPath() {
		when(documentMapper.deleteById(1l)).thenReturn(1);
		documentRepo.deleteDocumentsProvincialsById(1l);
	}
	
	@Test
	public void test_getDocumentsProvincialsById_happyPath() {
		when(documentMapper.getById(1l)).thenReturn(documents);
		PeriodoDocumentosProvinciales doc = documentRepo.getDocumentsProvincialsById(1l);
		assertNotNull(doc);
	}
	
	@Test
	public void test_getAllDocumentsProvincials_happyPath() {
		when(documentMapper.getAll()).thenReturn(documentos);
		List<PeriodoDocumentosProvinciales> doc = documentRepo.getAllDocumentsProvincials();
		assertNotNull(doc);
	}
	
	@Test
	public void test_updateDocumentsProvincials_happyPath() {
		when(documentMapper.updateById(documents)).thenReturn(1);
		documentRepo.updateDocumentsProvincials(documents);
		assertNotNull(documents);
	}

}
