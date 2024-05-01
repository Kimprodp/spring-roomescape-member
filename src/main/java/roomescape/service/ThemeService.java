package roomescape.service;

import java.util.List;
import org.springframework.stereotype.Service;
import roomescape.domain.Theme;
import roomescape.dto.ThemeRequest;
import roomescape.dto.ThemeResponse;
import roomescape.repository.ReservationRepository;
import roomescape.repository.ThemeRepository;

@Service
public class ThemeService {

    private final ThemeRepository themeRepository;
    private final ReservationRepository reservationRepository;

    public ThemeService(ThemeRepository themeRepository, ReservationRepository reservationRepository) {
        this.themeRepository = themeRepository;
        this.reservationRepository = reservationRepository;
    }

    public Long addTheme(ThemeRequest themeRequest) {
        validateNameDuplicate(themeRequest.name());
        Theme theme = themeRequest.toEntity();
        return themeRepository.save(theme);
    }

    public List<ThemeResponse> getAllTheme() {
        List<Theme> themes = themeRepository.findAll();
        return themes.stream()
                .map(ThemeResponse::from)
                .toList();
    }

    public ThemeResponse getTheme(Long id) {
        validateIdExist(id);
        Theme theme = themeRepository.findById(id);
        return ThemeResponse.from(theme);
    }

    public void deleteTheme(Long id) {
        validateIdExist(id);
        if (reservationRepository.existTimeId(id)) {
            throw new IllegalArgumentException("[ERROR] 해당 시간에 예약이 존재합니다.");
        }
        themeRepository.delete(id);
    }

    private void validateIdExist(Long id) {
        if (!themeRepository.existId(id)) {
            throw new IllegalArgumentException("[ERROR] id가 존재하지 않습니다 : " + id);
        }
    }

    public void validateNameDuplicate(String name) {
        if (themeRepository.existName(name)) {
            throw new IllegalArgumentException("[ERROR] 동일한 이름이 존재합니다. : " + name);
        }
    }
}