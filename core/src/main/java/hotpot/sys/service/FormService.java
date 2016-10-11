package hotpot.sys.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hotpot.common.service.SimpleCurdService;
import hotpot.sys.entity.Form;
import hotpot.sys.repository.FormRepository;
@Service
public class FormService extends SimpleCurdService<Form, Long> {
	
	@Autowired
	private FormRepository formRepository;
	
	public List<Form> findByState(String state){
		return this.formRepository.findByState(state);
	}
}
