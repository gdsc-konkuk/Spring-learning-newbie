package gdsc.shine.springlearningsimple.bean.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationFieldService {
    @Autowired
    private StationRepository stationRepository;

    public String sayHi() {
        return stationRepository.sayHi();
    }
}
