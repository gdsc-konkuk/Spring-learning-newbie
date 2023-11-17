package gdsc.shine.springlearningsimple.bean.di;

import org.springframework.stereotype.Component;

@Component
public class StationConstructorService {
    private StationRepository stationRepository;

    public StationConstructorService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public String sayHi() {
        return stationRepository.sayHi();
    }

    public void setStationRepository(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

}
