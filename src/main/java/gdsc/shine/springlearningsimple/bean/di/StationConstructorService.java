package gdsc.shine.springlearningsimple.bean.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationConstructorService {
    private StationRepository stationRepository;

    @Autowired
    public StationConstructorService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public String sayHi() {
        return stationRepository.sayHi();
    }
}
