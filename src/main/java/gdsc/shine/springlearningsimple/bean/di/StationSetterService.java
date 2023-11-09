package gdsc.shine.springlearningsimple.bean.di;

import org.springframework.stereotype.Component;

@Component("stationSetterService")
public class StationSetterService {
    private StationRepository stationRepository;

    public String sayHi() {
        return stationRepository.sayHi();
    }
}
