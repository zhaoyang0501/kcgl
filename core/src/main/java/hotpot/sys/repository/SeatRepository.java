package hotpot.sys.repository;
import hotpot.common.repository.SimpleCurdRepository;
import hotpot.sys.entity.Seat;
public interface SeatRepository   extends SimpleCurdRepository<Seat ,Long>{

	Seat findByName(String name);
}
