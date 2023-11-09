package gdsc.shine.springlearningsimple.bean.di;

import org.springframework.stereotype.Component;

@Component("stationConstructorService")
public class StationConstructorService {
    private StationRepository stationRepository;

    public String sayHi() {
        return stationRepository.sayHi();
    }
}
