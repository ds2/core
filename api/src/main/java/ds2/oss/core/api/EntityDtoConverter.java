package ds2.oss.core.api;

/**
 * Created by dstrauss on 29.03.17.
 */
public interface EntityDtoConverter<PKTYPE, ENTITY extends IdAware<PKTYPE>, DTO, EXP extends Exception> {
    DTO toDto(ENTITY e) throws EXP;

    ENTITY toEntity(DTO d) throws EXP;
}
