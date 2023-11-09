package gdsc.shine.springlearningsimple.bean.di;

import org.springframework.stereotype.Component;

@Component("stationConstructorService")
public class StationConstructorService {
	private final StationRepository stationRepository;


    public String sayHi() {
        return stationRepository.sayHi();
    }
}
