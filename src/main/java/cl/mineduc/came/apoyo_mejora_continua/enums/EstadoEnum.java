package cl.mineduc.came.apoyo_mejora_continua.enums;

public enum EstadoEnum {
    Habilitado(2416190883893347335l),
    Inhabilitado(2416190883893347336l);

    private final Long value;
    EstadoEnum(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

}
