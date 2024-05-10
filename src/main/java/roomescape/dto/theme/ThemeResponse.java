package roomescape.dto.theme;

import roomescape.domain.Theme.Theme;

public record ThemeResponse(
        Long id,
        String name,
        String description,
        String thumbnail
) {

    public static ThemeResponse from(Theme theme) {
        return new ThemeResponse(
                theme.getId(),
                theme.getName().getName(),
                theme.getDescription().getDescription(),
                theme.getThumbnail().getThumbnail()
        );
    }
}