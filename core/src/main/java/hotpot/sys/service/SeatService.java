package hotpot.sys.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hotpot.common.service.SimpleCurdService;
import hotpot.sys.entity.Seat;
import hotpot.sys.repository.SeatRepository;
@Service
public class SeatService extends SimpleCurdService<Seat, Long> {
	
	@Autowired
	private SeatRepository seatRepository;
	
	public Seat findByName(String name){
		return seatRepository.findByName(name);
	}
}
