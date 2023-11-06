package gdsc.shine.springlearningsimple.bean.di;

import org.springframework.stereotype.Component;

@Component
public class StationFieldService {
    private StationRepository stationRepository;

    public String sayHi() {
        return stationRepository.sayHi();
    }
}
