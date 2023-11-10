package gdsc.shine.springlearningsimple.bean.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StationFieldService {
    @Autowired
    private StationRepository stationRepository;

    public StationFieldService() {}

    public String sayHi() {
        return stationRepository.sayHi();
    }
}
