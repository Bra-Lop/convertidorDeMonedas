import java.util.Objects;

public final class divisaAtrib {
    private final String base_code;
    private final String target_code;
    private final float conversion_result;


    public divisaAtrib(String base_code
            , String target_code
            , float conversion_result) {
        this.base_code = base_code;
        this.target_code = target_code;
        this.conversion_result = conversion_result;
    }

    public String base_code() {
        return base_code;
    }

    public String target_code() {
        return target_code;
    }

    public float conversion_result() {
        return conversion_result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (divisaAtrib) obj;
        return Objects.equals(this.base_code, that.base_code) &&
                Objects.equals(this.target_code, that.target_code) &&
                Float.floatToIntBits(this.conversion_result) == Float.floatToIntBits(that.conversion_result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(base_code, target_code, conversion_result);
    }

    @Override
    public String toString() {
        return "Resultado de busqueda [" +
                "Divisa a convertir=" + base_code + ", " +
                "Divisa convertida=" + target_code + ", " +
                "Total de conversion=" + conversion_result + ']';
    }
}